-- liquibase formatted sql

-- changeset oss:1
CREATE TABLE rule (
    id SERIAL,
    query VARCHAR(255) UNIQUE NOT NULL,
    arguments JSON NOT NULL,
    negate BOOLEAN
)