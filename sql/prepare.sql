INSERT INTO user_entity(address, email, password, role, username)
VALUES ('Ha Dong', 'admin@gmail.com', '123456', 0, 'admin'),
       ('Ha Dong', 'user@gmail.com', '123456', 1, 'user');

INSERT INTO product_entity (id, name, price, seller, preview_image, description)
VALUES (1, 'Áo thun', 100000, 'Jody', 'https://dosi-in.com/images/detailed/42/CDL3_1.jpg', 'Thu xuân 2024'),
       (2, 'Áo sơ mi', 200000, 'Hai Ha', 'https://th.bing.com/th/id/OIP.7pfjWFSXSZdAOHqxL9mtwQHaLG?rs=1&pid=ImgDetMain', 'Fast Fashion'),
       (3, 'Quần jean', 300000, 'Thai Cong', 'https://afamilycdn.com/150157425591193600/2021/3/10/1530306901032039917589237871360618769515325n-16153480368581392256573.jpg', 'description'),
       (4, 'Quần kaki', 400000, '5S Fashion', 'https://laforce.vn/wp-content/uploads/2022/07/phoi-do-voi-quan-jean-nam.jpg', 'description'),
       (5, 'Áo khoác', 500000, 'Chuan men Ha Dong', 'https://laforce.vn/wp-content/uploads/2022/07/phoi-do-voi-quan-jean-nam.jpg', 'description'),
       (6, 'Áo len', 600000, 'Tiktok Shop', 'https://laforce.vn/wp-content/uploads/2022/07/phoi-do-voi-quan-jean-nam.jpg', 'description');
;