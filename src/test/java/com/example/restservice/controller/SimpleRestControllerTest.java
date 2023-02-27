package com.example.restservice.controller;

import com.example.restservice.dao.Person;
import com.example.restservice.service.PersonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class SimpleRestControllerTest {

    @InjectMocks
    SimpleRestController simpleRestController;
    @Mock
    PersonService personService;
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

    @Test
    @DisplayName("example test service")
    public void testControllerCallService1() {
        //Arrange
        Person person1 = new Person("Juan" , "Perez");
        Person person2 = new Person("Luis" , "Rojas");
        List<Person> mockedList = new ArrayList<>();
        mockedList.add(person1);
        mockedList.add(person2);

        //Action
        when(personService.findAll()).thenReturn(mockedList);
        ResponseEntity<List<Person>> result = simpleRestController.findAll();

        //Assert
        assertThat(result.getBody().size()).isEqualTo(2);
        assertThat(result.getBody().get(0).getFirstName()).isEqualTo(person1.getFirstName());
        assertThat(result.getBody().get(0).getFirstName()).isEqualTo(person1.getFirstName());
        assertThat(result.getBody().get(1).getFirstName()).isEqualTo(person2.getFirstName());
    }




}