package com.example.spring_api.Controller;

import com.example.spring_api.Repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/cart")
@CrossOrigin("*")
@RestController
public class CartController {

    @Autowired CartRepo cartRepo;

    @PostMapping("putProducts/{id}")
    public ResponseEntity<?> putProducts(@PathVariable Integer id){
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }
}
