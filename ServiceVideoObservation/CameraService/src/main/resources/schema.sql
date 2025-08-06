-- src/main/resources/schema.sql

CREATE TABLE IF NOT EXISTS cameras (
                                       id BIGSERIAL PRIMARY KEY,
                                       number INTEGER NOT NULL,
                                       ip VARCHAR(45) NOT NULL,
                                       port INTEGER NOT NULL,
                                       status BOOLEAN NOT NULL,
                                       number_object INTEGER NOT NULL,
                                       password VARCHAR(255) NOT NULL
);