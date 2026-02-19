package com.example.spring_api.Repo;

import com.example.spring_api.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepo extends JpaRepository<Cart,Integer> {

    List<Cart> findByUserId(Integer userId);

    Cart findByUserIdAndProductId(Integer userId, Integer productId);
}
