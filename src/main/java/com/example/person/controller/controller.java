package com.example.person.controller;

import com.example.person.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

    @Autowired
    private IPersonService personService;

    @GetMapping(value = {"","/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPerson(){

        return  ResponseEntity.ok(personService.findAll());
    }
}
