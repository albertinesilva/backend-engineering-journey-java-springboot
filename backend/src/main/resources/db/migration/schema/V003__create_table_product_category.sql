create table tb_product_category (
    category_id bigint not null,
    product_id bigint not null,
    primary key (category_id, product_id)
);