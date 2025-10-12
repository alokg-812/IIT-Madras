select m.match_num, r.name as main_referee from matches as m
join match_referees as mr on m.match_num = mr.match_num
join referees as r on mr.referee = r.referee_id where m.match_date = '2020-05-15'
