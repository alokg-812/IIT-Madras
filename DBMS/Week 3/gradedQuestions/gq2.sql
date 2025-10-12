select p.name as eldest_player from players as p
join teams as t on p.team_id = t.team_id
where t.name = 'Rainbow'
order by p.dob ASC limit 1
