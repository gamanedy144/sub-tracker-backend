--liquibase formatted sql
--changeset stu91300:202401131854_create_transaction_table splitStatement:true andDelimiter:;

CREATE TABLE IF NOT EXISTS sub_tracker.transaction(
     id INT PRIMARY KEY,
     subscription_id BIGINT NOT NULL,
     timestamp TIMESTAMP NOT NULL,
     status VARCHAR(255) NOT NULL,
     FOREIGN KEY (subscription_id)
         REFERENCES sub_tracker.subscription(id)
);
CREATE SEQUENCE IF NOT EXISTS sub_tracker.transaction_sequence start 1;



