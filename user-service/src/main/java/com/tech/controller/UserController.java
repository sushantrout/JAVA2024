package com.tech.controller;

import com.tech.dto.UserDTO;
import com.tech.entity.ApplicationUser;
import com.tech.mapper.UserMapper;
import com.tech.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        log.info("Getting all users");
        return userService.getAllUsers().stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(userMapper::toDTO)
                .orElse(null);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        ApplicationUser user = userMapper.toEntity(userDTO);
        ApplicationUser createdUser = userService.createUser(user);
        return userMapper.toDTO(createdUser);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        ApplicationUser user = userMapper.toEntity(userDTO);
        user.setId(id);
        ApplicationUser updatedUser = userService.updateUser(id, user);
        return userMapper.toDTO(updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
