DROP TABLE IF EXISTS events;

CREATE TABLE events (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description text,
    trigger_at INTEGER
  );

  INSERT INTO events VALUES (1,'Make me new toys! Write some code!', 'You are doing great. Keep at it.', 10),
  	(2,'Your code is easy to read! Keep it up!', 'This is good for your line count!', 100),
  	(3,'The new iPhone just came out. Be the first to build an app for it.', 'Use your entrepreneurial spirit to write some code.', 500);
