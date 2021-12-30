package com.example.person.service;


import com.example.person.dto.PersonDTO;
import com.example.person.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPersonService {

    List<PersonDTO> findAll();

    List<PersonDTO> findByName(String name);

    List<PersonDTO> findByLastName(String lastName);

    List<PersonDTO> findByAge(Integer age);

    PersonDTO findByPersonId(Integer id);

    boolean save(PersonDTO person);

    boolean saveAll(List<PersonDTO> persons);

    boolean deleteById(int id);

    PersonDTO updatePerson(int id, PersonDTO person);

    Person convertPerson(PersonDTO person);

    List<PersonDTO> convertListPerson(List<Person> persons);

    PersonDTO convertPersonDTO( Person person);

}
