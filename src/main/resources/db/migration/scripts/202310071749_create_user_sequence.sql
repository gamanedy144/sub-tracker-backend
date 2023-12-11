--liquibase formatted sql
--changeset stu91300:202310071749_create_user_sequence splitStatement:true andDelimiter:;

CREATE SEQUENCE IF NOT EXISTS sub_tracker.user_sequence start 1;
