--liquibase formatted sql
--changeset stu9130:202310071749_create_user_sequence splitStatement:true andDelimiter:;

CREATE SEQUENCE IF NOT EXISTS sub_tracker.user_sequence start 1;
