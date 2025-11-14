package com.tilde.userportal.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository     // This tells spring this is a DAO component 
public class TestDao {
    private final JdbcTemplate jdbcTemplate;

    public TestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;   // JdbcTemplate is autowired by Spring using out bean 
                                            //from spring-servlet.xml
    }

    public String getDatabaseVersion() {    // runs a simple SQL query to verify connectivity. 
        try {
            // Running a simple uery to confirm PostgreSQL connection
            return jdbcTemplate.queryForObject("SELECT version();", String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return "Database connection failed!: " + e.getMessage();
        }
        //return "postgres";
    }
}
