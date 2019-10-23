create table spenserca.TeamTeammate
(
    TeamId     int not null,
    TeammateId int not null,
    constraint pk_TeamTeammate_TeamId_TeammateId primary key (TeamId, TeammateId),
    constraint fk_TeamTeammate_TeamId foreign key (TeamId) references spenserca.Team (Id),
    constraint fk_TeamTeammate_TeammateId foreign key (TeammateId) references spenserca.Teammate (Id)
);