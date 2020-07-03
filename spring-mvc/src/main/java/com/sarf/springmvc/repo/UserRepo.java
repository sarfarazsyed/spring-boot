package com.sarf.springmvc.repo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.sarf.springmvc.error.UserNotFoundException;
import com.sarf.springmvc.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepo {
    @Autowired
    private ObjectMapper objectMapper;
    private Map<Long, User> userStorage = new HashMap<>();

    public User save(User user) {
        userStorage.put(user.getId(), user);
        return user;
    }

    public User save(User modifiedUser, Long id) {
        User user = Optional.ofNullable(userStorage.get(id)).orElseThrow(() -> new UserNotFoundException(id, "Update"));
        BeanUtils.copyProperties(modifiedUser, user);
        return userStorage.replace(id, user);
    }

    public User update(JsonPatch userPatch, Long id) throws JsonPatchException, JsonProcessingException {
        User user = Optional.ofNullable(userStorage.get(id)).orElseThrow(() -> new UserNotFoundException(id, "Patch Update"));
        JsonNode patched = userPatch.apply(objectMapper.convertValue(user, JsonNode.class));
        return userStorage.replace(id, objectMapper.treeToValue(patched, User.class));
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userStorage.get(id));
    }

    public boolean isExistsByUserName(String userName) {
        return userStorage.values().stream().anyMatch(user -> user.getUserName().equalsIgnoreCase(userName));
    }

    public boolean deleteById(Long id) {
        try {
            userStorage.remove(id);
            return true;
        } catch (UnsupportedOperationException u ) {
            throw new UserNotFoundException(id, "Delete");
        }
    }
}
