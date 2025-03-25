# 보안을 위한 스키마 변경(mariadb 기준)
DROP TABLE orders;

CREATE TABLE orders (
    id                  INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	book_isbn           VARCHAR(255) NOT NULL,
	book_name           VARCHAR(255),
	book_price          DOUBLE,
	quantity  			INT,
	`status`  			VARCHAR(255) NOT NULL,
	created_date        TIMESTAMP NOT NULL,
    last_modified_date  TIMESTAMP NOT NULL,
    created_by 			VARCHAR(255),
	last_modified_by    VARCHAR(255),
	`version`           INT NOT NULL
);