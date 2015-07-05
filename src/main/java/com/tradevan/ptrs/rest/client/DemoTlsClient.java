package com.tradevan.ptrs.rest.client;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import org.springframework.web.client.RestTemplate;

import com.tradevan.ptrs.rest.model.Employee;
import com.tradevan.ptrs.rest.model.EmployeeList;


public class DemoTlsClient {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	
	private RestTemplate restTemplate;
	
	public static final String BASE_URL = "https://localhost:8443/ptrs-rest";
	public static final String GET_ALL_URL;
	public static final String POST_URL;
	public static final String GET_PUT_DELETE_URL;
	
	static{
		GET_ALL_URL = new StringBuilder(BASE_URL).append("/service/demo_employees").toString();
		POST_URL = new StringBuilder(BASE_URL).append("/service/demo_employee").toString();
		GET_PUT_DELETE_URL = new StringBuilder(BASE_URL).append("/service/demo_employee/{id}").toString();
	}
	
	public DemoTlsClient(RestTemplate restTemplate){
		this.restTemplate = restTemplate;
	}


	public void getEmployee() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "2904");
		
		Employee e = restTemplate.getForObject(GET_PUT_DELETE_URL, Employee.class, params);
		logger.info("result of getEmployee(2904):");
		logger.info("Employee<{}> : {}", e.getId(), e.getName());
		logger.info("======end of getEmployee =====");
	}

	public void getAllEmployee() {
		EmployeeList list = restTemplate.getForObject(GET_ALL_URL,
				EmployeeList.class);
		logger.info("result of getAllEmployee:");
		for (Employee e : list.getEmployees()) {
			logger.info("Employee<{}> : {}", e.getId(), e.getName());
		}
		logger.info("======end of getAllEmployee =====");
	}

	public void postEmployee() {
		Employee newEmployee = new Employee();
		newEmployee.setId(3456L);
		newEmployee.setName("Jyun Yun Wang");
		Employee persistedNewEmployee = restTemplate.postForObject(POST_URL,
				newEmployee, Employee.class);

		logger.info("result of postEmployee:");
		logger.info("persistedNewEmployee<{}> : {}",
				persistedNewEmployee.getId(), persistedNewEmployee.getName());
		logger.info("======end of postEmployee =====");
	}

	public void putEmployee() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "2905");

		Employee newEmployee = new Employee();
		newEmployee.setId(2905L);
		newEmployee.setName("Jyun Yun HA");
		restTemplate.put(GET_PUT_DELETE_URL, newEmployee, params);

		logger.info("result of putEmployee:");
		logger.info("(put method has no returen value)");
		logger.info("======end of putEmployee =====");
	}

	public void deleteEmployee() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "2906");
		restTemplate.delete(GET_PUT_DELETE_URL,params);

		logger.info("result of deleteEmployee(2906):");
		logger.info("(delete method has no returen value)");
		logger.info("======end of deleteEmployee =====");
	}

}
