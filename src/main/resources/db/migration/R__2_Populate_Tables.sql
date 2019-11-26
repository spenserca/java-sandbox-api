insert into spenserca.Team (Name, Description, ModifiedDate)
select Name, Description, ModifiedDate
from (values ('Baker''s Dozen', 'Non-Qualified Team 9', getdate()),
             ('Fully Qualified', 'Non-Qualified Team 8', getdate())
     ) v (Name, Description, ModifiedDate);

go

insert into spenserca.Teammate (FirstName, LastName, NickName, ModifiedDate)
select FirstName, LastName, NickName, ModifiedDate
from (values (1, 'Spenser', 'Andreassen', 'Spen', getdate()),
             (2, 'Sean', 'Matthews', 'Seanathon', getdate())
     ) v (Id, FirstName, LastName, NickName, ModifiedDate);