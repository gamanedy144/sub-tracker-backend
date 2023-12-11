--liquibase formatted sql
--changeset stu91300:202310071753_create_subscription_table splitStatement:true andDelimiter:;

CREATE TABLE IF NOT EXISTS sub_tracker.subscription(
    id INT PRIMARY KEY,
    user_id INT NOT NULL,
    subscription_name VARCHAR(55) NOT NULL,
    provider_id INT NOT NULL,
    type subscription_type_enum

);

CREATE SEQUENCE IF NOT EXISTS sub_tracker.subscription_sequence start 1;
