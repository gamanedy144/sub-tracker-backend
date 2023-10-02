--liquibase formatted sql
--changeset stu9130:202308171351_create_user_table splitStatement:true andDelimiter:;

CREATE TABLE IF NOT EXISTS sub_tracker.user(
    ID INT PRIMARY KEY,
    USERNAME VARCHAR(55) NOT NULL,

)