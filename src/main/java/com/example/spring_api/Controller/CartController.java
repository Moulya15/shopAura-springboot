package com.example.spring_api.Controller;
import com.example.spring_api.Entity.Cart;
import com.example.spring_api.Repo.CartRepo;
import com.example.spring_api.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cart")
@CrossOrigin("*")
@RestController
public class CartController {

    @Autowired CartRepo cartRepo;

    @PostMapping("/addCart")
    public ResponseEntity<?> addToCart(@RequestBody Cart cart){
        Cart existing = cartRepo.findByUserIdAndProductId(
                cart.getUserId(),
                cart.getProduct().getId());

        if(existing != null){
            existing.setQuantity(existing.getQuantity()+ cart.getQuantity());
            cartRepo.save(existing);
            return new ResponseEntity<>("Quantity updated in cart", HttpStatus.OK);
        }

        cartRepo.save(cart);
        return new ResponseEntity<>("Cart added successfully", HttpStatus.OK);
    }

    @GetMapping("/getCart/{userId}")
    public ResponseEntity<?> getCart(@PathVariable Integer userId){

        List<Cart> cartList= cartRepo.findByUserId(userId);
        if(cartList.isEmpty()){
            return new ResponseEntity<>("Cart is Empty", HttpStatus.OK);
        }
        return new ResponseEntity<>(cartList,HttpStatus.OK);
    }
}
