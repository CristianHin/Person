package com.example.person.service;


import com.example.person.dto.PersonDTO;
import com.example.person.model.Person;
import com.example.person.repository.PersonDao;
import com.example.person.utils.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonImplements implements IPersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public List<PersonDTO> findAll() {
        Iterable<Person> persons = this.personDao.findAll();
        return this.convertListPerson((List<Person>) persons);
    }

    @Override
    public List<PersonDTO> findByName(String name) {
        List<Person> persons = personDao.findByNameContaining(name);
        return this.convertListPerson(persons);
    }

    @Override
    public List<PersonDTO> findByLastName(String lastName) {
        List<Person> persons = personDao.findByLastNameContaining(lastName);
        return this.convertListPerson(persons);
    }

    @Override
    public List<PersonDTO> findByAge(Integer age) {
        List<Person> persons = personDao.findByAgeGreaterThan(age);
        return this.convertListPerson(persons);
    }

    @Override
    public PersonDTO findByPersonId(Integer id) {
        Optional<Person> person = personDao.findById(id);
        return this.convertPersonDTO(person.get());
    }


    @Override
    public boolean save(PersonDTO personDTO) {
        Person personSave = this.convertPerson(personDTO);
        try {
            personDao.save(personSave);
            return true;
        }
        catch (Exception ex) {
            ex.getMessage();
            return false;
        }
    }

    @Override
    public boolean saveAll(List<PersonDTO> personsDTO) {
        try{
            for(PersonDTO person : personsDTO) {
                Person personSave = this.convertPerson(person);
                personDao.save(personSave);
            }

            return true;
        }catch (Exception e){
            e.getMessage();
            return false;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            personDao.deleteById(id);
            return true;
        }
        catch (Exception ex) {
            ex.getMessage();
            return false;
        }
    }

    @Override
    public PersonDTO updatePerson(int id, PersonDTO person) {
        Optional<Person> personDB = personDao.findById(id);

        if(person.getName() != null ) {
            personDB.get().setName(person.getName());
        }

        if(person.getLastName() != null ) {
            personDB.get().setLastName(person.getLastName());
        }

        if(person.getAge() != 0 ) {
            personDB.get().setAge(person.getAge());
        }
        if(person.getCity() != null ) {
            personDB.get().setCity(person.getCity());
        }

        personDB.get().setCreateAt(personDB.get().getCreateAt());

        return this.convertPersonDTO(personDB.get());
    }

    @Override
    public Person convertPerson(PersonDTO personDTO) {
        return Mapping.modelMapper().map(personDTO, Person.class);
    }

    @Override
    public List<PersonDTO> convertListPerson(List<Person> persons) {
        List<PersonDTO> personsDTO = new ArrayList<>();
        for(Person person : persons) {
            PersonDTO personDTO = this.convertPersonDTO(person);
            personsDTO.add(personDTO);
        }
        return personsDTO;
    }

    @Override
    public PersonDTO convertPersonDTO(Person person) {
        return Mapping.modelMapper().map(person, PersonDTO.class);
    }
}
