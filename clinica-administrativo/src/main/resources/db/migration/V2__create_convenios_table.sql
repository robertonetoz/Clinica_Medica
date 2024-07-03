CREATE TABLE convenios (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           empresa VARCHAR(255) NOT NULL,
                           CNPJ VARCHAR(255) NOT NULL,
                           telefone VARCHAR(255) NOT NULL
);