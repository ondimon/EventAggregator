create table parsed_links (
    id bigint UNSIGNED AUTO_INCREMENT,
    link varchar(255) not null,
    date TIMESTAMP,
    primary key (id)
);
