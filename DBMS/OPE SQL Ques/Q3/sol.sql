select mr.match_num 
from match_referees as mr
join matches on matches.match_num = mr.match_num
where matches.host_team_id = 'T0004' and matches.guest_team_id = 'T0002'
