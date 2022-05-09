create EXTENSION if not exists "uuid-ossp";

create table meals (
    id int8 not null,
    resource_id uuid default uuid_generate_v4(),
    meal_consistency varchar(255) not null,
    meal_name varchar(255) not null,
    meal_price float8 not null, primary key (id));

create table order_meals (
    order_id int8 not null,
    meal_id int8 not null);

create table orders (
    id int8 not null,
    resource_id uuid default uuid_generate_v4(),
    order_date timestamp not null,
    order_price float8 not null,
    user_id int8,
    primary key (id));

create table tables (
    id int8 not null,
    resource_id uuid default uuid_generate_v4(),
    booking_time_end timestamp,
    booking_time_start timestamp,
    is_smoking_table boolean not null,
    number_of_seats int4 not null,
    primary key (id));

create table users (
    id int8 not null,
    resource_id uuid default uuid_generate_v4(),
    email varchar(255) not null,
    name varchar(255) not null,
    phone_number varchar(255) not null,
    surname varchar(255) not null,
    username varchar(255) not null,
    table_id int8,
    primary key (id));

alter table meals
    add constraint UK_qhq8tw6dv11c0x362x8n7xfwp unique (resource_id);
alter table meals
    add constraint UK_f24ujgyx79qr11sffxc690qk8 unique (meal_name);
alter table orders
    add constraint UK_3i2xj14gt26rgq1ykvle5y4bh unique (resource_id);
alter table tables
    add constraint UK_5kp0pqde31xdkb3t3br8858nn unique (resource_id);
alter table users
    add constraint UK_dtoje6w3m2tscj65vn7xckrpp unique (resource_id);
alter table users
    add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);
alter table users
    add constraint UK_9q63snka3mdh91as4io72espi unique (phone_number);
alter table users
    add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username);

create sequence hibernate_sequence start 1 increment 1;

alter table order_meals
    add constraint FKjxd746gurxd3ox7owngu7jmit foreign key (meal_id) references meals;
alter table order_meals
    add constraint FKh3j3qrqr3blhwl2a6y5mdf77f foreign key (order_id) references orders;
alter table orders
    add constraint FK32ql8ubntj5uh44ph9659tiih foreign key (user_id) references users;
alter table users
    add constraint FKg50n07wuupfra7n2tiu924ru2 foreign key (table_id) references tables;