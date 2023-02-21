DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS wish_list;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS order_products;


CREATE TABLE customers (
    id int(11) unsigned NOT NULL AUTO_INCREMENT,
    first_name varchar(20) NOT NULL,
    last_name varchar(20) NOT NULL,
    email varchar(50) NOT NULL,
    phone_number varchar(20) NOT NULL,
    address varchar(50) NOT NULL,
    username varchar(50) NOT NULL,
    password varchar(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE products (
    id int(11) unsigned NOT NULL AUTO_INCREMENT,
    name varchar(300) NOT NULL,
    view_description varchar(max) NOT NULL,
    full_description varchar(max) NOT NULL,
    price double(11) NOT NULL,
    img varchar(300) NOT NULL,
    quantity int(11) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE wish_list (
    id int(11) unsigned NOT NULL AUTO_INCREMENT,
    customer_id int(11) NOT NULL,
    product_id int(11) NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (product_id) REFERENCES products(id),
);

create TABLE orders (
    id int(11) unsigned NOT NULL AUTO_INCREMENT,
    customer_id int(11) NOT NULL,
    order_date DATE,
    country varchar(300),
    city varchar(300),
    phone_number varchar(300),
    total_products int(11),
    total_price int(11),
    status varchar(300) DEFAULT 'OPEN',

    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customers(id),
);

create TABLE order_products (
    id int(11) unsigned NOT NULL AUTO_INCREMENT,
    customer_id int(11) NOT NULL,
    order_id int(11),
    product_id int(11) NOT NULL,
    quantity int(11),
    price int(11),

    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (product_id) REFERENCES products(id),
);

INSERT INTO customers (first_name, last_name, email, phone_number, address, username, password) VALUES
    ('majd', 'kanaan', 'majd.kannan@gmail.com', '0542071371', 'Tel-Aviv, Israel', 'MajdKa', 'password');

INSERT INTO products (name, view_description, full_description, price, img, quantity) VALUES
    ('Sunglasses', 'The Famous Sunglasses every body wants', 'This Sunglasses is The best in the world, developed by scientist in israel ' , 98  , 'https://media.istockphoto.com/id/1389923088/photo/beautiful-beach-with-sunglasses-and-hat-on-the-beach.jpg?s=1024x1024&w=is&k=20&c=KaYW7l7uiJsDmj7UJJCYS6_fVxmHdtyVQd-lDOm6ehg=', 14),
    ('Comfy chair', 'The perfect chair for your chill at home' ,'The Comfy Chair is the ultimate choice for your living room and now in one time price make sure you get your own before its over from our stock ' , 125.50 , 'https://media.istockphoto.com/id/1347867391/photo/happy-caucasian-woman-using-cellphone-chatting-on-internet-working-or-studying-online-sitting.jpg?s=1024x1024&w=is&k=20&c=BLmck5OkieJCG0RCi80F2NItAsJkslFqGcK5BfUPJy8=', 1),
    (' The Magical Tent', 'Do you love camping, you should have one', 'The Magic tent will make you warm and comfortable when you go out in a camping trip' , 79.90 ,'https://media.istockphoto.com/id/904960682/photo/camping-in-a-tent-under-the-stars-and-milky-way-galaxy.jpg?s=1024x1024&w=is&k=20&c=J365n3hJKyI4lMLfolm0yLK45IyWmMVUhR2jetNY1cQ=',8),
    ('Running shoes', 'very comfortable shoes for your run', 'Do you love to run, or you just thinking about start a healthy lifestyle, the Running shoes is the ultimate choice for you.' , 35 , 'https://media.istockphoto.com/id/1291035009/vector/sneakers-shoes-fitness-footwear-for-sport-running-and-training-colorful-modern-shoe-designs.jpg?s=612x612&w=0&k=20&c=TMb4msfxhPsnqVuVahvw0L1q8kcT7xOtLVBw-WtlGEk=', 25),
    ('backpack to be back at school', 'very comfy nice and good price backpack for you or your kids ', 'you want your kids to be comfortable and happy going back to school, buy him the hole new backpack you will only find at this website' , 25, 'https://media.istockphoto.com/id/1339055637/photo/back-to-school-background-stationery-supplies-in-the-school-bag-banner-design-education-on.jpg?s=1024x1024&w=is&k=20&c=VwomGmfd6wgE7rC5BmY6IQ5BGglnu9qh00_bfpPK7Us=', 220),
    (' Chef Knife', 'great value for the handmade chef knife', 'if you love cocking this knife will help make your meals even better' , 22.90, 'https://media.istockphoto.com/id/874095794/photo/chef-knife.jpg?s=1024x1024&w=is&k=20&c=wplObBexz8Eq9bUSmbcJ6cjjqZhvbjCM9pU_LqkTzqY=', 32),
    ('Kitchen wood table', 'large comfortable and low price kitchen table ', 'this table will make you and every body else love siting to eat around it' , 125.50, 'https://media.istockphoto.com/id/1159400036/photo/kitchen-counter-with-empty-wall.jpg?s=1024x1024&w=is&k=20&c=bPUFr4QNhWrOGqRZIna2EEtvlqwzbUZt30sQSVL6QMQ=', 11),
    ('Coffee making machine', 'The machine that will make you drink coffee at house', 'High quality coffee in your house after you buy this machine' , 300, 'https://media.istockphoto.com/id/1314140895/photo/close-up-view-of-young-woman-making-coffee-with-coffee-machine-during-office-break-time.jpg?s=1024x1024&w=is&k=20&c=dx0qSi0mW2AybxtRpPYMKM669X16hU1gm-6gAVMb6aA=',5),
    ('Shopping bags', 'the environment friendly bags', 'This bags can be usd fo long time it will make you help with saving the planet and its for a very low price, available with different colors ' , 1.50, 'https://media.istockphoto.com/id/1302448412/vector/canvas-bag-mockup-of-fabric-tote-cloth-totebag-with-handle-template-of-black-and-white.jpg?s=1024x1024&w=is&k=20&c=CFlv0v7vTZUZ5QKhNFJNvZgnb2sJBn5mL6qRp8j9N_g=',150),
    ('Toaster Oven', 'This Toaster oven wll make your life much easier', 'you can use this oven to warm food or to cck in it, you can olsr use for backing cookies' , 179.09, 'https://media.istockphoto.com/id/1291871285/photo/croissant-was-take-out-from-mini-oven-ready-to-serve-morning-breakfast-concept.jpg?s=1024x1024&w=is&k=20&c=QLhGyo1U8-zrUUzroryIxAHP2opoGFlhLXPbyV4vSPU=', 8);

