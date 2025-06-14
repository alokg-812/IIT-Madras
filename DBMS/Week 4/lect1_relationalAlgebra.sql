select * from student;
select * from course;
select * from department;

-- select operation:

select * from student where dept_name = 'Computer Science';


-- project operation:

select name, dept_name from student;


-- union operation:

select dept_name, name from student
union
select dept_name, course_id from course;


-- difference operation:

select dept_name, name from student
except
select dept_name, course_id from course;



-- intersection operation:

select dept_name, name from student
intersect
select dept_name, course_id from course;



-- cartesian product operation:

select * from student cross join course;



-- rename operation:

select dept_name as department_name from student;


-- division operation:

select d.dept_name from department as d  
where not exists(  
    select s.dept_name from student as s  
    where not exists (  
        select 1 from student s2  
        where s2.dept_name = d.dept_name  
        and s2.dept_name = s.dept_name  
    )  
);
