INSERT INTO hotel
("location", "name")
VALUES('São Bernardo do Campo', 'Pampas');
INSERT INTO hotel
("location", "name")
VALUES('São Paulo', 'Intercity');
INSERT INTO hotel
("location", "name")
VALUES('São Caetano', 'Confort');

INSERT INTO facility
("name")
VALUES('WIFI');
INSERT INTO facility
("name")
VALUES('Piscina');
INSERT INTO facility
("name")
VALUES('Estacionamento Gratuito');
INSERT INTO facility
("name")
VALUES('Café da manhã');

INSERT INTO room
(hotel_id, id, max_guests, price, status)
VALUES(1, 1, 3, 200.0, 'AVAILABLE');
INSERT INTO room
(hotel_id, id, max_guests, price, status)
VALUES(2, 2, 5, 400.0, 'AVAILABLE');
INSERT INTO room
(hotel_id, id, max_guests, price, status)
VALUES(3, 3, 2, 150.0, 'AVAILABLE');