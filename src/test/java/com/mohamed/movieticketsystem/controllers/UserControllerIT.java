package com.mohamed.movieticketsystem.controllers;

import com.mohamed.movieticketsystem.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getUsers() throws Exception {
        String payload="[{\n" +
                "    \"userId\":1,\n" +
                "    \"name\":\"Mohamed\",\n" +
                "    \"userName\":\"Mimo85\",\n" +
                "    \"Address\":\"Darmstadt\",\n" +
                "    \"age\":35,\n" +
                "    \"password\":\"mM85dahuw%\"\n" +
                "\n" +
                "}]";
        mockMvc.perform(MockMvcRequestBuilders.get("/getUsers")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json(payload));
    }

    @Test
    void getUserById() throws Exception {
        String payload="{\n" +
                "    \"userId\":1,\n" +
                "    \"name\":\"Mohamed\",\n" +
                "    \"userName\":\"Mimo85\",\n" +
                "    \"Address\":\"Darmstadt\",\n" +
                "    \"age\":35,\n" +
                "    \"password\":\"mM85dahuw%\"\n" +
                "\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.get("/User/"+1)
                .header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(payload)).andReturn();

    }

    @Test
    void saveNewUser() throws Exception {
        String payload="{\n" +
                "    \"userId\":1,\n" +
                "    \"name\":\"Mohamed\",\n" +
                "    \"userName\":\"Mimo85\",\n" +
                "    \"Address\":\"Darmstadt\",\n" +
                "    \"age\":35,\n" +
                "    \"password\":\"mM85dahuw%\"\n" +
                "\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/saveNewUser")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(payload))
                .andDo(MockMvcResultHandlers.print());
    }
}