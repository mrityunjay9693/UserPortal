package com.tilde.userportal.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonIgnore;

//Model class
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dob;
    private String email;
    private String phone;
    private String username;
    private String password; // hashed password

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH-mm-ss")
    private LocalDateTime createdAt;

    // Default Constructor
    public User() {
    }

    // Parameterized constructor(Without id, createdAt)
    public User(String firstName, String lastName, String gender, LocalDate dob, String email, String phone,
        String username, String password) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
            this.dob = dob;
            this.email = email;
            this.phone = phone;
            this.username = username;
            this.password = password;
    }

    // Full constructor(With id, createdAt)
    public User(int id, String firstName, String lastName, String gender, LocalDate dob, String email,
            String phone, String username, String password, LocalDateTime createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }

    //=====================================
    //       Getters and Setters 
    //=====================================
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // toString() without password for security
    public String toString() {
        return "User{" +
                "id=" + id + ", firstname='" + firstName + '\'' + ", lastname='" + lastName + '\'' + ", gender='"
                + gender + '\'' + ", dob='" + dob + '\'' + ", email='" + email + '\'' + ", phone='" + phone + '\''
                + ", username" + username + '\'' + ", createdAt=" + createdAt +
                "}";
    }

}
