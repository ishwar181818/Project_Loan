package com.adm.serviceimpl;

import com.adm.model.User;
import com.adm.repo.UserRepository;
import com.adm.servicei.ServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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
		 return userRepository.findByUsernameAndPassword(username, password);
		
	}
}
