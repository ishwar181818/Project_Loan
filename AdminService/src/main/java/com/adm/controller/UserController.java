package com.adm.controller;

import com.adm.model.User;
import com.adm.servicei.ServiceI;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/adm")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private ServiceI userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(
            @RequestPart("info") String json,
            @RequestPart("empImage") MultipartFile empImage,
            @RequestPart("empPancard") MultipartFile empPancard) {

        try {
            // Use ObjectMapper to convert JSON to User object
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(json, User.class);

            // Generate random username and password
            String username = generateUsername(user.getFirstName());
            String password = generatePassword(user.getFirstName());

            // Set generated values to User
            user.setUsername(username);
            user.setPassword(password);
            user.setEmployeeImage(empImage.getBytes());
            user.setEmployeePancard(empPancard.getBytes());

            // Save the user to the database
            userService.saveUser(user);

            return new ResponseEntity<>("User added successfully with username: " + username, HttpStatus.CREATED);

        } catch (IOException e) {
            return new ResponseEntity<>("Error processing the data: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private String generateUsername(String firstName) {
        int randomNo = (int) (Math.random() * 1000); // Random number
        return firstName  + randomNo;
    }

    private String generatePassword(String firstName) {
        int randomNo = (int) (Math.random() * 1000); // Random number
        return firstName + "@" + randomNo;
    }
    
    @GetMapping("/get/{username}/{password}")
    public ResponseEntity<?> getUserByCredentials(@PathVariable String username, @PathVariable String password) {
        // Call the service method to get user by username and password
        User user = userService.getUserByUsernameAndPassword(username, password);
        
        System.out.println(user.getUsername()+user.getPassword());
        
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);  // User found
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);  // User not found
        }
    }
    
    
    
    
    
    
    
}
