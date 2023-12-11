--liquibase formatted sql
--changeset stu91300:202310071753_create_subscription_provider_table splitStatement:true andDelimiter:;

CREATE TABLE IF NOT EXISTS sub_tracker.subscription_provider(
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    details VARCHAR(255) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS sub_tracker.subscription_provider_sequence start 1;
