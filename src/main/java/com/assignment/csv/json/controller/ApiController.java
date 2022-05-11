package com.assignment.csv.json.controller;

import com.assignment.csv.json.model.Customers;
import com.assignment.csv.json.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	CommonService commonService;

	/**
	 * get customer by id
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/customers/{id}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customers> getCustomerById(@PathVariable("id") String id) {
		Customers customers = commonService.findById(id);

		return new ResponseEntity(customers, HttpStatus.OK);
	}

	/**
	 * get customer by name
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/customers/name/{name}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Customers>> getCustomersByName(@PathVariable("name") String name) {
		List<Customers> customer = commonService.findAllByName(name);
		return new ResponseEntity(customer, HttpStatus.OK);
	}

	/**
	 * get customer group by zip code
	 * @return
	 */

	@RequestMapping(value = "/customers/zip", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,List<Customers>>> getCustomersGroupByZipCode() {
		Map<String,List<Customers>> groupByResult = commonService.getCustomersGroupByZipCode();

		return new ResponseEntity(groupByResult, HttpStatus.OK);
	}

}
