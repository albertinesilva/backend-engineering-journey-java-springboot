alter table if exists tb_user_role 
    add constraint FKea2ootw6b6bb0xt3ptl28bymv 
    foreign key (role_id) 
    references tb_role;

alter table if exists tb_user_role 
    add constraint FK7vn3h53d0tqdimm8cp45gc0kl 
    foreign key (user_id) 
    references tb_user;