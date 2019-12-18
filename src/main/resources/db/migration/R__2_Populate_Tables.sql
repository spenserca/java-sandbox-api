insert into PUBLIC.Team (Name, Description, ModifiedDate)
values ('Bakers Dozen', 'Non-Qualified Team 9', curdate()),
       ('Fully Qualified', 'Non-Qualified Team 8', curdate());

insert into PUBLIC.Teammate (FirstName, LastName, NickName, ModifiedDate)
values ('Spenser', 'Andreassen', 'Spen', curdate()),
       ('Sean', 'Matthews', 'Seanathon', curdate())