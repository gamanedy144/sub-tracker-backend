--liquibase formatted sql
--changeset stu91300:202310071753_create_subscription_type_enum splitStatement:true andDelimiter:;

CREATE TYPE subscription_type_enum AS ENUM('DAILY', 'MONTHLY', 'BI-MONTHLY', 'YEARLY');


