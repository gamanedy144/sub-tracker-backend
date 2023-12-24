--liquibase formatted sql
--changeset stu91300:202312231730_alter_subscription_type_enum splitStatement:true andDelimiter:;

ALTER TYPE sub_tracker.subscription_type_enum add value 'WEEKLY';
