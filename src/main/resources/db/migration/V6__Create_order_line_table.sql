CREATE TABLE order_line
(
    id                BIGSERIAL primary key not null,
    customer_order_id BIGINT references customer_order (id),
    product_id        BIGINT references product (id)
);