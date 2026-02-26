package com.example.spring_api.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name="productId")
    private ProductEntity product;
}
