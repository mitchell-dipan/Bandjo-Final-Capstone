BEGIN TRANSACTION;

DROP TABLE IF EXISTS followers;
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS pictures;
DROP TABLE IF EXISTS band_genre;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS shows;
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
	profile_pic varchar(1000),
	
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
	message_date DATE NOT NULL,
	message_content varchar(1000) NOT NULL,
	band_id INTEGER NOT NULL,
	message_id SERIAL NOT NULL,
	
	CONSTRAINT PK_message_id PRIMARY KEY (message_id),
	CONSTRAINT FK_band_id FOREIGN KEY (band_id) REFERENCES bands(band_id)
);

CREATE TABLE pictures
(
	pic_url varchar(1000) NOT NULL,
	band_id INTEGER NOT NULL,
	picture_id SERIAL NOT NULL,
	
	CONSTRAINT PK_picture_id PRIMARY KEY (picture_id),
	CONSTRAINT FK_band_id FOREIGN KEY (band_id) REFERENCES bands(band_id)
);

CREATE TABLE shows
(
	show_id SERIAL NOT NULL,
	band_id INTEGER NOT NULL,
	show_name varchar(100),
	show_date DATE NOT NULL,
	show_location varchar(100) NOT NULL,
	
	CONSTRAINT PK_show_id PRIMARY KEY (show_id),
	CONSTRAINT FK_band_id FOREIGN KEY (band_id) REFERENCES bands(band_id)
);

CREATE TABLE genres
(
	genre_id SERIAL NOT NULL PRIMARY KEY,
	genre_name varchar(100) NOT NULL
);

CREATE TABLE band_genre
(
	band_id INTEGER NOT NULL,
	genre_id INTEGER NOT NULL,
	
	CONSTRAINT band_id FOREIGN KEY (band_id) REFERENCES bands(band_id),
	CONSTRAINT genre_id FOREIGN KEY (genre_id) 	REFERENCES genres(genre_id),
	PRIMARY KEY(band_id, genre_id)
);

COMMIT TRANSACTION;
