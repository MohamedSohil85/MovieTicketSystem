package com.mohamed.movieticketsystem.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohamed.movieticketsystem.entities.RegistretedUser;
import com.mohamed.movieticketsystem.repositories.UserRepository;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class RegistretedUserRestControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private UserRepository userRepository;
    private static final ObjectMapper om = new ObjectMapper();
    private List<RegistretedUser>userList=new ArrayList<>();
    private RegistretedUser user;
    @BeforeEach
    void setUp() {

        user=new RegistretedUser();
        user.setName("Sohil");
        user.setUserName("MohamedSohil85");
        user.setAddress("Darmstadt");
        user.setAge(32);
        user.setPassword("mM185%S4&7mS");
        user.setUserId(1L);
        userList.add(user);
    }

    @Test
    void getUsers() throws JsonProcessingException, JSONException {
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        String expected=om.writeValueAsString(userList);
        ResponseEntity<String> responseEntity=restTemplate.getForEntity("/getUsers",String.class);
        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Assertions.assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        JSONAssert.assertEquals(expected, responseEntity.getBody(), false);
    }

    @Test
    void getUserById() throws JsonProcessingException, JSONException {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        String expected= om.writeValueAsString(user);
        ResponseEntity<String>responseEntity=restTemplate.getForEntity("/User/1",String.class);
        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Assertions.assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        JSONAssert.assertEquals(expected, responseEntity.getBody(), false);
    }

    @Test
    void saveNewUser() throws JSONException, JsonProcessingException {
        RegistretedUser user2=new RegistretedUser();
        user2.setName("Mohamed");
        user2.setUserName("MohamedSohil85");
        user2.setAddress("Darmstadt");
        user2.setAge(32);
        user2.setPassword("mM185%S4&7mS");
        user2.setUserId(1L);


        String expected=om.writeValueAsString(user2);
        Mockito.when(userRepository.save(user)).thenReturn(user2);
        ResponseEntity<String>responseEntity=restTemplate.postForEntity("/saveNewUser",user2,String.class);
        Assertions.assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());


    }
}