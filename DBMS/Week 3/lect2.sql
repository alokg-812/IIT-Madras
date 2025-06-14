-- Finding courses offered in Fall 2009 and Spring 2010

select distinct course_id
from section
where semester = 'Fall' and year = 2009 and
    course_id in (
        select course_id
        from section
        where semester = 'Spring' and year = 2010
    );



-- Finding courses offered in Fall 2009 but not in Spring 2010

select 