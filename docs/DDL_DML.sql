DROP DATABASE IF EXISTS wishlist;
CREATE DATABASE wishlist;
USE wishlist;

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
                      wish_link VARCHAR(255)
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



insert into wishlist.users(email, username, password)
    value ("adminEmail@email.com", "admin","admin");

insert into wishlist.wishlist(user_id, wishlist_name)
    value (1, "test");

insert into wishlist.wish(wish_name, wish_price, wish_link)
    value ("test1", 192.232,"google.com");

insert into wishlist.wishes_per_wishlist(wishlist_id, wish_id)
    value (1,1);