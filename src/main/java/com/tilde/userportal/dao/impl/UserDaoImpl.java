package com.tilde.userportal.dao.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.tilde.userportal.dao.UserDao; // Importing the interface, UserDaoImpl is fulfilling a contract
import com.tilde.userportal.dao.mapper.UserRowMapper;
import com.tilde.userportal.model.User;

@Repository
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate; // Dependency injection(how DB access happens)

    @Autowired // injects dependencies, tells Spring that it is persistence logic.
    public UserDaoImpl(JdbcTemplate jdbcTemplate) { // Constructor injection:
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
     * ===============================
     * SQL Queries
     * ===============================
     */

    private static final String INSERT_SQL = "INSERT INTO users (first_name, last_name, gender, dob, email, phone, username, password, created_at)"
            +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW()) RETURNING user_id";

    private static final String FIND_BY_ID_SQL_STRING = "SELECT * FROM users WHERE user_id =?";
    private static final String FIND_ALL_SQL = "SELECT * FROM users ORDER BY user_id";
    private static final String UPDATE_SQL = "UPDATE users SET first_name=?, last_name=?, gender=?, dob=?, email=?, phone=?, username=?, password=? WHERE user_id=?";
    private static final String DELETE_SQL = "DELETE FROM users WHERE user_id=?";
    private static final String FIND_BY_EMAIL_SQL = "SELECT * FROM users WHERE email =?";
    private static final String FIND_BY_USERNAME_SQL = "SELECT * FROM users WHERE username =?";

    /**
     * ===============================
     * CRUD Operations
     * ===============================
     */

    @Override
    public int save(User user) {

        KeyHolder keyHolder = new GeneratedKeyHolder(); // GeneratedKeyHolder() : Concrete implementation

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getGender());

            if (user.getDob() != null) {
                ps.setDate(4, java.sql.Date.valueOf(user.getDob()));
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }

            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPhone());
            ps.setString(7, user.getUsername());
            ps.setString(8, user.getPassword());

            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public User findById(int id) {
        List<User> users = jdbcTemplate.query(FIND_BY_ID_SQL_STRING, new UserRowMapper(), id);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, new UserRowMapper());
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update(UPDATE_SQL, user.getFirstName(), user.getLastName(), user.getGender(),
                user.getDob() != null ? java.sql.Date.valueOf(user.getDob()) : null, user.getEmail(), user.getPhone(),
                user.getUsername(), user.getPassword(), user.getId());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(DELETE_SQL, id);
    }

    @Override
    public User findByEmail(String email) {
        List<User> users = jdbcTemplate.query(FIND_BY_EMAIL_SQL, new UserRowMapper(), email);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User findByUsername(String username) {
        List<User> users = jdbcTemplate.query(FIND_BY_USERNAME_SQL, new UserRowMapper(), username);
        return users.isEmpty() ? null : users.get(0);
    }
}
