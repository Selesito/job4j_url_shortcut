create table site (
    id serial primary key,
    username varchar(2000) UNIQUE,
    registration boolean,
    login VARCHAR(2000),
    password VARCHAR(2000)
);

create table link (
    id serial primary key,
    url VARCHAR(2000) UNIQUE,
    code VARCHAR(2000),
    count INTEGER
);

create table site_links (
    site_id int not null references site(id),
    links_id int not null references link(id)
);