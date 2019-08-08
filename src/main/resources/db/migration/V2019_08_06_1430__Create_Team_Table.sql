create table spenserca.Team (
    Id          int         not null identity primary key,
    Name        varchar(64) not null,
    Description varchar(64) null,
    CreatedDate datetime    not null constraint df_Team_CreatedDate default (getdate())
);