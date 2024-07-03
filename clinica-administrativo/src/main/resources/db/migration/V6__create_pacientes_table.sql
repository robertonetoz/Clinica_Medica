CREATE TABLE pacientes (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           nome VARCHAR(255) NOT NULL,
                           rg DOUBLE NOT NULL,
                           orgao_emissor VARCHAR(255) NOT NULL,
                           cpf DOUBLE NOT NULL,
                           endereco VARCHAR(255) NOT NULL,
                           numero VARCHAR(255) NOT NULL,
                           complemento VARCHAR(255),
                           bairro VARCHAR(255) NOT NULL,
                           cidade VARCHAR(255) NOT NULL,
                           estado VARCHAR(255) NOT NULL,
                           telefone VARCHAR(255) NOT NULL,
                           celular VARCHAR(255) NOT NULL,
                           sexo VARCHAR(255) NOT NULL,
                           nascimento VARCHAR(255) NOT NULL,
                           possui_convenio VARCHAR(255),
                           nome_convenio VARCHAR(255)
);