package com.dissertation.subtrackerbackend.service.impl;

import com.dissertation.subtrackerbackend.domain.User;
import com.dissertation.subtrackerbackend.domain.dto.UserDTO;
import com.dissertation.subtrackerbackend.domain.mapper.UserMapper;
import com.dissertation.subtrackerbackend.repository.UserRepository;
import com.dissertation.subtrackerbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper mapper;
    @Override
    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User fetchUserById(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public List<User> saveMultipleUsers(List<User> subscriptionList) {
        return userRepository.saveAll(subscriptionList);
    }

    @Override
    public User saveUser(User subscription) {
        return userRepository.save(subscription);
    }

    @Override
    public User updateUser(UserDTO userDTO) {
        User userToBeSaved = new User();
        mapper.updateUserFromDto(userToBeSaved, userDTO);
        return userRepository.save(userToBeSaved);
    }

    @Override
    public void delete(long id){
        User temp = userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
    }
}
