drop table if exists spenserca.Team;

create table spenserca.Team
(
    Id           int identity (1, 1) primary key,
    Name         varchar(64) not null,
    Description  varchar(64) null,
    ModifiedDate datetime    not null
);

go

drop table if exists spenserca.Teammate;

create table spenserca.Teammate
(
    Id           int identity (1, 1) primary key,
    FirstName    varchar(64) not null,
    LastName     varchar(64) not null,
    NickName     varchar(64) null,
    ModifiedDate datetime    not null
);