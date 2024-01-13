--liquibase formatted sql
--changeset stu91300:202401131340_alter_subscription_table splitStatement:true andDelimiter:;

ALTER TABLE sub_tracker.subscription
    ADD COLUMN IF NOT EXISTS
        price MONEY;


