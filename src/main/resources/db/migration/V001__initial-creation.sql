create table gas_station (
    id bigint not null auto_increment, 
    is_there_convenience bit not null, 
    name varchar(255), 
    url_img varchar(255), 
    primary key (id)
  ) engine=InnoDB;

create table address (
    city varchar(255), 
    address_number varchar(255), 
    address_state varchar(255), 
    street varchar(255), 
    gas_station_id bigint not null, 
    primary key (gas_station_id)
) engine=InnoDB;
    
    create table gas_price (
    diesel double precision, 
    ethanol double precision, 
    ethanol_additive double precision, 
    gasoline double precision, 
    gasoline_additive double precision, 
    last_ethanol_price double precision, 
    last_gas_price double precision, 
    relation_gas_ethanol double precision, 
    updated_at datetime, updated_by varchar(255), 
    gas_station_id bigint not null, 
    primary key (gas_station_id)
    ) engine=InnoDB;
    

create table hibernate_sequence (
    next_val bigint
) engine=InnoDB;

insert into hibernate_sequence values (1);

create table refresh_token (
    id bigint not null, 
    expiry_date datetime not null, 
    token varchar(255) not null, 
    user_id bigint, 
    primary key (id)
) engine=InnoDB;

create table roles (
    id bigint not null auto_increment, 
    name varchar(20), 
    primary key (id)
) engine=InnoDB;

create table user_roles (
    user_id bigint not null, 
    role_id bigint not null, 
    primary key (user_id, role_id)
) engine=InnoDB;
    
create table users (
    id bigint not null auto_increment,
    email varchar(45), 
    password varchar(100), 
    username varchar(25), 
    primary key (id)
) engine=InnoDB;
    
alter table refresh_token add constraint UK_r4k4edos30bx9neoq81mdvwph unique (token);
alter table users add constraint UKr43af9ap4edm43mmtq01oddj6 unique (username);
alter table users add constraint UK6dotkott2kjsp8vw4d0m25fb7 unique (email);
alter table address add constraint FK6i36bpubftqv132mkwr4v2m1w foreign key (gas_station_id) references gas_station (id);
alter table gas_price add constraint FKkcss56enptl0b5oatx3sb9ywn foreign key (gas_station_id) references gas_station (id);
alter table refresh_token add constraint FKjtx87i0jvq2svedphegvdwcuy foreign key (user_id) references users (id);
alter table user_roles add constraint FKh8ciramu9cc9q3qcqiv4ue8a6 foreign key (role_id) references roles (id);
alter table user_roles add constraint FKhfh9dx7w3ubf1co1vdev94g3f foreign key (user_id) references users (id);