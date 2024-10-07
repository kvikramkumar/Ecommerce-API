package com.ecommerce.app.controller;

import com.ecommerce.app.dto.LoginRequest;
import com.ecommerce.app.dto.UserDTO;
import com.ecommerce.app.model.User;
import com.ecommerce.app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/users/signup",method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@Valid @RequestBody User user){
        try {
            userService.createUser(user);
            return new ResponseEntity<>("User created successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/users/login",method = RequestMethod.POST)
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest){

        boolean validUser = userService.validateUser(loginRequest);

        if(validUser){
            return ResponseEntity.ok("Login Successful");
        }
        else{
            return ResponseEntity.status(401).body("Invalid Email or password");
        }
    }

    @RequestMapping(value = "/api/users/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getUserDetails(@PathVariable("id") Long userID){
        UserDTO userDTO = userService.getUserDetails(userID);
        if(userDTO != null)
            return ResponseEntity.status(200).body(userDTO);
        else
            return ResponseEntity.status(404).body("User Not Found");
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.PUT)
    public ResponseEntity<String> updateUserDetails(@RequestBody User user){

        boolean updated = userService.updateUserDetails(user);
        if(updated)
            return ResponseEntity.status(200).body("User Details were updated");
        else
           return ResponseEntity.status(404).body("No such user found");
    }
}
