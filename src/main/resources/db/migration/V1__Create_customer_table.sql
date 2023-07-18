CREATE TABLE customer
(
    id               BIGSERIAL primary key not null,
    full_name         VARCHAR(250),
    registration_code VARCHAR(500),
    email            VARCHAR(50),
    telephone        VARCHAR(50)
);
