--liquibase formatted sql
--changeset stu9130:202310071753_create_subscription_table splitStatement:true andDelimiter:;

CREATE TYPE subscription_type_enum AS ENUM('daily', 'monthly', 'bi-monthly', 'yearly');


