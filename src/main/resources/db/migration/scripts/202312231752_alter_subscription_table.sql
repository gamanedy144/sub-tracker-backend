--liquibase formatted sql
--changeset stu91300:202312231752_alter_subscription_table splitStatement:true andDelimiter:;

ALTER TABLE sub_tracker.subscription
    ADD COLUMN if not exists
        end_date date;

