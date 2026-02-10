package com.example.spring_api.Controller;

import com.example.spring_api.Entity.User;
import com.example.spring_api.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/users")

public class UserController {

    @Autowired
    public UserRepo userRepo;

    @PostMapping("/UserRegistration")
    public ResponseEntity<?> userRegistration(@RequestBody User user){
        if (userRepo.existsByMobile(user.getMobile())){
            throw new RuntimeException("Phone number already exists");
        }
        userRepo.save(user);
        return new ResponseEntity<>("User registration is successful", HttpStatus.OK);
    }

    @PostMapping("/Login")
    public ResponseEntity<?> userLogin(@RequestBody User user){
        var checkUser=userRepo.findByMobile(user.getMobile()).orElseThrow(()-> new RuntimeException("User not found"));

        if(!checkUser.getPassword().equals(user.getPassword())){
            throw new RuntimeException("Invalid Password");
        }
        return ResponseEntity.ok(checkUser.getName());
    }

    @GetMapping("/GetUsersByMobile/{mobile}")
    public ResponseEntity<?> getUser(@PathVariable String mobile){
        var checkUser=userRepo.findByMobile(mobile).orElseThrow(()-> new RuntimeException("User not found"));
        return new ResponseEntity<>(checkUser,HttpStatus.OK);
    }

    @PutMapping("/UpdateProfile")
    public ResponseEntity<?> updateProfile(@RequestBody User user){
        var checkUser=userRepo.findByMobile(user.getMobile()).orElseThrow(()-> new RuntimeException("User not found"));
        checkUser.setName(user.getName());
        checkUser.setPassword(user.getPassword());
        userRepo.save(checkUser);
        return new ResponseEntity<>("Updated successfully",HttpStatus.OK);
    }
}