CREATE SCHEMA IF NOT EXISTS wishlist;
SET SCHEMA wishlist;


DROP TABLE IF EXISTS wishes_per_wishlist;
DROP TABLE IF EXISTS wishlist;
DROP TABLE IF EXISTS wish;
DROP TABLE IF EXISTS users;


CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       email VARCHAR(255) NOT NULL,
                       username VARCHAR(100) NOT NULL,
                       password VARCHAR(255) NOT NULL
);


CREATE TABLE wishlist (
                           wishlist_id INT AUTO_INCREMENT PRIMARY KEY,
                           user_id INT NOT NULL,
                           wishlist_name VARCHAR(100) NOT NULL,
                           FOREIGN KEY (user_id)
                               REFERENCES users(user_id)
                               ON DELETE CASCADE
);


CREATE TABLE wish (
                      wish_id INT AUTO_INCREMENT PRIMARY KEY,
                      wish_name VARCHAR(100) NOT NULL,
                      wish_price DOUBLE,
                      wish_link VARCHAR(255),
                      is_reserved BOOLEAN DEFAULT false
);


CREATE TABLE wishes_per_wishlist (
                                     wishlist_id INT NOT NULL,
                                     wish_id INT NOT NULL,
                                     PRIMARY KEY (wishlist_id, wish_id),
                                     FOREIGN KEY (wishlist_id)
                                         REFERENCES wishlist(wishlist_id)
                                         ON DELETE CASCADE,
                                     FOREIGN KEY (wish_id)
                                         REFERENCES wish(wish_id)
                                         ON DELETE CASCADE
);


INSERT INTO users(email, username, password)
VALUES ('adminEmail@email.com', 'admin', 'admin');

INSERT INTO wishlist(user_id, wishlist_name)
VALUES (1, 'test');

INSERT INTO wish(wish_name, wish_price, wish_link)
VALUES ('test1', 192.232, 'google.com');

INSERT INTO wishes_per_wishlist(wishlist_id, wish_id)
VALUES (1, 1);
