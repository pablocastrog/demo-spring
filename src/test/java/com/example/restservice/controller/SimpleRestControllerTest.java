package com.example.restservice.controller;

import com.example.restservice.dao.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.http.HttpStatus;

class SimpleRestControllerTest {

    @Test
    @DisplayName("Simple message should work with a name")
    public void testSayHelloWhenNameIsProvided() {

        SimpleRestController simpleRestController = new SimpleRestController();
        Optional<String> name = Optional.of("Juan Perez");
        String expectedMessage = "Hello Juan Perez";

        String result = simpleRestController.hello(name);

        assertEquals(expectedMessage, result);
    }

    @Test
    @DisplayName("Simple message should work without name")
    public void testSayHelloWithoutMessage() {

        SimpleRestController simpleRestController = new SimpleRestController();
        Optional<String> name = Optional.empty();
        String expectedMessage = "Hello World";

        String result = simpleRestController.hello(name);

        assertEquals(expectedMessage, result);
    }
    @Test
    @DisplayName("Return bad request when does not provide fullname")
    public void badRequestWhenDoesNotProvideFullname() {

        SimpleRestController simpleRestController = new SimpleRestController();
        Person person = new Person("", "");

        ResponseEntity expectedResponse =  new ResponseEntity<>(
                "You must provide full name",
                HttpStatus.BAD_REQUEST);

        ResponseEntity result = simpleRestController.greetings(person);

        assertEquals(expectedResponse, result);
    }

    @Test
    @DisplayName("Return ok status when provide the fullname")
    public void returnOkWhenProvideFullname() {

        SimpleRestController simpleRestController = new SimpleRestController();
        Person person = new Person("Juan", "Perez");

        ResponseEntity expectedResponse =  new ResponseEntity<>(
                "Hello " + person.getFirstName() + " " + person.getLastName(),
                HttpStatus.OK);

        ResponseEntity result = simpleRestController.greetings(person);

        assertEquals(expectedResponse, result);
    }
}