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
)
