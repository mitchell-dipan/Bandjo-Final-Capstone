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
INSERT INTO bands(name, description, members, user_id) VALUES ('Led Zeppelin', 'John Bonham, Jimmy Page', 'Cool dudes playing good music', (SELECT user_id FROM users WHERE username = 'jack'));
INSERT INTO bands(name, description, members, user_id) VALUES ('The Beatles', 'John Lennon, Ringo Star', 'Old People with basic music', (SELECT user_id FROM users WHERE username = 'matt'));
INSERT INTO bands(name, description, members, user_id) VALUES ('Grateful Dead', 'Jerry Garcia', 'Jam band galor', (SELECT user_id FROM users WHERE username = 'mitch'));
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

