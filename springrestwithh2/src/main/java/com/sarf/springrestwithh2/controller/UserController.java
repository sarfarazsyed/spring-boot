package com.sarf.springrestwithh2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.sarf.springrestwithh2.UserEntity;
import com.sarf.springrestwithh2.error.UserAlreadyExistsException;
import com.sarf.springrestwithh2.error.UserNotFoundException;
import com.sarf.springrestwithh2.model.User;
import com.sarf.springrestwithh2.repo.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User response = new User();
        BeanUtils.copyProperties(userRepo
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id, "retrieval")), response);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        if (userRepo.existsByUserName(user.getUserName())) {
            throw new UserAlreadyExistsException(user.getUserName());
        }
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        BeanUtils.copyProperties(userRepo.save(userEntity), user);
        return ResponseEntity.ok(user);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User modifiedUser, @PathVariable Long id) {
        UserEntity actualUser = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id, "Update"));
        BeanUtils.copyProperties(modifiedUser, actualUser, "id");
        BeanUtils.copyProperties(userRepo.save(actualUser), modifiedUser);
        return ResponseEntity.ok(modifiedUser);
    }

    @PatchMapping(path = "/update/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<User> partiallyUpdateUser(@RequestBody JsonPatch userPatch, @PathVariable Long id) {
        try {
            UserEntity user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id, "Patch Update"));
            JsonNode patched = userPatch.apply(objectMapper.convertValue(user, JsonNode.class));
            user = userRepo.save(objectMapper.treeToValue(patched, UserEntity.class));
            User updatedUser = new User();
            BeanUtils.copyProperties(user, updatedUser);
            return ResponseEntity.ok(updatedUser);
        } catch (JsonPatchException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deletePost(@PathVariable Long id) {
        userRepo.deleteById(id);
    }
}
