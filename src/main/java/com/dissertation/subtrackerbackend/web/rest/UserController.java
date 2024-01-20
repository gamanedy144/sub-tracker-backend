package com.dissertation.subtrackerbackend.web.rest;

import com.dissertation.subtrackerbackend.domain.User;
import com.dissertation.subtrackerbackend.domain.dto.UserDTO;
import com.dissertation.subtrackerbackend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<List<UserDTO>> fetchAllUsers(){
        return ResponseEntity.ok(userService.fetchAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> fetchUserById(@PathVariable long id){
        return ResponseEntity.ok(userService.fetchUserById(id));
    }
    @PostMapping("/multiple")
    public ResponseEntity<List<UserDTO>> saveMultipleUsers(@RequestBody List<User> userList){
        return ResponseEntity.ok(userService.saveMultipleUsers(userList));
    }
    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody User user){
        return ResponseEntity.ok(userService.saveUser(user));
    }
    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.updateUser(userDTO));
    }
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable long id){
        userService.delete(id);
    }

    @GetMapping("/find-by-email/{email}")
    public ResponseEntity<UserDTO> fetchUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.findDtoByEmail(email));
    }

}
