package com.example.thrive.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "user")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @NotNull(message = "first name can not be null")
    @NotBlank(message = "first name can not be blank")
    @Size(min = 2, max = 20, message = "first name must be 2-20 characters long")
    @Column(length = 20)
    private String firstName;

    @NotNull(message = "last name can not be null")
    @NotBlank(message = "last name can not be blank")
    @Size(min = 2, max = 20, message = "last name must be 2-20 characters long")
    @Column(length = 20)
    private String lastName;

    @NotNull(message = "username can not be null")
    @NotBlank(message = "username can not be blank")
    @Size(min = 8, max = 25, message = "username must be 8-25 characters long")
    @Column(length = 25, unique = true)
    private String username;

    @Email(message = "provided email is not valid")
    @Column(length = 40, unique = true)
    private String email;

    @NotNull(message = "password can not be null")
    @NotBlank(message = "password can not be blank")
    @Size(min = 8, max = 40, message = "password must be 8-40 characters long")
    @Column(length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Override
    public String toString() {
        return "UserModel{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
