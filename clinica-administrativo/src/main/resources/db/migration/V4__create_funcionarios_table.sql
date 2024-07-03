CREATE TABLE funcionarios (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              nome VARCHAR(255) NOT NULL,
                              rg VARCHAR(255) NOT NULL,
                              orgao_emissor VARCHAR(255) NOT NULL,
                              cpf VARCHAR(255) NOT NULL,
                              endereco VARCHAR(255) NOT NULL,
                              numero INT NOT NULL,
                              complemento VARCHAR(255),
                              bairro VARCHAR(255) NOT NULL,
                              cidade VARCHAR(255) NOT NULL,
                              estado VARCHAR(255) NOT NULL,
                              telefone VARCHAR(255),
                              celular VARCHAR(255),
                              ctps VARCHAR(255) NOT NULL,
                              pis VARCHAR(255) NOT NULL,
                              nascimento DATE NOT NULL
);