package com.assignment.csv.json.service;

import java.util.List;
import java.util.Map;

import com.assignment.csv.json.model.Customers;


public interface CommonService {

	Customers findById(String id);

	List<Customers> findAllByName(String name);

	Map<String,List<Customers>> getCustomersGroupByZipCode();
}
