package com.assignment.csv.json.model;

import lombok.Data;

import java.util.List;

@Data
public class Common {
    private String _id;
    private String index;
    private String age;
    private String eyeColor;
    private String name;
    private String gender;
    private String company;
    private String email;
    private String phone;
    private String about;
    private String registered;
    private String latitude;
    private String longitude;
    private List<String> tags;
    private Address address;
}