package com.example.spring_api.Controller;
import com.example.spring_api.Entity.Cart;
import com.example.spring_api.Repo.CartRepo;
import com.example.spring_api.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/cart")
@CrossOrigin("*")
@RestController
public class CartController {

    @Autowired CartRepo cartRepo;

    @PostMapping("/addCart")
    public ResponseEntity<?> addToCart(@RequestBody Cart cart){
        Integer userId=cart.getUserId();
        Integer productId=cart.getProduct().getId();

        Optional<Cart> existing=cartRepo.findByUserIdAndProductId(userId, productId);
        if(existing.isPresent()){
            Cart existingCart=existing.get();
            existingCart.setQuantity(existingCart.getQuantity()+cart.getQuantity());
            cartRepo.save(existingCart);
            return ResponseEntity.ok("Quantity updated in cart");
        }

        cartRepo.save(cart);
        return ResponseEntity.ok("Cart added successfully");
    }

    @GetMapping("/getCart/{userId}")
    public ResponseEntity<List<Cart>> getCart(@PathVariable Integer userId){

        List<Cart> cartList= cartRepo.findByUserId(userId);
        return new ResponseEntity<>(cartList,HttpStatus.OK);
    }

    @PutMapping("/updateQuantity/{cartId}")
    public ResponseEntity<?> updateQuantity(@PathVariable Integer cartId, @RequestBody Cart updatedCart){
        Optional<Cart> optionalCart = cartRepo.findById(cartId);

        if(optionalCart.isEmpty()){
            return new ResponseEntity<>("Cart item not found",HttpStatus.NOT_FOUND);
        }
        Cart cart=optionalCart.get();

        if(updatedCart.getQuantity()<=0){
            cartRepo.delete(cart);
            return new ResponseEntity<>("Item removed from cart",HttpStatus.OK);
        }

        cart.setQuantity(updatedCart.getQuantity());
        cartRepo.save(cart);

        return new ResponseEntity<>("Quantity updated successfully ",HttpStatus.OK);
    }

    @DeleteMapping("/deleteCart/{cartId}")
    public ResponseEntity<?> deleteCart(@PathVariable Integer cartId){
        Optional<Cart> optionalCart=cartRepo.findById(cartId);
        if(optionalCart.isEmpty()){
            return new ResponseEntity<>("Cart Item not found",HttpStatus.NOT_FOUND);
        }
        cartRepo.delete(optionalCart.get());

        return new ResponseEntity<>("Item deleted successfully", HttpStatus.OK);
    }
}
