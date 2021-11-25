# Microservices

## Config-service

#### H2 DB is configured & exposed in tcp port in config-service. All microservices connect to H2 DB via below tcp port

```
	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server inMemoryH2DatabaseServer() throws SQLException{
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "7777","-ifNotExists");
	}
```

#### Properties(config) files of all microservices is placed under /config in resources

## API-Gateway

#### Cors filter is enabled for all microservices
#### Exposes /get-all-apps which fetches all dicovery client registered with discovery server

## Loan-service

#### /loan-mortgage-test - interacts with mortgage-service to get instance details.
#### /hystrix-test - will call fallback method since /test API isn't available in mortgage-service
#### /fetchLoans - returns loan from loan-service & make call to mortgage-service via feign client to fetch mortgage details.


