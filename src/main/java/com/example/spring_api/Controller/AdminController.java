package com.example.spring_api.Controller;

import com.example.spring_api.Entity.Admin;
import com.example.spring_api.Entity.User;
import com.example.spring_api.Repo.AdminRepo;
import com.example.spring_api.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    public AdminRepo adminRepo;

    //admin will never have a registration page so it should be manually saved in the database

    @PostMapping("/Login")
    public ResponseEntity<?> userLogin(@RequestBody Admin user){
        var checkUser=adminRepo.findByMobile(user.getMobile()).orElseThrow(()-> new RuntimeException("User not found"));

        if(!checkUser.getPassword().equals(user.getPassword())){
            throw new RuntimeException("Invalid Password");
        }
        return ResponseEntity.ok(checkUser.getName());
    }

//    @GetMapping("/adminlogin/{mobile}/{password}")
//    public ResponseEntity<?> adminlogin(@PathVariable String mobile,@PathVariable String password)
//    {
//        Optional<Admin> adminOptional =adminRepo.findByMobile(mobile);
//        if(!adminOptional.isPresent())
//        {
//            return new ResponseEntity<>("Mobile is incorrect",HttpStatus.NOT_FOUND);
//        }
//        else
//        {
//            var admininfo=adminOptional.get();
//            if(admininfo.getPassword().equals(password))
//            {
//                return new ResponseEntity<>("Login Successfull",HttpStatus.OK);
//            }
//            else {
//                return new ResponseEntity<>("Password not found",HttpStatus.CONFLICT);
//            }
//        }
//    }
}
