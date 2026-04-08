1. Which BS4 only Gear Assembly saw the maximum sales in the first quarter of any given year?
HINT: Q1 is configured for the gear name only.
**Ans.** `Gear Assembly 2 (BS4)`
**Formula:** `=MAXIFS(G2:G175, B2:B175, "BS4 Only", D2:D175, "Q1")`

2. Based on the data, which is Gear Assembly is incurring maximum (net) loss?
HINT: Look at Unit margin
**Ans.** `Gear Assembly 3 (BS4/6)`
a. First fetching the minimum selling price to get maximum loss `=MINIFS(Data!$H$2:$H$175, Data!$A$2:$A$175, "Gear Assembly 2 (BS4)", Data!$E$2:$E$175, "2019-20")` <br>
b. Get loss using (Total making expenditure - selling price) `=J2-I2` <br>
c. Finding max loss `=MAX(ABS(K2:K19))` 

3. Which Gear Assembly returned the highest percentage unit (net) margin?**
HINT: Unit margin is the per gear assembly margin. It is the difference between Price and Unit Overall product cost for a particular gear assembly.
**Ans.** `Gear Assembly 5 (BS6)`
**Steps & Formula:**
a. First, calculate the "Total Unit Cost" in the `Cost` sheet by summing the cost components: `=SUM(Cost!C2:G2)` (assuming columns C through G hold the specific costs).
b. In the `Data` sheet, pull this total cost for each row using `SUMIFS` matching both the Assembly and Fiscal Year: `=SUMIFS(Cost!$H:$H, Cost!$A:$A, Data!A2, Cost!$B:$B, Data!E2)`
c. Calculate the % margin in a new column: `=(Data!H2 - [Total_Unit_Cost]) / Data!H2`
d. Find the gear assembly with the maximum value using a Pivot Table, or an index-match array formula: `=INDEX(A2:A175, MATCH(MAX(K2:K175), K2:K175, 0))` (Assuming K is the % margin column).

4. Which period saw the least amount of ending inventory in terms of volume?**
HINT: create a new column to concatenate Quarter and Fiscal Year. E.g. CONCAT(Dx,Ex), E.g. Q32020-21
**Ans.** `Q22021-22`
**Steps & Formula:**
a. Create a "Period" column (Let's say Column L): `=CONCAT(D2, E2)`
b. Calculate ending inventory for each row (Produced - Sales): `=F2 - G2` 
c. The easiest way to summarize this is a Pivot Table with `Period` in the Rows and `Sum of Ending Inventory` in the Values. Sort ascending to find the minimum. 
d. To do it with a single array formula finding the minimum sum: `=MIN(SUMIFS(F:F, L:L, UNIQUE(L2:L175)) - SUMIFS(G:G, L:L, UNIQUE(L2:L175)))`

5. Which Gear Assembly made the maximum jump in the percentage revenue from 2019-20 to 2020-21?**
HINT: Revenue is the product of sales (volumes) and price.
**Ans.** `Gear Assembly 6 (BS6)`
**Steps & Formula:**
a. Ensure you have an "Amount" (Revenue) column in the Data sheet: `=G2 * H2`
b. Sum the revenue for 2019-20 for a specific assembly: `=SUMIFS(Amount_Col, Assembly_Col, "Gear Assembly...", FY_Col, "2019-20")`
c. Sum the revenue for 2020-21 for the same assembly.
d. Calculate the percentage jump: `=(Revenue_20_21 - Revenue_19_20) / Revenue_19_20`
e. *Best Practice:* Use a Pivot Table with Assembly in Rows, FY in Columns, and Sum of Amount in Values. Add a calculated column outside the pivot table applying the jump formula `=(Col_20_21 - Col_19_20) / Col_19_20` and find the max.

6. What is the Overall Equipment Effectiveness (OEE) of manufacturing in Week-1(01-04-2022 to 07-04-2022 both days included)? (FLOAT BETWEEN 0 AND 1)**
Hint: OEE of a particular shift can be zero if no production happens.
**Ans.** `0.8008`
**Steps & Formula:**
a. OEE = Availability * Performance * Quality.
b. **Availability:** Week 1 has 21 total shifts (7 days * 3). There are 5 non-operational shifts (zeros in output). `16 / 21` = 0.7619.
c. **Performance:** Total Actual Output (67,587) / (Operational Shifts * Rated Output). `67587 / (16 * 4000)` = 1.0560.
d. **Quality:** (Total Output - Scrap) / Total Output. `(67587 - 322) / 67587` = 0.9952.
e. **Combined Formula:** `=(16/21) * (67587 / (16*4000)) * ((67587-322)/67587)`

7. What is the overall quality of the manufacturing process during the fortnight? (FLOAT BETWEEN 0 AND 1)**
**Ans.** `0.9949`
**Steps & Formula:**
a. Quality = (Total Output for Fortnight - Total Scrap for Fortnight) / Total Output for Fortnight
b. Total output across all 14 days = 152,336
c. Total scrap across all 14 days = 772
d. **Formula:** `=(152336 - 772) / 152336`

8. What is the performance of the manufacturing process during Week-2? (FLOAT BETWEEN 0 AND 1)**
**Ans.** `1.0594`
**Steps & Formula:**
a. Performance = Total Actual Output / (Operational Shifts * Rated Output)
b. Week 2 has 20 operational shifts (1 planned maintenance shift). 
c. Total Actual Output for Week 2 = 84,749
d. **Formula:** `=84749 / (20 * 4000)`

9. What is the average number of parts manufactured per hour during the fortnight?**
Assume that a shift runs for 8 hours and there is no break between shifts. (INTEGER)
(Round down the answer to the nearest whole number. E.g. We can’t have 2.3 parts, so the answer will be 2 parts)
Hint: Exclude non-production time.
**Ans.** `528`
**Steps & Formula:**
a. Find total operational shifts for the fortnight: 16 (Week 1) + 20 (Week 2) = 36 shifts.
b. Find total operational hours: `36 * 8` = 288 hours.
c. Divide total parts by total hours and round down.
d. **Formula:** `=ROUNDDOWN(152336 / 288, 0)`

10. The company uses MAPE (Mean Absolute Percentage Error) to measure process variability in the manufacturing process. Which shift sees the maximum process variability during the fortnight? E.g. Shift 1 (STRING)**
**Ans.** `Shift 3`
**Steps & Formula:**
a. MAPE formula requires finding the absolute error percentage for *each operational shift*: `=ABS(Actual - 4000) / Actual`
b. For Shift 1, calculate the average of these errors excluding days with 0 output: `=AVERAGE(ABS(Actual_Output!B2:B15 - 4000) / Actual_Output!B2:B15)` (Use as an array formula, filtering out 0s).
c. Repeat for Shift 2 and Shift 3. 
d. Shift 3 results in ~8.01% variability, which is the highest.