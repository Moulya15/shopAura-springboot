package com.example.spring_api.Repo;

import com.example.spring_api.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart,Integer> {

    List<Cart> findByUserId(Integer userId);

    Optional<Cart> findByUserIdAndProductId(Integer userId, Integer productId);
}
