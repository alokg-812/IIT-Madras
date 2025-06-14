-- find courses offered in Fall 2009 and in Spring 2010 
select course_id
from section 
where semester = 'Fall' and year = 2009 and
	course_id in (
select course_id from section 
where semester = 'Spring' and year = 2010
	);

-- find courses offered in Fall 2009 or in Spring 2010 
select course_id
from section 
where semester = 'Fall' and year = 2009 or
	course_id in (
select course_id from section 
where semester = 'Spring' and year = 2010
	);

-- find courses offered in Fall 2009 but not in Spring 2010 
select course_id
from section 
where semester = 'Fall' and year = 2009 and
	course_id not in (
select course_id from section 
where semester = 'Spring' and year = 2010
	);


select count(ID)
from takes
where (course_id, sec_id, semester, year) in 
	( select course_id, sec_id, semester, year from teaches where teaches.ID = 10101
	);

select T.name
from instructor as T, instructor as S
where T.salary > S.salary and S.dept_name = 'Biology';


select dept_name, avg_salary
from (
select dept_name, avg(salary) as avg_salary
from instructor
group by dept_name
)
where avg_salary > 42000;


with max_budget(value) as
(	select max(budget)
	from department)
select dept_name
from department, max_budget
where department.budget = max_budget.value;


