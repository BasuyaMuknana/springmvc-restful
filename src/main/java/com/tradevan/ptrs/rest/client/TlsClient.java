package com.tradevan.ptrs.rest.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;

import org.springframework.web.client.RestTemplate;

import com.tradevan.ptrs.rest.model.Employee;
import com.tradevan.ptrs.rest.model.EmployeeList;

public class TlsClient extends AbstractTlsClient {

	public TlsClient(RestTemplate restTemplate) {
		this.logger = LoggerFactory.getLogger(getClass());
		this.restTemplate = restTemplate;
	}

	public Employee getEmployee(long id) {
		String url = new StringBuilder(SERVICE_BASE_URL).append(R_EMPLOYEE)
				.append(R_ID).toString();
		logger.info("preparing getEmployee url:{}", url);

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", String.valueOf(id));
		logger.info("preparing getEmployee id:{}", id);

		Employee e = restTemplate.getForObject(url, Employee.class, params);
		logger.info("result of getEmployee({}):", id);
		logger.info("Employee<{}> : {}", e.getId(), e.getName());
		logger.info("===== end of getEmployee =====");
		return e;
	}

	public Employee postEmployee(Employee newEmployee) {
		String url = new StringBuilder(SERVICE_BASE_URL).append(R_EMPLOYEE)
				.toString();
		logger.info("preparing postEmployee url:{}", url);
		// Employee newEmployee = new Employee();
		// newEmployee.setId(3456L);
		// newEmployee.setName("Jyun Yun Wang");
		Employee persistedNewEmployee = restTemplate.postForObject(url,
				newEmployee, Employee.class);

		logger.info("result of postEmployee:");
		logger.info("persistedNewEmployee<{}> : {}",
				persistedNewEmployee.getId(), persistedNewEmployee.getName());
		logger.info("===== end of postEmployee =====");
		return persistedNewEmployee;
	}

	public void putEmployee(Employee newEmployee) {
		String url = new StringBuilder(SERVICE_BASE_URL).append(R_EMPLOYEE)
				.append(R_ID).toString();
		logger.info("preparing putEmployee url:{}", url);

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", String.valueOf(newEmployee.getId()));

		// Employee newEmployee = new Employee();
		// newEmployee.setId(2905L);
		// newEmployee.setName("Jyun Yun HA");
		restTemplate.put(url, newEmployee, params);

		logger.info("result of putEmployee:");
		logger.info("(put method has no returen value)");
		logger.info("===== end of putEmployee =====");
	}

	public void deleteEmployee(long id) {
		String url = new StringBuilder(SERVICE_BASE_URL).append(R_EMPLOYEE)
				.append(R_ID).toString();
		logger.info("preparing deleteEmployee url:{}", url);

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", String.valueOf(id));
		logger.info("preparing deleteEmployee id:{}", id);

		restTemplate.delete(url, params);

		logger.info("result of deleteEmployee({}):", id);
		logger.info("(delete method has no returen value)");
		logger.info("===== end of deleteEmployee =====");
	}

	public List<Employee> getAllEmployee() {
		String url = new StringBuilder(SERVICE_BASE_URL).append(R_EMPLOYEES)
				.toString();
		logger.info("preparing getAllEmployee url:{}", url);

		EmployeeList list = restTemplate.getForObject(url, EmployeeList.class);
		logger.info("result of getAllEmployee:");
		for (Employee e : list.getEmployees()) {
			logger.info("Employee<{}> : {}", e.getId(), e.getName());
		}
		logger.info("===== end of getAllEmployee =====");
		return list.getEmployees();
	}

}
