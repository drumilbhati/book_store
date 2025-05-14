CREATE TABLE book_entity if not exists (
    id INT SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL
); 