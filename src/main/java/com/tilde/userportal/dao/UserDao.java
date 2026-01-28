package com.tilde.userportal.dao;

import java.util.List;

import com.tilde.userportal.model.User;

public interface UserDao {  // Contract
    
    //Create (Insert)
    int save(User user);

    //Read (Single)
    User findById(int id);

    //Read (All)
    List<User> findAll();

    //Update
    //int update(User user);
    int update(User user);

    //Delete
    int delete(int id);

    //For validation
    User findByEmail(String email);

    //For login, validating username during registration
    User findByUsername(String username);
}
