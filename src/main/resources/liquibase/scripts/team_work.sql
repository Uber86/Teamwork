-- liquibase formatted sql

-- changeset oss:1
CREATE TABLE rule (
    id SERIAL PRIMARY Key,
    product_name varchar(255),
    product_id UUID,
    product_text text
)

-- changeset oss:2
CREATE TABLE query (
    id BIGSERIAL PRIMARY Key,
    query varchar(255) UNIQUE NOT NULL ,
    arguments TEXT NOT NULL,
    negate BOOLEAN,
    rule_id bigint references rule(id)
)