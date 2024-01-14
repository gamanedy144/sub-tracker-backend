--liquibase formatted sql
--changeset stu91300:202401141905_alter_subscription_table splitStatement:true andDelimiter:;

ALTER TABLE sub_tracker.subscription
    alter column end_date
        drop not null;




