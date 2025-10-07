SELECT s.student_fname, s.dob
FROM students s
JOIN departments d ON s.department_code = d.department_code
WHERE d.department_code = 'ME' AND s.dob > '2003-06-15'
