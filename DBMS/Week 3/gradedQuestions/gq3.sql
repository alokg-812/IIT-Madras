select t.city, t.name as team_name from teams as t
join players as p on t.team_id = p.team_id
where p.jersey_no between 80 and 99
