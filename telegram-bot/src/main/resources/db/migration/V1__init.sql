create table users (
    id bigint UNSIGNED AUTO_INCREMENT,
    chat_id bigint not null,
    date_registration TIMESTAMP,
    site_id bigint,
    user_name varchar(255),
    primary key (id)
);