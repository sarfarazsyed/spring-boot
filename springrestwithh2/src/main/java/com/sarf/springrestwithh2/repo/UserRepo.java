package com.sarf.springrestwithh2.repo;

import com.sarf.springrestwithh2.UserEntity;
import com.sarf.springrestwithh2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<UserEntity, Long> {
    boolean existsByUserName(String userName);
}