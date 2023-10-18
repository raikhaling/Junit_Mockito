package com.example.testing.controller;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.example.testing.entity.User;
import com.example.testing.exception.UserNotFoundException;
import com.example.testing.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@Slf4j
class UserControllerTest {
    private static final String END_POINT_PATH ="/users";
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean
    private UserServiceImpl userService;

    @Test
    void addShouldReturn400BadRequest() throws Exception {
        log.info("Running add empty user test case ");
        User newUser = User.builder().firstName("").lastName("").email("").build();

        String requestBody = objectMapper.writeValueAsString(newUser);

        mockMvc.perform(post(END_POINT_PATH).contentType("application/json")
                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andDo(print());
        log.info("Test passed successfully");
    }
    @Test
    void addShouldReturn201Created() throws Exception {
        log.info("Running add user test case ");

        String email = "ram@gmail.com";
        User user = User.builder()
                .firstName("Ram")
                .lastName("Bhandari")
                .email(email)
                .password("bhandari")
                .build();

        User userWithId = User.builder()
                .id(1L)
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

        String requestBody = objectMapper.writeValueAsString(user);

        Mockito.when(userService.add(Mockito.any())).thenReturn(userWithId);

        mockMvc.perform(post(END_POINT_PATH).contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is("/users/1")))
                .andExpect(jsonPath("$.email", is(email)))
                .andExpect(jsonPath("$.password").doesNotExist())
                .andDo(print());
        log.info("Test passed successfully");
    }

    @Test
    void testGetShouldReturn404NotFound() throws Exception {
        Long userId = 12323L;
        String reqeustURI = END_POINT_PATH + "/" + userId;

        Mockito.when(userService.get(userId)).thenThrow(UserNotFoundException.class);

        mockMvc.perform(get(reqeustURI).contentType("application/json"))
                .andExpect(status().isNotFound());

    }

    @Test
    void testGetShouldReturn200OK() throws Exception {
        log.info("Running test to get a specific user");
        User user = User.builder()
                .id(1L)
                .firstName("Ram")
                .lastName("Bhandari")
                .email("email@gmail.com")
                .password("bhandari")
                .build();

        String reqeustURI = END_POINT_PATH + "/" + user.getId();

        Mockito.when(userService.get(Mockito.anyLong())).thenReturn(user);

        mockMvc.perform(get(reqeustURI).contentType("application/json"))
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email",is(user.getEmail())))
                .andExpect(jsonPath("$.password").doesNotExist())
                .andDo(print());

        log.info("Test passed successfully");
    }

    @Test
    void testListShouldReturn204NoContent() throws Exception {

        Mockito.when(userService.list()).thenReturn(new ArrayList<>());

        mockMvc.perform(get(END_POINT_PATH))
                .andExpect(status().isNoContent())
                .andDo(print());

        Mockito.verify(userService, Mockito.times(1)).list();
    }
    @Test
    void testListShouldReturn200OK() throws Exception {
        log.info("Running get-all-users Test");
        String email = "ram@gmail.com";
        User user1 = User.builder()
                .firstName("Ram")
                .lastName("Bhandari")
                .email(email)
                .password("bhandari")
                .build();

        String firstName = "Sam";
        String lastName = "Bhandari";
        User user2 = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email("sam@gmail.com")
                .password("bhandari")
                .build();
        List<User> list = List.of(user1, user2);

        Mockito.when(userService.list()).thenReturn(list);

        mockMvc.perform(get(END_POINT_PATH))
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email",is(email)))//first users
                .andExpect(jsonPath("$[1].firstName", is(firstName))) //second user
                .andExpect(jsonPath("$[1].lastName", is(lastName))) //second user
                .andDo(print());
        log.info("Test passed successfully");
    }

    @Test
    void testPutShouldReturn404NotFound() throws Exception {
        log.info("Test updating a user started.");

        Long userId = 123L;
        String requestURI = END_POINT_PATH + "/" + userId;

        User user = User.builder()
                .id(userId)
                .firstName("")
                .lastName("")
                .email("")
                .password("")
                .build();

        String requestBody = objectMapper.writeValueAsString(user);

      //  Mockito.when(userService.update(user)).thenThrow(UserNotFoundException.class);

        mockMvc.perform(put(requestURI).contentType("application/json")
                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andDo(print());

        log.info("Test successfully passed.");

    }
    @Test
    void testPutShouldReturn400BadRequest() throws Exception {
        log.info("Test updating a user started.");

        Long userId = 123L;
        String requestURI = END_POINT_PATH + "/" + userId;

        User user = User.builder()
                .id(userId)
                .firstName("firstName")
                .lastName("lastName")
                .email("sam@gmail.com")
                .password("bhandari")
                .build();

        String requestBody = objectMapper.writeValueAsString(user);

        Mockito.when(userService.update(user)).thenThrow(UserNotFoundException.class);

        mockMvc.perform(put(requestURI).contentType("application/json").content(requestBody))
                .andExpect(status().isNotFound())
                .andDo(print());

        log.info("Test successfully passed.");

    }
    @Test
    void testPutShouldReturn200OK() throws Exception {
        log.info("Test updating a user started.");

        Long userId = 123L;
        String requestURI = END_POINT_PATH + "/" + userId;

        User user = User.builder()
                .id(userId)
                .firstName("ram")
                .lastName("sam")
                .email("ramsam@gmail.com")
                .password("bhandari")
                .build();

        String email = user.getEmail();

        String requestBody = objectMapper.writeValueAsString(user);

        Mockito.when(userService.update(Mockito.any())).thenReturn(user);

        mockMvc.perform(put(requestURI).contentType("application/json").content(requestBody))
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email",is(email)))
                .andExpect(jsonPath(".$password").doesNotExist())
                .andDo(print());

        log.info("Test successfully passed.");

    }

    @Test
    void delete() {
    }
}