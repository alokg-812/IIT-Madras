select m.name 
from managers as m
join teams as t on t.team_id = m.team_id
where t.name = 'Arawali'
