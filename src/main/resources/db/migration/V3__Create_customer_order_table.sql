CREATE TABLE customer_order
(
    id              BIGSERIAL primary key not null,
    submission_date DATE,
    customer_id     BIGINT references customer (id)
);