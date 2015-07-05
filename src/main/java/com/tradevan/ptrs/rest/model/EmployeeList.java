package com.tradevan.ptrs.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Wrap class of List<Employee>, the reason which is needed is JABX 2 can't marshal 
 * java.util.List class properly.
 * 
 * @author 2904
 *
 */
@XmlRootElement(name="employees")
public class EmployeeList {
	private int count;
	private List<Employee> employees;

	public EmployeeList() {}
	
	public EmployeeList(List<Employee> employees) {
		this.employees = employees;
		this.count = employees.size();
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	@XmlElement(name="employee")
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
}
