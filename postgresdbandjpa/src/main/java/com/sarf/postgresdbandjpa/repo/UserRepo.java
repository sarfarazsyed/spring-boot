package com.sarf.postgresdbandjpa.repo;


import com.sarf.postgresdbandjpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    boolean existsByUserName(String userName);
}