package com.example.restservice.controller;

import com.example.restservice.dao.Person;
import com.example.restservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SimpleRestController {

    @Autowired
    PersonService personService;
    @GetMapping("/hello")
    public String hello(@RequestParam(required = false) Optional<String> name){
        String paramValue = name.orElse("");

        return !paramValue.isBlank() ? "Hello " + name.orElse(null): "Hello World";
    }

    @PostMapping("/greetings")
    public ResponseEntity<String> greetings(@RequestBody Person person){

        if(person.getFirstName().isBlank() || person.getLastName().isBlank()) {
            return new ResponseEntity<>(
                    "You must provide full name",
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                "Hello " + person.getFirstName() + " " + person.getLastName(),
                HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Person>> findAll(){
        List<Person> listPerson = personService.findAll();

        return new ResponseEntity<>(listPerson, HttpStatus.OK);
    }
}
