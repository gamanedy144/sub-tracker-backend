--liquibase formatted sql
--changeset stu91300:202401131352_alter_subscription_table splitStatement:true andDelimiter:;

ALTER TABLE sub_tracker.subscription
    drop column price;
ALTER TABLE sub_tracker.subscription
    ADD COLUMN price float;



