merge into spenserca.TeamTeammate t
using (values (1, 1),
              (1, 2),
              (2, 2)
)
    as s (TeamId, TeammateId)
on t.TeamId = s.TeamId
    and t.TeammateId = s.TeammateId
when not matched then
    insert (TeamId,
            TeammateId)
    values (s.TeamId,
            s.TeammateId);