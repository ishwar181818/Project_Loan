package com.adm.model;

import com.adm.enums.UserType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;

    private String firstName;  

    private String lastName;   

    private String email;

    private String employeeSalary;

    private int employeeAge;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String username;

    private String password;

    @Lob
    @Column(length = 999999999)
    private byte[] employeeImage;

    @Lob
    @Column(length = 999999999)
    private byte[] employeePancard;
}
