--liquibase formatted sql
--changeset stu9130:202308171351_create_user_table splitStatement:true andDelimiter:;

CREATE TABLE IF NOT EXISTS sub_tracker.user(
    id INT PRIMARY KEY,
    username VARCHAR(55) NOT NULL UNIQUE,
    email VARCHAR(75) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    profile_picture_path TEXT
)