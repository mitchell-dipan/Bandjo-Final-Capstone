BEGIN TRANSACTION;

DROP TABLE IF EXISTS followers;
DROP TABLE IF EXISTS bands;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE bands
(
	name varchar(100) NOT NULL,
	description varchar(1000),
	members varchar(1000) NOT NULL,
	user_id INTEGER NOT NULL,
	band_id SERIAL NOT NULL,
	
	CONSTRAINT PK_band_id PRIMARY KEY(band_id),
	CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE followers
(
	user_id INTEGER NOT NULL,
	band_id INTEGER NOT NULL,
	
	CONSTRAINT FK_band_id FOREIGN KEY (band_id) REFERENCES bands(band_id),
	CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
	PRIMARY KEY(band_id, user_id)
);

CREATE TABLE messages
(
	
);

COMMIT TRANSACTION;
