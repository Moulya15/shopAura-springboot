package com.example.spring_api.Repo;

import com.example.spring_api.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart,Integer> {

}
