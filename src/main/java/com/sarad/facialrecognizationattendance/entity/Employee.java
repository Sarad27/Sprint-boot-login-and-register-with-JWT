package com.sarad.facialrecognizationattendance.entity;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;

@TypeDefs(
        @TypeDef(
                name="string-array",
                typeClass = StringArrayType.class
        )
)


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

    @Column(
            columnDefinition = "text[]"
    )
    @Type(type = "string-array")
    private String[]  images;


}
