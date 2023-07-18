CREATE TABLE customer
(
    id               BIGSERIAL primary key not null,
    fullName         VARCHAR(250),
    registrationCode VARCHAR(500),
    email            VARCHAR(50),
    telephone        VARCHAR(50)
);
