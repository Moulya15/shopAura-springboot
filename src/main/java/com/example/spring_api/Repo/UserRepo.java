package com.example.spring_api.Repo;

import com.example.spring_api.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {

    boolean existsByMobile(String mobile);

    Optional<User> findByMobile(String mobile);
}
