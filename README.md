# ORDER MANAGEMENT SYSTEM

Orders' system API to manage products, orders, order lines and customers data.  

Framework: Spark.  

Database: PostgresSql.  
Database migration tool: Flyway.  
Template engine: Handlebars.  
Logging API: SLF4J.  

Used IDE: IntelliJ IDEA 2023.2 EAP.  

## Business logic  
Inserting the new customer also creates the new order with the customer id. On the order page are displayed 
all products. Adding the product to the chart creates the new order line related to the order id.  

## Completed functionalities  
Insert customer data to the database with a simultaneous creation of the new order.  
Insert order line to the database related to the current order.  
Insert, display, update and delete products on products page.  