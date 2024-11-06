create database nutech_test;

-- App User
create table app_user (
	email varchar(100) not null,
	first_name varchar(100) not null,
	last_name varchar(100) not null,
	password varchar(100) not null,
	profile_image varchar(100),
	primary key (email)
);

select * from app_user;

-- Banner
create table banner(
	banner_name varchar(100) not null,
	banner_image varchar(100) not null,
	description text not null,
	primary key (banner_name)
);

select * from banner;

-- Service
create table service(
	service_code varchar(100) not null,
	service_name varchar(100) not null,
	service_icon varchar(100) not null,
	service_tarif numeric not null,
	primary key (service_code)
);

select * from service;

-- Balance
create table balance(
	email varchar(100) not null,
	balance numeric not null,
	primary key (email)
);

select * from balance;

-- Transaction
create table transactions(
	invoice_number varchar(100) not null,
	transaction_type varchar(100) not null,
	description varchar(100) not null,
	total_amount numeric not null,
	created_on timestamp with time zone not null,
	primary key (invoice_number)
);

select * from transactions;