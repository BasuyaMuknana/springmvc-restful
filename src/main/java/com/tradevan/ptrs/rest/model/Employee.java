package com.tradevan.ptrs.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.tradevan.framework.model.BaseEntity;

@Entity
@Table(name = "EMPLOYEE")
@XmlRootElement(name="employee")
public class Employee extends BaseEntity{
	
//	@Id
//    @GeneratedValue(strategy = GenerationType.TABLE)
//    protected Long id;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1118874532021341630L;
	@Column(name = "name")
	private String name;
	
	public Employee(){
		
	}
	
	public Employee(long id, String name){
		this.id = id;
		this.name = name;
	}
	
	@XmlElement(name="id")
	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@XmlElement(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
