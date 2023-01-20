package com.sarad.facialrecognizationattendance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "employee"
)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "First name is mandatory")
    private String firstName;


    @Column(nullable = false)
    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

}
