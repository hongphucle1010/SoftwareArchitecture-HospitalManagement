-- schema.sql
CREATE TABLE IF NOT EXISTS Admin (
    id SERIAL PRIMARY KEY,
    subject VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL
    );
