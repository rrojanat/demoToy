DROP TABLE IF EXISTS Toy;
DROP TABLE IF EXISTS Gender;
DROP TABLE IF EXISTS Age;
DROP TABLE IF EXISTS Cart;
DROP TABLE IF EXISTS CartDetail;
CREATE TABLE IF NOT EXISTS Toy (toyId INTEGER PRIMARY KEY, name VARCHAR(100), genderId INTEGER, ageId INTEGER, price DECIMAL(7, 2), brand VARCHAR(20), qty INTEGER, toyImg VARCHAR(200));
CREATE TABLE IF NOT EXISTS Gender (genderId INTEGER PRIMARY KEY, description VARCHAR(20));
CREATE TABLE IF NOT EXISTS Age (ageId INTEGER PRIMARY KEY, description VARCHAR(20));
CREATE TABLE IF NOT EXISTS Cart (cartId INTEGER IDENTITY PRIMARY KEY, subTotal DECIMAL(7, 2), shoppingFee DECIMAL(5, 2), total DECIMAL(7, 2), shoppingName VARCHAR(200), addr1 VARCHAR(200), addr2 VARCHAR(200), city VARCHAR(100), province VARCHAR(100), postcode VARCHAR(5), email VARCHAR(100));
CREATE TABLE IF NOT EXISTS CartDetail (cartDetailId INTEGER IDENTITY PRIMARY KEY, cartId INTEGER, toyId INTEGER, qty INTEGER, detailPrice DECIMAL(7, 2));
INSERT INTO Toy values(1, 'Balance Training Bicycle', 3, 3, 119.95, 'SportsFun', 20, '/img/1.jpg');
INSERT INTO Toy values(2, '43 Piece Dinner Set', 1, 2, 12.95, 'CoolKidz', 20, '/img/2.jpg');
INSERT INTO Toy values(3, 'Horses and Unicorns Set', 3, 2, 24.95, 'CoolKidz', 20, '/img/3.jpg');
INSERT INTO Gender values(1, 'Female');
INSERT INTO Gender values(2, 'Male');
INSERT INTO Gender values(3, 'Neutral');
INSERT INTO Age values(1, 'Baby');
INSERT INTO Age values(2, 'Toddler');
INSERT INTO Age values(3, '3_to_5');
INSERT INTO Age values(4, '6_to_8');
INSERT INTO Age values(5, 'Over8');
INSERT INTO Cart values(null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO CartDetail values(null, 1, 1, 2, null);
INSERT INTO CartDetail values(null, 1, 2, 1, null);
INSERT INTO CartDetail values(null, 1, 3, 1, null);