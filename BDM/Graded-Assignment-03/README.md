## Problem Statement

1. Use Sheets “Data” and “Cost” for answer Questions 1 to 5.
2. Q1 – Q5 are configured to take STRING INPUT
3. To avoid errors, copy + paste the exact text from the cell to answer sheet.
For example, for gear name, enter the full name like: Gear Assembly 3 (BS4/6)
4. Use Sheets “Shift_Running”, “Actual_Output” and “Scrap” to answer Questions 6 to 10.
5. If a shift is operational, then the Availability for that shift is 1; else 0.
6. Performance, Quality or MAPE can be calculated for operational shifts only.

<img width="280" height="130" alt="image" src="https://github.com/user-attachments/assets/27182e5f-2aaa-4a5a-bf7c-987f818a6ad2" />

where, n = no. of days (exclude non-operational shifts / days);
At = Actual Output;
Ft = Rated Output
Take Rated Output as 4000.

QUESTIONS:
1. Which BS4 only Gear Assembly saw the maximum sales in the first quarter of any given
year?
HINT: Q1 is configured for the gear name only.
2. Based on the data, which is Gear Assembly is incurring maximum (net) loss?
HINT: Look at Unit margin
3. Which Gear Assembly returned the highest percentage unit (net) margin?
HINT: Unit margin is the per gear assembly margin. It is the difference between Price
and Unit Overall product cost for a particular gear assembly.
4. Which period saw the least amount of ending inventory in terms of volume?
HINT: create a new column to concatenate Quarter and Fiscal Year.
E.g. CONCAT(Dx,Ex), E.g. Q32020-21
5. Which Gear Assembly made the maximum jump in the percentage revenue from 2019-
20 to 2020-21?
HINT: Revenue is the product of sales (volumes) and price.
6. What is the Overall Equipment Effectiveness (OEE) of manufacturing in Week-1
(01-04-2022 to 07-04-2022 both days included)? (FLOAT BETWEEN 0 AND 1)
Hint: OEE of a particular shift can be zero if no production happens.
7. What is the overall quality of the manufacturing process during the fortnight? (FLOAT
BETWEEN 0 AND 1)
8. What is the performance of the manufacturing process during Week-2? (FLOAT
BETWEEN 0 AND 1)
9. What is the average number of parts manufactured per hour during the fortnight?
Assume that a shift runs for 8 hours and there is no break between shifts. (INTEGER)
(Round down the answer to the nearest whole number. E.g. We can’t have 2.3 parts, so
the answer will be 2 parts)
Hint: Exclude non-production time.
10. The company uses MAPE (Mean Absolute Percentage Error) to measure process
variability in the manufacturing process. Which shift sees the maximum process
variability during the fortnight? E.g. Shift 1 (STRING)
