--liquibase formatted sql
--changeset stu91300:202312231743_alter_subscription_table splitStatement:true andDelimiter:;

ALTER TABLE sub_tracker.subscription
    rename column startdate to start_date;

