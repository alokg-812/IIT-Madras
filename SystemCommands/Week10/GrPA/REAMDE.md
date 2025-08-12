# GRPA 1
- Write a Bash script to reconstruct the Shopping Bill dataset from the OCR data files and store it in a separate file for each card.
- The reconstructed dataset should contain one file for each card with the `name` `shopping_bill_<card_number>.txt`
- `<card_number>` is a number of the card e.g. '1', '4', '12', '24' etc.
- The generated files should contain the data in the format given below.
```
SHOPNAME:CUSTOMER_NAME:CARD_NO
Item:Category:Qty:Price:Cost
Carrots:Vegetable/Food:1.5:50:75
```
- Where information like shopname, category, item. cost etc. can be fetched from their respective `ocr_.txt` files.
- Or you can find all the information in a single file named `ocr_full.txt`
- An example output for the output file shopping_bill_1.txt is given below.
```
SV Stores:Srivatsan:1
Item:Category:Qty:Price:Cost
Carrots:Vegetables/Food:1.5:50:75
Soap:Toiletries:4:32:128
Tomatoes:Vegetables/Food:2:40:80
Bananas:Vegetables/Food:8:8:64
Socks:Footwear/Apparel:3:56:168
Curd:Dairy/Food:0.5:32:16
Milk:Dairy/Food:1.5:24:36
```
- [Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week10/GrPA/GrPA1.bash)
