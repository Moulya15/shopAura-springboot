package com.example.spring_api.Repo;

import com.example.spring_api.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<ProductEntity,Integer> {
    ProductEntity findByName(String name);
}
