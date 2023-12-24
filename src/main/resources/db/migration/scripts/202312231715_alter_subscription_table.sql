--liquibase formatted sql
--changeset stu91300:202312231715_alter_subscription_table splitStatement:true andDelimiter:;

ALTER TABLE sub_tracker.subscription
    ADD COLUMN if not exists
        startDate date;

