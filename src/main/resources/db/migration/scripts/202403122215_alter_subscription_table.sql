--liquibase formatted sql
--changeset stu91300:202403122215_alter_subscription_table splitStatement:true andDelimiter:;

ALTER TABLE sub_tracker.subscription
    add column active boolean;




