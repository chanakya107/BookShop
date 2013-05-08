
CREATE TABLE books
(
                 isbn VARCHAR,
                 title VARCHAR(255) not NULL,
                 author1 VARCHAR(255) not NULL,
                 author2 VARCHAR(255),
                 price INTEGER not NULL,
                 newbookquantity INTEGER,
                 usedbookquantity INTEGER,
                 PRIMARY KEY ( isbn )
);
CREATE TABLE orders
(
  orderid INTEGER Primary key AUTOINCREMENT,
  customername text,
  email text,
  phonenumber text,
  address text,
  pincode text,
  date DATETIME,
  isbn text ,
  status text,
  booktype text,
  FOREIGN KEY(isbn) REFERENCES books(isbn)
);
insert into books values('1234567890','One week with her','Prince','Jain',250,1,0);
insert into books values('1234567891','Life is what you make it','Preety','Shenoy',200,2,1);
insert into books values('1234567892','I too have a love story','Ravindar','',150,2,1);
insert into books values('1234567893','Revolution 2020','A.P.J.Abdul Kalam','',300,5,3);
insert into books values('1234567894','Talkative Man','R.K.Narayan','',250,0,5);

insert into orders values(1,'Prince','connect2princejain@gmail.com','8050612996','Electronic City',123106,'25-04-2013 12:54:33','1234567890','pending','New');
insert into orders values(2,'Neha','neharastogi093@gmail.com','8930349807','Electronic City',123106,'24-04-2013 01:23:44','1234567891','pending','Used');
insert into orders values(3,'Melanie','melanie@gmail.com','8930349807','Hosa Road',123116,'23-04-2013 04:23:44','1234567892','pending','New');
insert into orders values(4,'Pranav','pranav@gmail.com','8930347897','Electronic City',123106,'21-04-2013 11:23:44','1234567893','pending','Used');
insert into orders values(5,'Deepthi','deepthi@gmail.com','8930349807','Electronic City',123106,'20-04-2013 10:20:44','1234567894','pending','Used');


