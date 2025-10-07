SELECT faculty_fname, faculty_lname
FROM faculty
JOIN departments ON faculty.department_code = departments.department_code
WHERE departments.department_name = 'Computer Science'
  AND faculty.doj > '2015-03-03'
