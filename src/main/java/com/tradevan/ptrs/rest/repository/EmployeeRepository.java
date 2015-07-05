package com.tradevan.ptrs.rest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tradevan.framework.repository.BaseRepository;
import com.tradevan.ptrs.rest.model.Employee;

public interface EmployeeRepository extends BaseRepository<Employee, Long>{
	
}
