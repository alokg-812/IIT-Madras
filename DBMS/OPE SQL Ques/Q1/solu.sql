select c.title from course as c
join department as d on d.dept_name = c.dept_name
where d.dept_name like 'Statistics'
