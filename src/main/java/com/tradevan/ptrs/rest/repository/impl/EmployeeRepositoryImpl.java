package com.tradevan.ptrs.rest.repository.impl;


import org.springframework.stereotype.Repository;

import com.tradevan.framework.repository.JpaBaseRepository;
import com.tradevan.ptrs.rest.model.Employee;

import com.tradevan.ptrs.rest.repository.EmployeeRepository;

@Repository
public class EmployeeRepositoryImpl extends JpaBaseRepository<Employee, Long> implements EmployeeRepository{


}
