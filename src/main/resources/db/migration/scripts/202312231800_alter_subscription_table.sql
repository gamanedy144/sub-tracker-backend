--liquibase formatted sql
--changeset stu91300:202312231800_alter_subscription_table splitStatement:true andDelimiter:;

ALTER TABLE sub_tracker.subscription
    ADD COLUMN IF NOT EXISTS
        next_occurrence_date date,
    ADD COLUMN IF NOT EXISTS
        last_occurrence_date date;

