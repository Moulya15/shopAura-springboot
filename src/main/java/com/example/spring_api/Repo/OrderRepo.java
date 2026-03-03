package com.example.spring_api.Repo;

import com.example.spring_api.Entity.Orders;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Orders,Integer> {
    List<Orders> findByUserId(Integer userId);
}
