create table USERS (
    id bigint auto_increment,
    name varchar(50),
    balance int,
    primary key (id)
);

create table USER_TRANSACTIONS(
    id bigint auto_increment,
    user_id bigint,
    amount int,
    transaction_date timestamp,
    foreign key (user_id) references USERS (id) on delete cascade
);

insert into users
    (name, balance)
    values
    ('sam', 1000),
    ('mike', 1200),
    ('jake', 800),
    ('marshal', 2000);