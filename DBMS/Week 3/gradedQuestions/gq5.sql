select b1.title, count(*)
from book_catalogue as b1 join book_copies as b2
on b1.isbn_no = b2.isbn_no
where b1.title like '%Easy%'
group by b1.title
