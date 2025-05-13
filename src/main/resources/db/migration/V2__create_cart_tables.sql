CREATE TABLE cart (
                      id binary(16) default(uuid_to_bin(uuid())) PRIMARY KEY,
                      date_created date not NULL default(curdate())
);

CREATE TABLE cart_items (
                            id BIGINT auto_increment PRIMARY KEY,
                            cart_id binary(16) not NULL,
                            product_id BIGINT not NULL,
                            quantity INT default 1 not NULL,
                            CONSTRAINT FOREIGN KEY (cart_id) REFERENCES cart(id) ON DELETE CASCADE,
                            CONSTRAINT FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
                            CONSTRAINT UNIQUE (cart_id, product_id)
);