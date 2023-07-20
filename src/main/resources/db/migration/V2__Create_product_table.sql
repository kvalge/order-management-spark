CREATE TABLE product
(
    id       BIGSERIAL primary key not null,
    name     VARCHAR(500),
    sku_code VARCHAR(500),
    price    VARCHAR(50)
);