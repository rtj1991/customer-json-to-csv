package com.assignment.csv.json.service;

import com.assignment.csv.json.model.Customers;
import com.assignment.csv.json.repository.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonRepository commonRepository;

    @Override
    public Customers findById(String id) {
        return commonRepository.findAllById(id);
    }

    @Override
    public List<Customers> findAllByName(String name) {
    	return commonRepository.findAllByName(name);
    }

    @Override
    public  Map<String,List<Customers>> getCustomersGroupByZipCode() {
        Map<String,List<Customers>> groupByResult= new HashMap<>();
        List<String> stringList = commonRepository.findAllGroupByZipCode();
        for (String list:stringList) {
            List<Customers> customers = commonRepository.
                    findAllByZipCode(list);
            groupByResult.put(list,customers);
        }
        return groupByResult;
    }
}
