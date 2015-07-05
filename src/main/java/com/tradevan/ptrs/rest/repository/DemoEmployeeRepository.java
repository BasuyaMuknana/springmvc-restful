package com.tradevan.ptrs.rest.repository;

import java.util.List;

import com.tradevan.ptrs.rest.model.Employee;

public interface DemoEmployeeRepository {
	public Employee get(long id);
	public void add(Employee employee);
	public void update(Employee employee);
	public void remove(long id);
	public List<Employee> getAll();
	
}
