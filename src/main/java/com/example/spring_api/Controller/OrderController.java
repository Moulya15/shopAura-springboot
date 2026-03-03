package com.example.spring_api.Controller;

import com.example.spring_api.Entity.Cart;
import com.example.spring_api.Entity.OrderItem;
import com.example.spring_api.Entity.Orders;
import com.example.spring_api.Repo.CartRepo;
import com.example.spring_api.Repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/order")
@CrossOrigin("*")
@RestController
public class OrderController {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private OrderRepo orderRepo;

    @PostMapping("/checkout/{userId}")
    public ResponseEntity<?> checkout(@PathVariable Integer userId){
        List<Cart> cartItems=cartRepo.findByUserId(userId);

        if(cartItems.isEmpty()){
            return ResponseEntity.badRequest().body("Cart is empty");
        }

        Orders order=new Orders();
        order.setUserId(userId);
        order.setOrderDate(LocalDateTime.now());

        List<OrderItem> orderItems=new ArrayList<>();
        double total=0;

        for(Cart cart:cartItems){
            OrderItem item=new OrderItem();
            item.setProduct(cart.getProduct());
            item.setQuantity(cart.getQuantity());
            item.setPrice(cart.getProduct().getPrice());
            item.setOrder(order);

            total+=cart.getProduct().getPrice()*cart.getQuantity();
            orderItems.add(item);
        }

        order.setItems(orderItems);
        order.setTotalAmount(total);

        orderRepo.save(order);

        cartRepo.deleteAll(cartItems);

        return ResponseEntity.ok("Order placed successfully");
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Orders>> getOrderHistory(@PathVariable Integer userId){

        List<Orders> orders= orderRepo.findByUserId(userId);
        return ResponseEntity.ok(orders);
    }
}
