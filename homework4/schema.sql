DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS generators;


CREATE TABLE events (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description text,
    trigger_at INTEGER
  );
  
CREATE TABLE generators (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	description text,
	rate INTEGER,
	base_cost INTEGER,
	unlock_at INTEGER
);

INSERT INTO events VALUES (1,'Make me new toys! Write some code!', 'You are doing great. Keep at it.', 10),
  	(2,'Your code is easy to read! Keep it up!', 'This is good for your line count!', 100),
  	(3,'The new iPhone just came out. Be the first to build an app for it.', 'Use your entrepreneurial spirit to write some code.', 500);

INSERT INTO generators VALUES (1, 'Intern', 'Interns generate code, but not quickly.', 5, 10, 10),
	(2, 'Software Engineer', 'Software Engineers can write code pretty quickly.', 10, 100, 100),
	(32, 'Researcher', 'They do Math?', 20, 500, 500);