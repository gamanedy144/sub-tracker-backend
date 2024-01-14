--liquibase formatted sql
--changeset stu91300:202401141900_alter_subscription_table splitStatement:true andDelimiter:;

ALTER TABLE sub_tracker.subscription
    ADD COLUMN IF NOT EXISTS category varchar(255);




