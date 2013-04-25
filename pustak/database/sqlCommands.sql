
CREATE TABLE books
(
                 isbn VARCHAR not NULL,
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
  isbn text,
  status text,
  booktype text,
  FOREIGN KEY(isbn) REFERENCES books(isbn)
);
