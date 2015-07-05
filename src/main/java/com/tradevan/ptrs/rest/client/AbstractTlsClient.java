package com.tradevan.ptrs.rest.client;

import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.client.RestTemplate;



public abstract class AbstractTlsClient {
	@Value("${service.base.url}")
	public static String SERVICE_BASE_URL;
	
	@Value("${resource.employee}")
	public static String R_EMPLOYEE;
	
	@Value("${resource.employees}")
	public static String R_EMPLOYEES;
	
	@Value("${resource.id}")
	public static String R_ID;

	protected Logger logger;
	protected RestTemplate restTemplate;
	
	
	/*
	 * initialize the evil public static field
	 */
	@Value("${service.base.url}")
	public void setServiceBaseUrl(String serviceBaseUrl) {
		SERVICE_BASE_URL = serviceBaseUrl;
	}  
	
	@Value("${resource.employees}")
	public void setResourceEmployees(String resourceEmployees) {
		R_EMPLOYEES = resourceEmployees;
	} 
	
	@Value("${resource.employee}")
	public void setResourceEmployee(String resourceEmployee) {
		R_EMPLOYEE = resourceEmployee;
	} 
	
	@Value("${resource.id}")
	public void setResourceId(String resourceId) {
		R_ID = resourceId;
	} 
	
	@SuppressWarnings("unchecked")
	public Object getAllRecource(String url, Class type){
		logger.info("getAllRecource");
		return restTemplate.getForObject(url, type);
	}
	
	
}
