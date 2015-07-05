package com.tradevan.ptrs.rest.controller;

import java.io.StringReader;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tradevan.ptrs.rest.model.Employee;
import com.tradevan.ptrs.rest.model.EmployeeList;
import com.tradevan.ptrs.rest.repository.DemoEmployeeRepository;
import com.tradevan.ptrs.rest.repository.EmployeeRepository;

@Controller
public class EmployeeController {
	//the view name defined in rest-servlet.xml
	private static final String XML_VIEW_NAME = "employees";
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private Jaxb2Marshaller jaxb2Mashaller;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@RequestMapping(method=RequestMethod.GET, value="/employee/{id}")
	public ModelAndView getEmployee(@PathVariable String id) {

		Employee e = employeeRepository.findById(Long.parseLong(id));
		return new ModelAndView(XML_VIEW_NAME, "object", e);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/employees")
	public ModelAndView getEmployees() {
		List<Employee> employees = (List<Employee>)employeeRepository.findAll();
		EmployeeList list = new EmployeeList(employees);
		return new ModelAndView(XML_VIEW_NAME, "employees", list);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/employee")
	public ModelAndView addEmployee(@RequestBody String body) {
		Source source = new StreamSource(new StringReader(body));
		Employee e = (Employee) jaxb2Mashaller.unmarshal(source);
		employeeRepository.save(e);
		return new ModelAndView(XML_VIEW_NAME, "object", e);
	}

	@RequestMapping(method=RequestMethod.PUT, value="/employee/{id}")
	public ModelAndView updateEmployee(@RequestBody String body) {
		Source source = new StreamSource(new StringReader(body));
		Employee e = (Employee) jaxb2Mashaller.unmarshal(source);
		employeeRepository.update(e);
		return new ModelAndView(XML_VIEW_NAME, "object", e);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/employee/{id}")
	public ModelAndView removeEmployee(@PathVariable String id) {
		employeeRepository.delete(Long.parseLong(id));
		List<Employee> employees = (List<Employee>)employeeRepository.findAll();
		EmployeeList list = new EmployeeList(employees);
		return new ModelAndView(XML_VIEW_NAME, "employees", list);
	}
}
