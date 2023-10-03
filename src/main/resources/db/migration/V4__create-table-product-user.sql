create table product_user (

    product_fk bigint not null,
    user_fk bigint not null,

    primary key(product_fk,user_fk),
    foreign key(product_fk) references products(id),
    foreign key(user_fk) references users(id)

);