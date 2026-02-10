package com.example.spring_api.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price;
    private String description;
    @Column(columnDefinition = "LONGTEXT")//required only for base64
    private String image;

}
