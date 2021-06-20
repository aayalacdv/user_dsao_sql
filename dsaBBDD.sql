DROP DATABASE IF EXISTS dsa;
CREATE DATABASE dsa;
USE dsa;

CREATE TABLE user (
	idUser VARCHAR(40) PRIMARY KEY NOT NULL,
	name VARCHAR(40) NOT NULL, 
	surname VARCHAR(40) NOT NULL,
	password VARCHAR(40) NOT NULL,
	age INTEGER, 
	player_id VARCHAR(40),
	money FLOAT
)ENGINE = InnoDB; 

CREATE TABLE product ( 
	idProduct VARCHAR(40) PRIMARY KEY NOT NULL,
	price FLOAT,
	urlProduct VARCHAR(100)
)ENGINE = InnoDB; 

CREATE TABLE game (
	idGame VARCHAR(40) PRIMARY KEY NOT NULL,
	date DATE,
	duration FLOAT,
	levels_passed INTEGER, 
	player_id VARCHAR(40),
	FOREIGN KEY (player_id) references user(idUser)
)ENGINE = InnoDB; 

CREATE TABLE userProducts (
	id_User VARCHAR(40) NOT NULL,
	id_Product VARCHAR(40) NOT NULL,
	cantidad INTEGER NOT NULL,
	FOREIGN KEY (id_User) REFERENCES user(idUser),
	FOREIGN KEY (id_Product) REFERENCES product(idProduct)
)ENGINE = InnoDB;

CREATE TABLE userGames (
	idUserGame VARCHAR(40) NOT NULL,
	id_Game VARCHAR(40) NOT NULL,
	FOREIGN KEY (idUserGame) REFERENCES user(idUser),
	FOREIGN KEY (id_Game) REFERENCES game(idGame)
)ENGINE = InnoDB;

CREATE TABLE unityGame (
	level INTEGER NOT NULL,
	prefab VARCHAR(40) NOT NULL
)ENGINE = InnoDB;

INSERT INTO user VALUES('lau','Laura', 'Nuez', 'asdf', 22, '' , 1000);
INSERT INTO product VALUES('vida', 400, 'http://10.0.2.2:8080/pociones/vida.png' );
INSERT INTO product VALUES('resistencia', 400, 'http://10.0.2.2:8080/pociones/resistencia.png' );
INSERT INTO product VALUES('velocidad', 400, 'http://10.0.2.2:8080/pociones/velocidad.png' );

INSERT INTO userProducts  VALUES ("lau", 'vida', 3);
INSERT INTO userProducts  VALUES ("lau", 'resistencia', 3);
INSERT INTO userProducts  VALUES ("lau", 'velocidad', 3);

INSERT INTO unitygame VALUES ( 1 , 'Prefabs/Level1');
INSERT INTO unitygame VALUES ( 2 , 'Prefabs/Level2');
INSERT INTO unitygame VALUES ( 3 , 'Prefabs/Level3');
INSERT INTO unitygame VALUES ( 4 , 'Prefabs/Level4');
