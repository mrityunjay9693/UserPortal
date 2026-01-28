-- ===================================================
-- File: users-table-ddl.sql
-- Purpose: Create users table for Users Module
-- Database: PostgreSQL
-- ===================================================

CREATE TABLE users(
    user_id     SERIAL PRIMARY KEY,

    first_name  VARCHAR(1000) NOT NULL,
    last_name   VARCHAR(100),

    gender      VARCHAR(10),
    dob        DATE,

    email       VARCHAR(150) NOT NULL,
    phone       VARCHAR(20),

    username    VARCHAR(100) NOT NULL,
    password    VARCHAR(255) NOT NULL,

    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uk_users_email UNIQUE (email),
    CONSTRAINT uk_users_username UNIQUE (username)
)