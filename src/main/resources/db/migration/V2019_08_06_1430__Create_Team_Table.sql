create table spenserca.Team (
                                Id          int         not null primary key,
                                Name        varchar(64) not null,
                                Description varchar(64) null,
                                CreatedDate datetime    not null constraint df_Team_CreatedDate default (getdate())
);

insert into spenserca.Team
select Id,
       [Name],
       [Description],
       CreatedDate
from (values (1, 'Bakers Dozen', 'Non-Qualified Team 9', getdate()),
             (2, 'Fully Qualified', 'Non-Qualified Team 8', getdate())
     ) v (Id, Name, Description, CreatedDate);