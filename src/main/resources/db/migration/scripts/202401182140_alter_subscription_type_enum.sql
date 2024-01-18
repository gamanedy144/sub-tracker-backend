--liquibase formatted sql
--changeset stu91300:202401182140_alter_subscription_type_enum splitStatement:true andDelimiter:;

ALTER TYPE sub_tracker.subscription_type_enum RENAME VALUE 'BI-MONTHLY' TO 'BIMONTHLY'



