alter table if exists tb_product_category 
    add constraint FK5r4sbavb4nkd9xpl0f095qs2a 
    foreign key (category_id) 
    references tb_category;

alter table if exists tb_product_category 
    add constraint FKgbof0jclmaf8wn2alsoexxq3u 
    foreign key (product_id) 
    references tb_product;