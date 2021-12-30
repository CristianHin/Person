package com.example.person.repository;


import com.example.person.model.Person;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Repository
public interface PersonCustomDao {

    List<Person> findByNameContaining(String name);

    List<Person> findByLastNameContaining(String lastName);

    List<Person> findByAgeGreaterThan(Integer age);


}
