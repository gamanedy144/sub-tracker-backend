package com.dissertation.subtrackerbackend.service.impl;

import com.dissertation.subtrackerbackend.domain.User;
import com.dissertation.subtrackerbackend.domain.dto.UserDTO;
import com.dissertation.subtrackerbackend.domain.mapper.UserMapper;
import com.dissertation.subtrackerbackend.repository.UserRepository;
import com.dissertation.subtrackerbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper mapper;
    @Override
    public List<UserDTO> fetchAllUsers() {
        return userRepository.findAll().stream().map(user -> mapper.toDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDTO fetchUserById(long id) {
        return mapper.toDto(userRepository.findById(id).orElseThrow());
    }

    @Override
    public List<UserDTO> saveMultipleUsers(List<User> subscriptionList) {
        return userRepository.saveAll(subscriptionList).stream().map(user -> mapper.toDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDTO saveUser(User subscription) {
        return mapper.toDto(userRepository.save(subscription));
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User userToBeSaved = new User();
        mapper.updateUserFromDto(userToBeSaved, userDTO);
        return mapper.toDto(userRepository.save(userToBeSaved));
    }

    @Override
    public void delete(long id){
        User temp = userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
    }
}
