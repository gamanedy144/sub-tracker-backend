--liquibase formatted sql
--changeset stu91300:202401211910_alter_user_table splitStatement:true andDelimiter:;

ALTER TABLE sub_tracker.user
    ADD COLUMN IF NOT EXISTS
        create_ts timestamp;