package com.example.person.repository;


import com.example.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonDao extends JpaRepository<Person, Integer>, PersonCustomDao {

}
