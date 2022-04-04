create table opera (
    id   number      not null auto_increment,
    name varchar2(100),
    author varchar2(100),
    description varchar2(4000),
    category number,
    capacity number,
    primary key (id),
    unique key opera_name_author(name, author)
);

create table event (
    id   number      not null auto_increment,
    idOpera number,
    eventDate DATE,
    soldTickets number,
    primary key (id),
    unique key event_opera_date(idOpera, eventDate)
);


create table ticket (
    id   number      not null auto_increment,
    idEvent number,
    primary key (id)
);