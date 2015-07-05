package com.tradevan.ptrs.rest.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tradevan.ptrs.rest.model.Employee;
import com.tradevan.ptrs.rest.model.EmployeeList;

public class DemoTlsApplication {
	static Logger logger = LoggerFactory.getLogger(DemoTlsApplication.class);
	
	public static void main(String[] args) {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"conf/client-application-context.xml");

		TlsClient client  = applicationContext.getBean("tlsClient", TlsClient.class);
		
		client.getEmployee(2904L);
		client.getAllEmployee();
		
		Employee newEmployee = new Employee();
		newEmployee.setId(3456L);
		newEmployee.setName("Jyun Yun Wang");
		client.postEmployee(newEmployee);
		
		Employee updatedEmployee = new Employee();
		updatedEmployee.setId(2905L);
		updatedEmployee.setName("Jyun Yun HA");
		client.putEmployee(updatedEmployee);
		
		client.deleteEmployee(2906L);
		client.getAllEmployee();
		
		EmployeeList eList = (EmployeeList)client.getAllRecource("https://localhost:8443/ptrs-rest/service/employees", 
				EmployeeList.class);
		logger.info("result of getAllRecource:");
		for (Employee e : eList.getEmployees()) {
			logger.info("Employee<{}> : {}", e.getId(), e.getName());
		}
		logger.info("======end of getAllRecource =====");
	}
}
