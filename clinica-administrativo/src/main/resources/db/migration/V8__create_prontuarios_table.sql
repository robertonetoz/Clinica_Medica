-- V8__create_prontuarios_table.sql
CREATE TABLE prontuarios (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             registro VARCHAR(255) NOT NULL,
                             historico TEXT,
                             receituario TEXT,
                             exames TEXT
);