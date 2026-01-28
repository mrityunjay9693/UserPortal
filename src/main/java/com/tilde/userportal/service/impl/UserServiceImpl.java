package com.tilde.userportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tilde.userportal.dao.UserDao;
import com.tilde.userportal.model.User;
import com.tilde.userportal.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public int registerUser(User user) {
        // 1. Validation
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        if (emailExists(user.getEmail())) {
            throw new IllegalArgumentException("Email alreay exists.");
        }

        if (usernameExists(user.getUsername())) {
            throw new IllegalArgumentException("Username alrady exists.");
        }

        // 2. Password Hashing
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        // 3. Save user
        return userDao.save(user);
    }

    @Override
    public User getUserById(int id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public boolean updateUser(User user) {
        // 1. Basic Validation
        if (user == null || user.getId() < 0) {
            return false;
        }

        // 2. Fetch existing user from DB
        User existingUser = userDao.findById(user.getId());
        if (existingUser == null) {
            return false;
        }

        // 3. Merge only provided fields
        if (user.getFirstName() != null) {
            existingUser.setFirstName(user.getFirstName());
        }

        if (user.getLastName() != null) {
            existingUser.setLastName(user.getLastName());
        }

        if (user.getGender() != null) {
            existingUser.setGender(user.getGender());
        }

        if (user.getDob() != null) {
            existingUser.setDob(user.getDob());
        }

        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }

        if (user.getPhone() != null) {
            existingUser.setPhone(user.getPhone());
        }

        if (user.getUsername() != null) {
            existingUser.setUsername(user.getUsername());
        }

        // Password handling
        if (user.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        // Update the merge entity
        return userDao.update(existingUser) > 0;
        // return userDao.update(user);
    }

    @Override
    public boolean deleteUserById(int id) {
        return userDao.delete(id) > 0;
    }

    @Override
    public boolean emailExists(String email) {
        return userDao.findByEmail(email) != null;
    }

    @Override
    public boolean usernameExists(String username) {
        return userDao.findByUsername(username) != null;
    }
}
