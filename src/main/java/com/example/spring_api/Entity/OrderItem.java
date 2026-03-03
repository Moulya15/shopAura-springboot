package com.example.spring_api.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantity;
    private double price;

    @ManyToOne
    @JoinColumn(name="order_id")
    @JsonIgnore
    private Orders order;

    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductEntity product;
}
