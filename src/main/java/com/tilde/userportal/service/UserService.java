package com.tilde.userportal.service;

import java.util.List;
import com.tilde.userportal.model.User;

public interface UserService {  // Contract
    // Called when new user registers
    int registerUser(User user);

    // Fetch single user by id.
    User getUserById(int id);
    
    // Fetch all users 
    List<User> getAllUsers();

    boolean updateUser(User user);  // 

    boolean deleteUserById(int id); // delete user

    boolean emailExists(String email);  // Used before registration

    boolean usernameExists(String username);    // Used before registration
}
