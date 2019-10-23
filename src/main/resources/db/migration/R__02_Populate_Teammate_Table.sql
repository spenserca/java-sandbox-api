merge into spenserca.Teammate t
using (values (1, 'Spenser', 'Andreassen', 'Spen'),
              (2, 'Sean', 'Matthews', 'Seanathon')
)
    as s (Id, FirstName, LastName, NickName)
on t.Id = s.Id
when not matched then
    insert (Id,
            FirstName,
            LastName,
            NickName)
    values (s.Id,
            s.FirstName,
            s.LastName,
            s.NickName);