# ORDER MANAGEMENT SYSTEM

Orders' system API to manage products, orders, order lines and customers data.  

Framework: Spark.  

Database: PostgresSql.  
Database migration tool: Flyway.  
Template engine: Handlebars.  
Logging API: SLF4J.  

Used IDE: IntelliJ IDEA 2023.2 EAP.  

## Business logic  
Inserting (register) the new customer data also creates the new order related to the customer by id. On the order page 
are displayed all products. Adding the product to the cart creates the new order line related to the 
order and product by their id. It's possible to add new product to the cart by taken to the order page by 
nav bar Orders link. Just ordered product and the list of all ordered products are displayed on the 
order line page.  

## Completed functionalities  
To insert customer data to the database with a simultaneous creation of the new order.  
To insert order line to the database related to the current order.  
To display just ordered product and the list of all ordered products.  
To insert, display, update and delete products on products page.  