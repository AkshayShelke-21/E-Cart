//Creating user Table
CREATE TABLE user(
user_id int(10)NOT NULL AUTO_INCREMENT PRIMARY KEY,
is_admin int NOT NULL,
user_name varchar(255)NOT NULL,
user_password varchar(255)NOT NULL
)

//Creating cart Table
CREATE TABLE cart(
cart_id int(10)NOT NULL AUTO_INCREMENT PRIMARY KEY,
user_id int(10)NOT NULL,
product_id int(10)NOT NULL,
product_qty int(20)DEFAULT NULL,
total_price float NOT NULL,
FOREIGN KEY(user_id)REFERENCES user(user_id),
FOREIGN KEY(product_id)REFERENCES product(product_id),
)

//Creating product Table
CREATE TABLE product(
product_id int(10)NOT NULL AUTO_INCREMENT PRIMARY KEY,
produvt_name varchar(255)NOT NULL,
product_description(255)DEFAULT NULL,
product_price float NOT NULL,
product_qty int(20)NOT NULL
)

//Creating product order_details
CREATE TABLE order_details(
order_id int(10)NOT NULL AUTO_INCREMENT PRIMARY KEY,
user_id int(10)NOT NULL,
product_id int(10)NOT NULL,
product_qty int(20)NOT NULL,
order_price float NOT NULL
FOREIGN KEY(product_id)REFERENCES product(product_id),
FOREIGN KEY(user_id)REFERENCES user(user_id)
)