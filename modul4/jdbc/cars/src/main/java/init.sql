DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    first_name varchar(255),
    last_name varchar(255),
    address varchar(255)
);

CREATE TABLE cars (
    id bigint AUTO_INCREMENT,
    user_id bigint,
    made varchar(255),
    model varchar(255),
    number varchar(255),
    color varchar(255),
    FOREIGN KEY (user_id) references users(id)
);

INSERT INTO users (id, first_name, last_name, address) VALUES
(1, 'Raine', 'Wrennall', '08 Arkansas Plaza'),
(2, 'Geri', 'Wratten', '4802 Fordem Plaza'),
(3, 'Darren', 'Fitchet', '3269 Sundown Circle'),
(4, 'Shandy', 'Elsy', '07 Canary Road'),
(5, 'Fernanda', 'Safe', '8190 Autumn Leaf Crossing'),
(6, 'Rosamond', 'Gaukrodge', '81425 Shopko Pass'),
(7, 'Orrin', 'Boscott', '558 Village Green Center'),
(8, 'Sunshine', 'Fairbourn', '48395 Graedel Parkway');


insert into cars (id, user_id, made, model, number, color) values
(1, 2, 'Mitsubishi', 'nbf12', 'Tredia', 'Orange'),
(2, 1, 'Ford', 'E350', '123ab', 'Indigo'),
(3, 4, 'Buick', 'Century', 'dc567', 'Red'),
(4, 6, 'Pontiac', '6000', 'fgt67', 'Crimson'),
(5, 8, 'Ford', 'Taurus', 'nm759', 'Pink'),
(6, 1, 'Ford', 'EXP', 'lkr23', 'Aquamarine'),
(7, 2, 'Saab', '900', '12345', 'Pink'),
(8, 5, 'Mercury', 'Mariner', 'grtg', 'Indigo'),
(9, 3, 'Chevrolet', 'Tahoe', '13574', 'Maroon'),
(10, 7, 'Audi', 'Quattro', '542f', 'Pink');