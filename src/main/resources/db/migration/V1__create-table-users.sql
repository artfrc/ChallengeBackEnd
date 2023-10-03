create table users (

    id bigint not null auto_increment,
    name varchar(100) not null,
    document varchar(11) not null unique,
    email varchar(100) not null unique,
    balance FLOAT not null,

    primary key(id)

);