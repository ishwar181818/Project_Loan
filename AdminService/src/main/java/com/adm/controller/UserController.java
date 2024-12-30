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
import java.util.List;

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
    
    @GetMapping("/verify/{username}/{password}")
    public ResponseEntity<User> getUserByCredentials(@PathVariable String username, @PathVariable String password) {
        // Call the service method to get user by username and password
        User user = userService.getUserByUsernameAndPassword(username, password);
        
        System.out.println(user.getUsername()+user.getPassword());
        
        return new ResponseEntity<User>(user, HttpStatus.OK); 
    }
    
    
    @GetMapping("/getAll")
	public ResponseEntity<List<User>> getAllEmployee()
	
	{
		
		
		
	   List<User>list	= userService.getAllEmployee();
	    
		
		
		
		
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
		
	}
    
    
    @GetMapping("/get/{userid}")
	public ResponseEntity<User> getEmployee(@PathVariable int userid)
	
	{
		
		
		
	    User user	=userService.getUser(userid);
	   
		
		;
		
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}
    
    
    
    @PutMapping("/update/{userid}")
    public ResponseEntity<User> updateEmployee(
            @PathVariable int userid, // Employee ID from URL
            @RequestPart("info") String json, // Employee details in JSON format
            @RequestPart("empImage") MultipartFile empImage, // Pancard file
            @RequestPart("empPancard") MultipartFile empPancard) { // Aadharcard file

        try {
            // Delegate the processing of the request to the service
            User updatedEmployee = userService.updateEmployee(userid, json, empImage, empPancard);

            // Return the updated employee in the response with a 200 OK status
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } catch (Exception e) {
            // Handle errors (e.g., invalid data or file handling)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }




 }
    
    @DeleteMapping("/del/{userid}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int userid) {
        // Try to delete the employee
        try {
            // Call the service method to delete the user
            userService.delUser(userid);

            // If the deletion is successful, return 204 No Content
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            // If any exception occurs (e.g., employee not found), return 404 Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

       
    
    
    
    
    
    
    
}
