merge into spenserca.Team t
using (values (1, 'Baker''s Dozen', 'Non-Qualified Team 9'),
              (2, 'Fully Qualified', 'Non-Qualified Team 8')
)
    as s (Id, Name, Description)
on t.Id = s.Id
when not matched then
    insert (Id,
            Name,
            Description)
    values (s.Id,
            s.Name,
            s.Description);