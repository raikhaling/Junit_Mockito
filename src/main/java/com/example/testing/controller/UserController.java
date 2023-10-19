package com.example.testing.controller;

import com.example.testing.dto.UserDTO;
import com.example.testing.entity.User;
import com.example.testing.exception.UserNotFoundException;
import com.example.testing.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserService service;
    private final ModelMapper modelMapper;
    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid User user) {
        User persistedUser = service.add(user);
        URI uri = URI.create("/users/" +persistedUser.getId());
        return ResponseEntity.created(uri).body(entityToDto(persistedUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") @Positive Long id) {
        try {
            User user = service.get(id);
            return ResponseEntity.ok(entityToDto(user));

        } catch (UserNotFoundException e) {

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> list() {
        List<User> userList = service.list();
        if (userList.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(listToDto(userList));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid User user,
                                    @PathVariable @Positive Long id){
        try {
            user.setId(id);
            User updatedUser = service.update(user);
            return ResponseEntity.ok(entityToDto(updatedUser));

        } catch (Exception e) {

            log.info("{}",e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @Positive Long id) {

        try {
            service.delete(id);
            return ResponseEntity.noContent().build();

        } catch (UserNotFoundException e) {

            return ResponseEntity.notFound().build();
        }
    }

    private UserDTO entityToDto(User entity) {
        return modelMapper.map(entity, UserDTO.class);
    }

    private List<UserDTO> listToDto(List<User> listUsers) {
        return listUsers.stream().map(
                        entity -> entityToDto(entity))
                .collect(Collectors.toList());
    }
}
