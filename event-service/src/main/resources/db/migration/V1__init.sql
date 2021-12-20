create table events (
    id bigint UNSIGNED AUTO_INCREMENT,
    name varchar(255) not null,
    description TEXT,
    link varchar(255) not null,
    date_start TIMESTAMP,
    date_end TIMESTAMP,
    primary key (id)
);

create table tags (
    id bigint UNSIGNED AUTO_INCREMENT,
    name varchar(255) not null,
    primary key (id)
);

create table events_tag (
   id_tag bigint UNSIGNED not null,
   id_event bigint UNSIGNED not null,
   foreign key (id_event) references events (id) ON DELETE CASCADE,
   foreign key (id_tag) references tags (id) ON DELETE CASCADE
);