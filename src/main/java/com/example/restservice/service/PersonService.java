package com.example.restservice.service;

import com.example.restservice.dao.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonService {

    public List<Person> findAll(){
        List<Person> personList = new ArrayList<>();
        Person person = new Person("Juan", "Perez");
        personList.add(person);

        return personList;
    }


}
