package com.mohamed.movieticketsystem.controllers;

import com.mohamed.movieticketsystem.entities.RegistretedUser;
import com.mohamed.movieticketsystem.exceptions.ResourcesException;
import com.mohamed.movieticketsystem.repositories.UserRepository;
import org.hibernate.validator.constraints.LuhnCheck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
class RegistretedUserControllerTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private RegistretedUserController userController;
    RegistretedUser user= Mockito.mock(RegistretedUser.class);
    List<RegistretedUser>userList=new ArrayList<>();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        user.setName("Sohil");
        user.setUserName("MohamedSohil85");
        user.setAddress("Darmstadt");
        user.setAge(32);
        user.setPassword("mM185%S4&7mS");
        user.setUserId(1L);
        userList.add(user);

    }

    @Test
    void getUsers() throws ResourcesException {
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        assertThat(userController.getUsers(),is(userList));
    }

    @Test
    void getUserById() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        assertThat(userController.getUserById(1L),is(user));

    }


}