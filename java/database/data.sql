BEGIN TRANSACTION;
INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('mitch','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('matt','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('jon','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('sam','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('lei','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('alex','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('sarah','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('mike','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('jack','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
COMMIT;



BEGIN TRANSACTION;
INSERT INTO bands(name, description, members, user_id, profile_pic) VALUES ('Led Zeppelin', 'John Bonham, Jimmy Page', 'Cool dudes playing good music', (SELECT user_id FROM users WHERE username = 'jack'),'https://extrachill.com/wp-content/uploads/2021/03/led-zeppelin-logos.jpeg');
INSERT INTO bands(name, description, members, user_id, profile_pic) VALUES ('The Beatles', 'John Lennon, Ringo Star', 'Old People with basic music', (SELECT user_id FROM users WHERE username = 'matt'),'https://cdn.mos.cms.futurecdn.net/PiGbPUZRaSxnGqK582evgm-1200-80.jpg');
INSERT INTO bands(name, description, members, user_id, profile_pic) VALUES ('Grateful Dead', 'Jerry Garcia', 'Jam band galor', (SELECT user_id FROM users WHERE username = 'mitch'), 'https://logos-world.net/wp-content/uploads/2021/08/Grateful-Dead-Logo.png');
COMMIT;


BEGIN TRANSACTION;
INSERT INTO messages (message_date, message_content, band_id) VALUES ('12/05/22', 'Cannot believe we are so good at music', (SELECT band_id FROM bands WHERE name = 'Led Zeppelin'));
INSERT INTO messages (message_date, message_content, band_id) VALUES ('12/10/22', 'Why do all these kids think we are overrated?', (SELECT band_id FROM bands WHERE name = 'The Beatles'));
INSERT INTO messages (message_date, message_content, band_id) VALUES ('11/11/22', 'let us jam until we become jelly', (SELECT band_id FROM bands WHERE name = 'Grateful Dead'));
INSERT INTO messages (message_date, message_content, band_id) VALUES ('12/25/21', 'Merry Christler', (SELECT band_id FROM bands WHERE name = 'Led Zeppelin'));
INSERT INTO messages (message_date, message_content, band_id) VALUES ('01/14/12', 'Still cannot believe people call us overrated', (SELECT band_id FROM bands WHERE name = 'The Beatles'));
INSERT INTO messages (message_date, message_content, band_id) VALUES ('01/13/12', 'The beatles are overrated', (SELECT band_id FROM bands WHERE name = 'Grateful Dead'));
INSERT INTO messages (message_date, message_content, band_id) VALUES ('12/05/22', 'Cannot believe we are so good at music', (SELECT band_id FROM bands WHERE name = 'Led Zeppelin'));
INSERT INTO messages (message_date, message_content, band_id) VALUES ('09/12/20', 'Covid cant stop us', (SELECT band_id FROM bands WHERE name = 'Grateful Dead'));
INSERT INTO messages (message_date, message_content, band_id) VALUES ('08/06/17', 'Life is pretty cool', (SELECT band_id FROM bands WHERE name = 'Led Zeppelin'));
INSERT INTO messages (message_date, message_content, band_id) VALUES ('10/09/14', 'People who think all the time, have nothing to think about except thoughts', (SELECT band_id FROM bands WHERE name = 'The Beatles'));
COMMIT;


BEGIN TRANSACTION;
INSERT INTO followers(user_id, band_id) VALUES((SELECT user_id FROM users WHERE username = 'mitch'), (SELECT band_id FROM bands WHERE name = 'Led Zeppelin'));
INSERT INTO followers(user_id, band_id) VALUES((SELECT user_id FROM users WHERE username = 'mitch'), (SELECT band_id FROM bands WHERE name = 'The Beatles'));
INSERT INTO followers(user_id, band_id) VALUES((SELECT user_id FROM users WHERE username = 'matt'), (SELECT band_id FROM bands WHERE name = 'Grateful Dead'));
INSERT INTO followers(user_id, band_id) VALUES((SELECT user_id FROM users WHERE username = 'jon'), (SELECT band_id FROM bands WHERE name = 'Led Zeppelin'));
INSERT INTO followers(user_id, band_id) VALUES((SELECT user_id FROM users WHERE username = 'jon'), (SELECT band_id FROM bands WHERE name = 'Grateful Dead'));
COMMIT;

BEGIN TRANSACTION;
INSERT INTO pictures(band_id, pic_url) VALUES((SELECT band_id FROM bands WHERE name = 'Led Zeppelin'), 'https://www.rollingstone.com/wp-content/uploads/2012/11/LedZeppelin.jpg');
INSERT INTO pictures(band_id, pic_url) VALUES((SELECT band_id FROM bands WHERE name = 'Led Zeppelin'), 'https://www.udiscovermusic.com/wp-content/uploads/2020/11/Led-Zeppelin-GettyImages-84848509-1000x600.jpg');
INSERT INTO pictures(band_id, pic_url) VALUES((SELECT band_id FROM bands WHERE name = 'Led Zeppelin'), 'https://townsquare.media/site/366/files/2021/04/robert-plant-jimmy-page-led-zeppelin-1975.jpg?w=1200&h=0&zc=1&s=0&a=t&q=89');
INSERT INTO pictures(band_id, pic_url) VALUES((SELECT band_id FROM bands WHERE name = 'Led Zeppelin'), 'https://media.gq-magazine.co.uk/photos/5f6a28e8c32ccc672f945c2f/master/pass/20200922-led-cars-02.jpg');
INSERT INTO pictures(band_id, pic_url) VALUES((SELECT band_id FROM bands WHERE name = 'The Beatles'), 'https://media.gq-magazine.co.uk/photos/634d5e48bd70b3f668459a4f/master/pass/Beatles%20Revolver%20remix%201710%20header_01.jpg');
INSERT INTO pictures(band_id, pic_url) VALUES((SELECT band_id FROM bands WHERE name = 'The Beatles'), 'https://d.newsweek.com/en/full/1580370/beatles.jpg');
INSERT INTO pictures(band_id, pic_url) VALUES((SELECT band_id FROM bands WHERE name = 'Grateful Dead'), 'https://upload.wikimedia.org/wikipedia/commons/6/6b/Grateful_Dead_%281970%29.png');
INSERT INTO pictures(band_id, pic_url) VALUES((SELECT band_id FROM bands WHERE name = 'Grateful Dead'), 'https://media.newyorker.com/photos/590966551c7a8e33fb38d5b4/master/w_2560%2Cc_limit/121126_r22861_g2048.jpg');
COMMIT;

BEGIN TRANSACTION;
INSERT INTO shows(band_id, show_name, show_date, show_location) VALUES ((SELECT band_id FROM bands WHERE name = 'Led Zeppelin'), 'Get The Led Out', '12/05/2023', 'Red Rocks, CO');
INSERT INTO shows(band_id, show_name, show_date, show_location) VALUES ((SELECT band_id FROM bands WHERE name = 'Led Zeppelin'), 'Lost in Time', '10/30/23', 'The Gorge, WA');
INSERT INTO shows(band_id, show_name, show_date, show_location) VALUES ((SELECT band_id FROM bands WHERE name = 'The Beatles'), 'We Live Once More', '01/12/24', 'The Armory, MI');
INSERT INTO shows(band_id, show_name, show_date, show_location) VALUES ((SELECT band_id FROM bands WHERE name = 'The Beatles'), 'throwback', '10/04/98', 'Columbus, OH');
INSERT INTO shows(band_id, show_name, show_date, show_location) VALUES ((SELECT band_id FROM bands WHERE name = 'Grateful Dead'), 'Jammin', '07/10/22', 'Boulder, Co');
INSERT INTO shows(band_id, show_name, show_date, show_location) VALUES ((SELECT band_id FROM bands WHERE name = 'Grateful Dead'), 'YOOOOOOO', '05/30/23', 'Cincinnati, OH');
COMMIT;

BEGIN TRANSACTION;
INSERT INTO genres(genre_name) VALUES('Rock');
INSERT INTO genres(genre_name) VALUES('Country');
INSERT INTO genres(genre_name) VALUES('EDM');
INSERT INTO genres(genre_name) VALUES('Psychadelia');
INSERT INTO genres(genre_name) VALUES('Pop');
INSERT INTO genres(genre_name) VALUES('Punk');
INSERT INTO genres(genre_name) VALUES('Blues');
INSERT INTO genres(genre_name) VALUES('Jazz');
INSERT INTO genres(genre_name) VALUES('Funk');
INSERT INTO genres(genre_name) VALUES('Folk');
COMMIT;

BEGIN TRANSACTION;
INSERT INTO band_genre(band_id, genre_id) VALUES((SELECT band_id FROM bands WHERE name = 'Led Zeppelin'), (SELECT genre_id FROM genres WHERE genre_name = 'Rock'));
INSERT INTO band_genre(band_id, genre_id) VALUES((SELECT band_id FROM bands WHERE name = 'Led Zeppelin'), (SELECT genre_id FROM genres WHERE genre_name = 'Blues'));
INSERT INTO band_genre(band_id, genre_id) VALUES((SELECT band_id FROM bands WHERE name = 'Led Zeppelin'), (SELECT genre_id FROM genres WHERE genre_name = 'Folk'));
INSERT INTO band_genre(band_id, genre_id) VALUES((SELECT band_id FROM bands WHERE name = 'The Beatles'), (SELECT genre_id FROM genres WHERE genre_name = 'Rock'));
INSERT INTO band_genre(band_id, genre_id) VALUES((SELECT band_id FROM bands WHERE name = 'The Beatles'), (SELECT genre_id FROM genres WHERE genre_name = 'Blues'));
INSERT INTO band_genre(band_id, genre_id) VALUES((SELECT band_id FROM bands WHERE name = 'The Beatles'), (SELECT genre_id FROM genres WHERE genre_name = 'Psychadelia'));
INSERT INTO band_genre(band_id, genre_id) VALUES((SELECT band_id FROM bands WHERE name = 'The Beatles'), (SELECT genre_id FROM genres WHERE genre_name = 'Folk'));
INSERT INTO band_genre(band_id, genre_id) VALUES((SELECT band_id FROM bands WHERE name = 'Grateful Dead'), (SELECT genre_id FROM genres WHERE genre_name = 'Rock'));
INSERT INTO band_genre(band_id, genre_id) VALUES((SELECT band_id FROM bands WHERE name = 'Grateful Dead'), (SELECT genre_id FROM genres WHERE genre_name = 'Blues'));
INSERT INTO band_genre(band_id, genre_id) VALUES((SELECT band_id FROM bands WHERE name = 'Grateful Dead'), (SELECT genre_id FROM genres WHERE genre_name = 'Folk'));
INSERT INTO band_genre(band_id, genre_id) VALUES((SELECT band_id FROM bands WHERE name = 'Grateful Dead'), (SELECT genre_id FROM genres WHERE genre_name = 'Country'));
INSERT INTO band_genre(band_id, genre_id) VALUES((SELECT band_id FROM bands WHERE name = 'Grateful Dead'), (SELECT genre_id FROM genres WHERE genre_name = 'Psychadelia'));
COMMIT;

