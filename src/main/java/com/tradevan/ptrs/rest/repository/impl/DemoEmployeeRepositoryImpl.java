package com.tradevan.ptrs.rest.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tradevan.ptrs.rest.model.Employee;
import com.tradevan.ptrs.rest.repository.DemoEmployeeRepository;

@Repository
public class DemoEmployeeRepositoryImpl implements DemoEmployeeRepository{
	private static List<Employee> fakeRepo;
	{
		fakeRepo = new ArrayList<Employee>();
		fakeRepo.add(new Employee(2904L, "Jyun Yun Wu"));
		fakeRepo.add(new Employee(2905L, "Jyun Yun Lee"));
		fakeRepo.add(new Employee(2906L, "Jyun Yun Chen"));
	}
	@Override
	public Employee get(long id) {
		for(Employee e : fakeRepo){
			if (e.getId() == id){
				return e;
			}
		}
		return new Employee();
	}

	@Override
	public void add(Employee employee) {
		for(Employee e : fakeRepo){
			if (e.getId() == employee.getId()){
				return;
			}
		}
		fakeRepo.add(employee);
	}

	@Override
	public void update(Employee employee) {
		for(Employee e : fakeRepo){
			if (e.getId() == employee.getId()){
				fakeRepo.set(fakeRepo.indexOf(e), employee);
			}
		}
	}

	@Override
	public void remove(long id) {
		for(Employee e : fakeRepo){
			if (e.getId() == id){
				fakeRepo.remove(fakeRepo.indexOf(e));
			}
		}
	}

	@Override
	public List<Employee> getAll() {
		return fakeRepo;
	}

}
