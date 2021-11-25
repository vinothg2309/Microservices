package com.example.demo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.AllLoandetails;
import com.example.demo.entity.Loan;
import com.example.demo.feign.MortgageServiceRestAPI;
import com.example.demo.model.CustomerLoanDetails;
import com.example.demo.model.MortgageDetail;
import com.example.demo.repository.LoanRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class Loancontroller {

	@Autowired
	Environment environment;

	@Value("${eureka.instance.instanceId}") 
	private String instanceId;

	@Autowired
	private HttpServletRequest request;

	@Value("classpath:loan.json") 
	Resource resourceFile;

	@Autowired
	MortgageServiceRestAPI mortgageServiceRestAPI;

	// @Autowired RestTemplate restTemplate;

	@Value("${user.message}")
	private String userMsg;

	@Autowired
	private LoanRepository loanRepository;

	@GetMapping("/loan-test") 
	public String testing() throws UnknownHostException{

		InetAddress ia = InetAddress.getLocalHost();

		ia.getHostName();
		HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return "Hello from" +instanceId+ ", PORT -- "+ request.getServerPort();
	}

	@RequestMapping("/loan-mortgage-test") 
	public String hi(@RequestParam(value="name", defaultValue="Artaban") String name) {

		//String greeting=this.mortgageServiceRestAPI.getForObject("http://mortgage-service/greeting");

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); 
		String loanServiceDetails= "Sender -->" + environment.getProperty("eureka.instance.instanceId")
		+" --PORT-- " + request.getServerPort();

		return loanServiceDetails+"\n";
	}

	@SuppressWarnings({ "unchecked", "unused", "deprecation" }) 
	@GetMapping("/getLoanDetail/{custNumber}") 
	public AllLoandetails getLoanDetail (@PathVariable("custNumber") Integer custnumber) 
			throws IOException, ParseException {

		AllLoandetails allLoanDetails=new AllLoandetails(); 
		File file=resourceFile.getFile();

		JSONParser jsonParser = new JSONParser(); 
		try (FileReader reader = new FileReader(file)){

			//Read 3SON file

			Object obj=jsonParser.parse(reader);

			JSONArray loanList=(JSONArray) obj;

			System.out.println(loanList); 
			Gson gson = new Gson();

			List<Loan> updateSessionkeyValuelist = new ArrayList<>();

			loanList.forEach( emp-> updateSessionkeyValuelist
					.add(gson.fromJson(((JSONObject) emp).get("loandetail").toString(), 
							new TypeToken<Loan>(){}.getType())) ); 
			List<Loan> loanDetails=updateSessionkeyValuelist.stream()
					.filter(loanobj-> loanobj.getCustomerNumber().equals(custnumber)).collect(Collectors.toList()); 
			allLoanDetails.setLoanDetails(loanDetails);

			//allLoanDetails.setStatus("Saved Successfully!!!");

			allLoanDetails.setInstanceId(instanceId); 
			allLoanDetails.setInstancePort(request.getServerPort());

			//MortgageDetail mortgageDetail this.restTemplate.getForObject("http://

			MortgageDetail mortgageDetail=mortgageServiceRestAPI.fetchMortgage(custnumber);

			allLoanDetails.setMortgageDetail(mortgageDetail);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) { e.printStackTrace();

		} 
		return allLoanDetails;
	}

	@RequestMapping("/userMsg") 
	public String userMsg() throws UnknownHostException {
		HttpServletRequest request =
				((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return userMsg+ " from port: "+ request.getServerPort();
	}

	@RequestMapping (value = "/hello")
	@HystrixCommand (fallbackMethod="planb", commandProperties = {
			@HystrixProperty (name="execution.isolation.thread + timeoutInMilliseconds", value = "1000")

	})
	public String hello() throws InterruptedException { Thread.sleep(2000);
		return "Hello World";
	}

	private String planb() {
		return "Sorry our Systems are busy! try again later.";
	}

	@GetMapping("/hystrix-test") 
	public String hystrixTest() {

		String response = "Success";

		response=mortgageServiceRestAPI.test(); 
		return response;
	}

	@PostMapping(path="/saveLoan")
	private Loan saveLoan (@RequestBody Loan loan) { 
		Loan savedLoan = loanRepository.save(loan); 
		return savedLoan;
	}



	@GetMapping (path="/fetchLoans/{customerId}") 
	private CustomerLoanDetails fetchLoans (@PathVariable Integer customerId) { 
		CustomerLoanDetails customerLoanDetails = new CustomerLoanDetails();

		List<Loan> loans = loanRepository.findByCustomerNumber(customerId); 
		customerLoanDetails.setLoans(loans);

		MortgageDetail mortgageDetail = mortgageServiceRestAPI.fetchMortgage(customerId);

		customerLoanDetails.setMortgageDetail(mortgageDetail);

		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		customerLoanDetails.setInstancePort(request.getServerPort());

		customerLoanDetails.setInstanceId(environment.getProperty("eureka.instance, instanceId"));

		return customerLoanDetails;

	}
}