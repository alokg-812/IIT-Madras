Tasks to do:
1. Add a column next to Language and name it as Lang-Adj and in this column, the value should be the marks from the language column multiplied by 4.
2. Add a row at the end called Avg to contain the column averages of Maths, Physics, Chemistry and Lang-Adj.
3. Add another row to have the maximum score each of the above columns.
4. Add another row to have the minimum score each of the above columns.
5. Add a column called 'Total_Avg_Marks' to have the average score of Maths, Physics, Chemistry and Lang-Adj.
6. Add a column called Grade which contains the grade using the logic, if
   - `Total_Avg_Marks` is greater than or equal to `90`, then `A` grade,
   - if total marks is greater than or equal to `75` and less than `90`, then `B` grade,
   - if total marks is greater than or equal to `60` and less than `75`, then `C` grade,
   - if total marks is greater than or equal to `50` and less than `60`, then `D` grade,
   - if total marks is greater than or equal to `35` and less than `50`, then `E` grade, and
   - if total marks is lesser than `35`, then `F` grade.

7. Use VLOOKUP along with the table in Sheet 2 to give the grade description in the next column.


### Questions

Questions:
1. What is the maximum score achieved in Maths? (INTEGER)
2. What is the minimum score achieved in Physics? (INTEGER)
3. What is the average score achieved in Chemistry? (FLOAT)
4. What is the average score achieved in Lang_Adj? (FLOAT) <br>
Use COUNTIFS function to find:
5. Find the total number of MECH department students who got a D grade. (INTEGER)
6. Find the total number of CS department students who got C grade. (INTEGER)
