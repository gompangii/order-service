CREATE TABLE orders (
	id                  INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	book_isbn           VARCHAR(255) NOT NULL,
	book_name           VARCHAR(255),
	book_price			DOUBLE,
	quantity            INT NOT NULL,
	`status`            VARCHAR(255) NOT NULL,
	created_date        TIMESTAMP NOT NULL,
    last_modified_date  TIMESTAMP NOT NULL,
	`version`           INT NOT NULL
);