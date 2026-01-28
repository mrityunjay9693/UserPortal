package com.tilde.userportal.dao.mapper;

import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tilde.userportal.model.User;

public class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();

                user.setId(rs.getInt("user_id"));
                user.setFirstName(
                                rs.getString("first_name"));
                user.setLastName(
                                rs.getString("last_name"));
                user.setGender(
                                rs.getString("gender"));

                // Handle Date -> LocalDate
                if (rs.getDate("dob") != null) {
                        user.setDob(rs.getDate("dob").toLocalDate());
                }

                user.setEmail(
                                rs.getString("email"));
                user.setPhone(
                                rs.getString("phone"));
                user.setUsername(
                                rs.getString("username"));
                user.setPassword(
                                rs.getString("password"));

                // Handle TIMESTAMP -> LocalDateTime
                Timestamp ts = rs.getTimestamp("created_at");
                if (ts != null) {
                        user.setCreatedAt(ts.toLocalDateTime());
                }
                return user;
        }
}
