1. Which BS4 only Gear Assembly saw the maximum sales in the first quarter of any given year?
HINT: Q1 is configured for the gear name only.
**Ans.** `Gear Assembly 2 (BS4)`
**Formula:** `=MAXIFS(G2:G175, B2:B175, "BS4 Only", D2:D175, "Q1")`

2. Based on the data, which is Gear Assembly is incurring maximum (net) loss?
HINT: Look at Unit margin
**Ans.** `233`
a. First fetching the minimum selling price to get maximum loss `=MINIFS(Data!$H$2:$H$175, Data!$A$2:$A$175, "Gear Assembly 2 (BS4)", Data!$E$2:$E$175, "2019-20")` <br>
b. Get loss using (Total making expenditure - selling price) `=J2-I2` <br>
c. Finding max loss `=MAX(ABS(K2:K19))` 

3. Which Gear Assembly returned the highest percentage unit (net) margin?
HINT: Unit margin is the per gear assembly margin. It is the difference between Price and Unit Overall product cost for a particular gear assembly.
**Ans.**
**Formula:**

4. Which period saw the least amount of ending inventory in terms of volume?
HINT: create a new column to concatenate Quarter and Fiscal Year.
E.g. CONCAT(Dx,Ex), E.g. Q32020-21
**Ans.**
**Formula:**

5. Which Gear Assembly made the maximum jump in the percentage revenue from 2019-20 to 2020-21?
HINT: Revenue is the product of sales (volumes) and price.
**Ans.**
**Formula:**

6. What is the Overall Equipment Effectiveness (OEE) of manufacturing in Week-1(01-04-2022 to 07-04-2022 both days included)? (FLOAT BETWEEN 0 AND 1)
Hint: OEE of a particular shift can be zero if no production happens.
**Ans.**
**Formula:**

7. What is the overall quality of the manufacturing process during the fortnight? (FLOAT BETWEEN 0 AND 1)
**Ans.**
**Formula:**

8. What is the performance of the manufacturing process during Week-2? (FLOAT BETWEEN 0 AND 1)
**Ans.**
**Formula:**

9. What is the average number of parts manufactured per hour during the fortnight?
Assume that a shift runs for 8 hours and there is no break between shifts. (INTEGER)
(Round down the answer to the nearest whole number. E.g. We can’t have 2.3 parts, so the answer will be 2 parts)
Hint: Exclude non-production time.
**Ans.**
**Formula:**

10. The company uses MAPE (Mean Absolute Percentage Error) to measure process variability in the manufacturing process. Which shift sees the maximum process variability during the fortnight? E.g. Shift 1 (STRING)
**Ans.**
**Formula:**