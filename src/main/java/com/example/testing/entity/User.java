package com.example.testing.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements Serializable {


    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    @NotBlank(message = "E-mail address must not be empty")
    @Email(message = "User must have valid email address")
    private String email;

    @Column(length = 20, nullable = false)
    @Length(min = 3, max = 15)
    private String firstName;

    @Column(length = 20, nullable = false)
    @Length(min = 3, max = 15)
    private String lastName;

    @Column(length = 10, nullable = false)
    @Length(min = 6, max = 15)
    private String password;
}
