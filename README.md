# ChequePrintProject

Console Project for printing cheques + RESTful Spring cheque printing functionality backend API

### The project stack:

- Java 17 and Gradle 7.5;
- Spring 6 & Spring Boot 3;
- Spring Data JPA Repositories;
- DB: PostgreSQL with 2 tables (product и discount_card);
- DB connection pool: Hikari;
- DB migration: Flyway;
- JUnit 5 tests;
- Additional libraries: Lombok & Apache Commons;
- Git & GitHub.

### Instructions:

1) The app consists of console and Spring Boot Rest API parts.
2) Console part starts through CheckRunner. Parameters shall
   be delivered through arguments of main method (Run/Edit configurations). There are 2 allowable input patterns to
   start this ap part working:
   (a) java CheckRunner <product_cart> card-<DiscountCard_Id> cashier-<Cashier_Id>
   Product cart may consist of any number of goods key-value pairs patterned as <product_id>-<quantity>, divided by
   single spaces. No product id shall repeat.
   Input pattern example: "java CheckRunner 1-7 2-5 3-1 4-1 5-1 6-1 7-1 card-1 cashier-1001"
   (b) java file <file_path>
   The app will read command lines from your Txt file by the path indicated. Command lines shall conform (a)-pattern
   above. You can put any number of command lines. Each of them will be proceeded.
   Input pattern example: "java file D:\ChequePrintProject\src\main\resources\Cheques.txt"
3) Spring Boot API part shall be started through ChequePrintProjectApplication boot starter. The requests are to be
   provided in JSON format in the body of Post requests via 'http://localhost:8081/cheque' endpoint.
   You shall refer Map<Long, Integer> products (where Long is for Product Id and Integer is for quantity of products),
   Long cardId (for Discount card Id) and Long cashierId (for cashier Id). Request may be proceeded e.g. by the means of
   your Frontend, Postman or Intellij IDEA generated requests.

Example:

{
"products": {
"1": 2,
"2": 3,
"3": 4
},
"cardId": 1,
"cashierId": 1001
}

5) Console requests will be printed both to console and to file Cheques.txt (shall be added to the cheques band) in the
   app resources folder.
6) Spring Boot Rest API request will return JSON Response with all information allowable in printed console app cheque.

Response format example:

{
"title": "CASH RECEIPT",
"storeName": "DrumsticksStore#1",
"address": "Minsk, Herearound Str., 111-222",
"phoneNumber": "tel. +375(11)222-33-44",
"cashierNumber": 1001,
"date": "2022-12-21",
"time": "09:15:29.0345229",
"positions": [
{
"qty": 2,
"description": "Vic Firth drumsticks 2B",
"price": 14.0,
"positionTotal": 28.0
},
{
"qty": 3,
"description": "Vic Firth drumsticks 2BN",
"price": 14.1,
"positionTotal": 42.3
},
{
"qty": 4,
"description": "Vic Firth drumsticks 5B",
"price": 14.1,
"positionTotal": 56.4
}
],
"sum": 126.69,
"promoDiscount": 0.0,
"cardDiscount": 3.80,
"taxable": 102.42,
"vat": 20.48,
"total": 122.90,
"ad": "*** Place your ad here ***"
}

7) PostgreSQL settings as well as other necessary Spring Boot settings are listed in application.properties file.
8) DB init script is described by Flyway migration script in resources.db.migration.V1_1__initial_script.sql.
9) DB may be filled by test product and discount card objects. You may use script in resources.DB_insert_entities.sql
   for it.

### Printed Cheque Example:
![This is an image](https://ibb.co/SXFv42X)

### DB Diagram:
![This is an image](https://ibb.co/z8g3Wfj)
