 -- initial mariadb file for flyway
 -- according to https://mariadb.com/kb/en/string-data-types/ TEXT holds 65k characters
 -- varchar(255) could be replaced with TINYTEXT
 -- foreign keys ref https://mariadb.com/kb/en/foreign-keys/

CREATE TABLE Post (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    creationDate DATE,
    validated BIT
);