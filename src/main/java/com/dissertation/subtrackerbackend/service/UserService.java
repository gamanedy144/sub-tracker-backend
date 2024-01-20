package com.dissertation.subtrackerbackend.service;

import com.dissertation.subtrackerbackend.domain.User;
import com.dissertation.subtrackerbackend.domain.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> fetchAllUsers();

    UserDTO fetchUserById(long id);

    List<UserDTO> saveMultipleUsers(List<User> userList);

    UserDTO saveUser(User user);

    UserDTO updateUser(UserDTO userDto);

    void delete(long id);

    UserDTO findDtoByEmail(String email);

    User findByEmail(String email);
    User getCurrentUser();
}
