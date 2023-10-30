package com.example.testing.service;

import com.example.testing.entity.User;
import com.example.testing.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
     User add(User user);
     User update(User user) throws UserNotFoundException;

     User get(Long id) throws UserNotFoundException;

     List<User> list();
     void delete(Long id) throws UserNotFoundException;

     List<User> searchUserByName(String keyword);
}
