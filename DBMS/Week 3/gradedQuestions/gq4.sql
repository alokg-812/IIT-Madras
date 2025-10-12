select m.roll_no, bi.member_no from book_issue as bi
join members as m on bi.member_no = m.member_no
where m.member_class = 'Student' and bi.doi between '2021-08-02' and '2021-08-07'
