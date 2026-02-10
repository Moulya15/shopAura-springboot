package com.example.spring_api.Repo;

import com.example.spring_api.Entity.Admin;
import com.example.spring_api.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin,Integer> {
    boolean existsByMobile(String mobile);

    Optional<Admin> findByMobile(String mobile);
}
