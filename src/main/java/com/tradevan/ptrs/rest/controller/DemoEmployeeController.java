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

@Controller
public class DemoEmployeeController {
	//the view name defined in rest-servlet.xml
	private static final String XML_VIEW_NAME = "employees";
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private Jaxb2Marshaller jaxb2Mashaller;
	
	@Autowired
	private DemoEmployeeRepository demoEmployeeRepository;
	
	@RequestMapping(method=RequestMethod.GET, value="/demo_employee/{id}")
	public ModelAndView getEmployee(@PathVariable String id) {
		Employee e = demoEmployeeRepository.get(Long.parseLong(id));
		return new ModelAndView(XML_VIEW_NAME, "object", e);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/demo_employees")
	public ModelAndView getEmployees() {
		List<Employee> employees = demoEmployeeRepository.getAll();
		EmployeeList list = new EmployeeList(employees);
		return new ModelAndView(XML_VIEW_NAME, "employees", list);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/demo_employee")
	public ModelAndView addEmployee(@RequestBody String body) {
		Source source = new StreamSource(new StringReader(body));
		Employee e = (Employee) jaxb2Mashaller.unmarshal(source);
		demoEmployeeRepository.add(e);
		return new ModelAndView(XML_VIEW_NAME, "object", e);
	}

	@RequestMapping(method=RequestMethod.PUT, value="/demo_employee/{id}")
	public ModelAndView updateEmployee(@RequestBody String body) {
		Source source = new StreamSource(new StringReader(body));
		Employee e = (Employee) jaxb2Mashaller.unmarshal(source);
		demoEmployeeRepository.update(e);
		return new ModelAndView(XML_VIEW_NAME, "object", e);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/demo_employee/{id}")
	public ModelAndView removeEmployee(@PathVariable String id) {
		demoEmployeeRepository.remove(Long.parseLong(id));
		List<Employee> employees = demoEmployeeRepository.getAll();
		EmployeeList list = new EmployeeList(employees);
		return new ModelAndView(XML_VIEW_NAME, "employees", list);
	}
}
