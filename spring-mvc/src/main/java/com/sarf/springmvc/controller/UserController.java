package com.sarf.springmvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.sarf.springmvc.error.UserAlreadyExistsException;
import com.sarf.springmvc.error.UserNotFoundException;
import com.sarf.springmvc.model.User;
import com.sarf.springmvc.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userRepo
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id, "retrieval")));
    }

    @PostMapping(path = "/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        if (userRepo.isExistsByUserName(user.getUserName())) {
            throw new UserAlreadyExistsException(user.getUserName());
        }
        return ResponseEntity.ok(userRepo.save(user));
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) {
        return ResponseEntity.ok(userRepo.save(user, id));
    }

    @PatchMapping(path = "/update/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<User> partiallyUpdateUser(@RequestBody JsonPatch user, @PathVariable Long id) {

        try {
            return ResponseEntity.ok(userRepo.update(user, id));
        } catch (JsonPatchException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Boolean> deletePost(@PathVariable Long id) {
        return ResponseEntity.ok(userRepo.deleteById(id));
    }
}
