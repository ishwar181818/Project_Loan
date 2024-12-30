package com.adm.serviceimpl;

import com.adm.exception.InvalidCredentialsException;
import com.adm.model.User;
import com.adm.repo.UserRepository;
import com.adm.servicei.ServiceI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService implements ServiceI {
	
	@Value("${spring.mail.username}")
	private static String FROM_MAIL;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender emailSender; // Inject JavaMailSender to send emails

    public void saveUser(User user) {
        // Save the user to the database
        userRepository.save(user);

        // Create the email content directly here
        String subject = "Your Account Credentials";
        String text = "Hello " + user.getFirstName() + ",\n\n" +
                "Your account has been created successfully. Here are your credentials:\n" +
                "Username: " + user.getUsername() + "\n" +
                "Password: " + user.getPassword() + "\n\n" +
                "Regards,\n" +
                "Your Company";

        // Create SimpleMailMessage directly inside saveUser method
        SimpleMailMessage message = new SimpleMailMessage();
       message.setFrom(FROM_MAIL);
        message.setTo("yogtej.harbade@gmail.com"); // Set recipient email
        message.setSubject(subject);   // Set subject
        message.setText(text);         // Set email body text

        // Send the email
        emailSender.send(message); // Send the email directly
    }

	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		 User user= userRepository.findByUsernameAndPassword(username, password);
		 
		 if (user != null) {
			   return user;
			 } else {
			      throw new InvalidCredentialsException("Invalid username and password");
			  }
		
	}

	@Override
	public List<User> getAllEmployee() {
		
		
		
		return userRepository.findAll();
	}

	@Override
	public User getUser(int userid) {
		
		Optional<User>op=userRepository.findById(userid);
		
		if(op.isPresent())
			
		{
			
			User user=op.get();
			
			return user;
		}
		
		else
			
		{
			
			return null;
		}
		
		
	}

	@Override
	public User updateEmployee(int userid, String json, MultipartFile empImage, MultipartFile empPancard) {
		// Initialize ObjectMapper for JSON to Object mapping
	    ObjectMapper om = new ObjectMapper();
	    User updatedEmployee = null;

	    // Step 1: Parse the incoming JSON string into an Employee object
	    try {
	        updatedEmployee = om.readValue(json, User.class);
	    } catch (JsonMappingException e) {
	        // Handle mapping errors (e.g., invalid field names or data types)
	        e.printStackTrace();
	        return null; // Return null or throw exception as needed
	    } catch (JsonProcessingException e) {
	        // Handle JSON processing errors (e.g., malformed JSON)
	        e.printStackTrace();
	        return null; // Return null or throw exception as needed
	    }

	    // Step 2: Retrieve the existing employee from the database using the ID
	    Optional<User> existingEmployeeOpt = userRepository.findById(userid);
	    if (!existingEmployeeOpt.isPresent()) {
	        return null; // Employee not found, return null or handle it differently
	    }

	    // Step 3: Update the fields of the existing employee with the new values
	    User existingEmployee = existingEmployeeOpt.get();

	    // Update non-file fields
	    existingEmployee.setFirstName(updatedEmployee.getFirstName());
	    existingEmployee.setLastName(updatedEmployee.getLastName());
	    existingEmployee.setEmail(updatedEmployee.getEmail());
	    existingEmployee.setEmployeeSalary(updatedEmployee.getEmployeeSalary());
	    existingEmployee.setEmployeeAge(updatedEmployee.getEmployeeAge());
        existingEmployee.setUserType(updatedEmployee.getUserType());
	    // Step 4: Handle file updates (Aadhar card and Pancard)
	    if (empImage != null && !empImage.isEmpty()) {
	        try {
	            existingEmployee.setEmployeeImage(empImage.getBytes()); // Update Aadharcard
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    if (empPancard != null && !empPancard.isEmpty()) {
	        try {
	            existingEmployee.setEmployeePancard(empPancard.getBytes()); // Update Pancard
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    // Step 5: Save the updated employee back to the database
	    userRepository.save(existingEmployee);

	    // Step 6: Return the updated employee
	    return existingEmployee; // Optionally, return the updated Employee object
		
		
		
	}

	@Override
	public void delUser(int userid) {
	    // Try to delete the employee by ID
	    Optional<User> user = userRepository.findById(userid);
	    
	    if (user.isPresent()) {
	        // If the user exists, delete the employee
	        userRepository.deleteById(userid);
	    } else {
	        // If the user doesn't exist, throw an exception (handle gracefully in controller)
	        throw new RuntimeException("Employee not found");
	    }
	}

}
