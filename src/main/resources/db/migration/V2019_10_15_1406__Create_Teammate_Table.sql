create table spenserca.Teammate
(
    Id           int         not null primary key,
    FirstName    varchar(64) not null,
    LastName     varchar(64) not null,
    NickName     varchar(64) not null,
    ModifiedDate datetime    not null
        constraint df_Teammate_CreatedDate default (getdate())
);