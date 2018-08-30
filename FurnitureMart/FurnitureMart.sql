-- Chandrakanth yela 

-- create database muscle----
Drop database if exists muscle;
Create Database muscle;
use muscle;

 -- table in the database muscle-----
 Create table User(email varchar(20) not null,firstname varchar(20) not null,
		lastname varchar(20), phonenumber char(13),password varchar(100),salt varchar(1000),image mediumblob,primary key(email));
        
Create table Admin(email varchar(20) not null,firstname varchar(20) not null,
		lastname varchar(20), phonenumber char(13),password varchar(100),salt varchar(1000),image mediumblob,gender varchar(10), birthdate varchar(20), primary key(email));

select * from Product;
desc Product;

desc User;
Drop table Admin;
Create table Admin(email varchar(20) not null,firstname varchar(20) not null,
		lastname varchar(20), phonenumber char(13),password varchar(15),primary key(email));
Create table Address(address varchar(40) not null,
		city varchar(20), state varchar(15), zipcode char(10),email varchar(20), primary key(address,email)
        ,foreign key(email) References User(email));
        
Create table Product(productId varchar(10) not null, description varchar(150), productname varchar(20), productprice float, 
			quantity int, productImage mediumblob,primary key (productId));
   
-- buffer table for storing cart items
Create table BufCArt(productId varchar(10) not null, email varchar(20),price varchar(20),productname varchar(20), dateAdded date,quantity varchar(20),
 foreign key(productId) references Product(productId),
        foreign key (email) references User(email));


 -- OrderPlaced Table
 Create table OrderPlaced (orderId varchar(10) not null, 
			email varchar(20) not null, totalCost float, orderDate date,orderStatus varchar(10),
            primary key(orderId)
					,foreign key (email) references User(email));
                   
 -- CartItems Table
 
Create table CartItems(productId varchar(10) not null, quantity int , 
					dateAdded date, orderId varchar(20),price varchar(20),
        foreign key (orderId) references OrderPlaced(orderId),foreign key (productId) references Product(productId));

select email,firstname,lastname,phonenumber,password,salt from User;

Delete from BufCArt where productId='20351' and email="test1@gmail.com";

select * from OrderPlaced Join address on orderplaced.email = address.email;

select Product.productname , Product.productprice ,cartitems.quan 
from  Product
 inner join cartitems on Product.productId = cartitems.productId
 inner join orderplaced on orderplaced.orderId = cartitems.orderId
 where email="test1@gmail.com" and orderplaced.orderId=81;

ALTER TABLE cartitems CHANGE quantity quan int;

UPDATE BufCArt SET quantity ="7" WHERE productId=123 AND email='koy@g.com';
select productId,productname,price,dateAdded,quantity from BufCart where email='koy@g.com';
Select count(email), count(firstname), count(lastname) from User as test;


Create table testUser(id varchar(20) not null,name varchar(20) not null,
		profession varchar(20));
Insert into testUser(id,name,profession) values (3,"Khaleesi","Targerian");
select * from testUser;
desc testuser;

