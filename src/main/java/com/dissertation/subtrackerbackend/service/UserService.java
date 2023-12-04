package com.dissertation.subtrackerbackend.service;

import com.dissertation.subtrackerbackend.domain.User;
import com.dissertation.subtrackerbackend.domain.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<User> fetchAllUsers();

    User fetchUserById(long id);

    List<User> saveMultipleUsers(List<User> userList);

    User saveUser(User user);

    User updateUser(UserDTO userDto);

    void delete(long id);
}
