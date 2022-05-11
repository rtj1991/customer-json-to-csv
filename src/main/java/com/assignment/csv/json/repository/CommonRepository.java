package com.assignment.csv.json.repository;

import com.assignment.csv.json.model.Customers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CommonRepository {

    @Autowired
    private SessionFactory factory;

    public Customers findAllById(String id) {

        Session session = factory.openSession();
        Query query = session.createQuery("FROM Customers c WHERE c.id=:id");
        query.setParameter("id", id);
        Customers customers = (Customers) query.getSingleResult();

        return customers;
    }

    public List<Customers> findAllByName(String name) {

        Session session = factory.openSession();
        Query query = session.createQuery("FROM Customers c WHERE c.name like :name");
        query.setParameter("name", "%"+name+"%");
        List<Customers> customers = query.getResultList();

        return customers;
    }

    public List<Customers> findAllByZipCode(String zipCode) {

        Session session = factory.openSession();
        Query query = session.createQuery("FROM Customers c where c.zipcode=:zipCode ");
        query.setParameter("zipCode", zipCode);
        List<Customers> resultList = query.getResultList();

        return resultList;
    }

    public List<String> findAllGroupByZipCode() {

        Session session = factory.openSession();
        Query query = session.createQuery("select c.zipcode FROM Customers c GROUP BY c.zipcode");
        List<String>  resultList = query.getResultList();

        return resultList;
    }
}
