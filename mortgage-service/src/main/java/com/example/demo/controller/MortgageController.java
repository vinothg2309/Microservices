package com.example.demo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.entity.Mortgage;
import com.example.demo.model.MortgageDetail;
import com.example.demo.repository.MortgageRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RefreshScope
@RestController
public class MortgageController {

	@Autowired 
	Environment environment;

	@Value("${user.message}")
	private String message;

	@GetMapping("/userMsg")
	public String userMsg() {
		return message;
	}

	@Value("classpath:mortgage.json")
	Resource resourceFile;

	@Value("${eureka.instance.instance-id}") private String instanceId;

	@Autowired
	private MortgageRepository mortgageRepository;

	@Value("${user.message}") 
	private String userMsg;

	@GetMapping("/mortgage-test")
	public String testing() throws UnknownHostException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		return "Hello from"+ environment.getProperty("eureka.instance.instanceId")+"--PORT--"
		+request.getServerPort()+"Mortgage";

	}

	@GetMapping("/greeting")
	public String greeting() throws UnknownHostException{ 
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		return "Hello from Receiver --> "+environment.getProperty("eureka.instance.instanceId");

	}

	@SuppressWarnings("unchecked")
	@GetMapping("/getfortgageDetails/{custnumber}")

	public MortgageDetail getMotgageDetails (@PathVariable String custnumber) throws IOException, ParseException{

		File file = resourceFile.getFile();

		MortgageDetail mortgageDetail = new MortgageDetail();

		JSONParser jsonParser= new JSONParser();

		try (FileReader reader=new FileReader(file)){

			//Read 350N file 
			Object obj = jsonParser.parse(reader);

			JSONArray loanList=(JSONArray) obj;

			System.out.println(loanList); 
			Gson gson=new Gson();

			List<MortgageDetail> mortgagelist=new ArrayList<>();

			loanList.forEach( emp-> mortgagelist.add(gson.fromJson(((JSONObject) emp).
					get("mortgagedetail").toString(), new TypeToken<MortgageDetail>(){}.getType()))); //mortgagebetall mortgagelist.stream().filter(Ioanobj - Ioanobj.getCustomerfumber().e

			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); //
			mortgageDetail.setStatus("Saved Successfully!!!");

			mortgageDetail.setInstanceId(instanceId); mortgageDetail.setInstancePort (request.getServerPort());

		} catch (FileNotFoundException e) { e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return mortgageDetail;
	}

	@PostMapping(path="/saveMortgage") 
	private Mortgage saveLoan (@RequestBody Mortgage loan){

		Mortgage savedLoan = mortgageRepository.save(loan); return savedLoan; 
	}

	@GetMapping(path="/fetchMortgage/{customerId}")

	private MortgageDetail fetchLoans (@PathVariable Integer customerId) { 
		MortgageDetail mortgageDetail = new MortgageDetail();

		List<Mortgage> mortgages = mortgageRepository.findByCustomerNumber(customerId);

		mortgageDetail.setMortgages (mortgages); mortgageDetail.setStatus("Success");

		HttpServletRequest request= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		mortgageDetail.setInstancePort(request.getServerPort()); mortgageDetail.setInstanceId(environment.getProperty("eureka.instance.instanceId"));

		return mortgageDetail;

	}


}
