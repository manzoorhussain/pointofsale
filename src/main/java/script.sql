nCREATE DATABASE pointofsale;

CREATE TABLE `category` (
	`cat_id` VARCHAR (150),
	`cat_name` VARCHAR (300),
	`cat_desc` VARCHAR (1500),
	`cat_status` VARCHAR (2),
	`created_date` DATETIME ,
	`created_by` VARCHAR (300),
	`modified_date` DATETIME ,
	`modified_by` VARCHAR (300),
	CONSTRAINT PK_CAT PRIMARY KEY (cat_id)
); 


CREATE TABLE `PRODUCT` (
	`cat_id` VARCHAR (150),
	`prod_id` VARCHAR (150),
	`prod_name` VARCHAR (300),
	`prod_desc` VARCHAR (1500),
	`prod_status` VARCHAR (2),
	`created_date` DATETIME ,
	`created_by` VARCHAR (300),
	`modified_date` DATETIME ,
	`modified_by` VARCHAR (300),
	CONSTRAINT PK_PROD PRIMARY KEY (cat_id,prod_id),
	FOREIGN KEY (cat_id) REFERENCES category(cat_id)
); 


CREATE TABLE USERS (
	`user_code` VARCHAR (150),
	`user_id` VARCHAR (300),
	`user_name` VARCHAR (500),
	`user_password` VARCHAR (500),
	`user_status` VARCHAR (1),
	`created_date` DATETIME ,
	`created_by` VARCHAR (300),
	`modified_date` DATETIME ,
	`modified_by` VARCHAR (300),
	CONSTRAINT PK_USER PRIMARY KEY (user_code)
); 
ALTER TABLE USERS ADD user_image LONGBLOB;

CREATE TABLE USERS_AUTHERATON (
	`SR_NO` INT(5) NOT NULL AUTO_INCREMENT,
	`user_code` VARCHAR (300),
	`user_id` VARCHAR (500),
	`user_token` VARCHAR (500),
	CONSTRAINT PK_USERS_AUTHERATON PRIMARY KEY (SR_NO)
); 

ALTER TABLE product ADD prod_price VARCHAR(100);

ALTER TABLE PRODUCT ADD prod_image LONGBLOB;



CREATE TABLE ORDER_HISTORY (
	`SR_NO` INT(5) NOT NULL AUTO_INCREMENT,
	`ord_code` VARCHAR (150),
	`ord_desc` VARCHAR (1000),
	`ord_year` VARCHAR (10),
	`ord_month` VARCHAR (10),
	`ord_date` VARCHAR (10),
	`ord_created` VARCHAR (10),
	CONSTRAINT PK_ORDER PRIMARY KEY (SR_NO)
);
