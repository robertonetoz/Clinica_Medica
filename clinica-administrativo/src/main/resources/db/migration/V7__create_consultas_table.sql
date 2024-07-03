CREATE TABLE consultas (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           registro INT NOT NULL,
                           cd_paciente BIGINT,
                           cd_medico BIGINT,
                           data VARCHAR(255) NOT NULL,
                           hora VARCHAR(255) NOT NULL,
                           retorno BOOLEAN NOT NULL,
                           cancelamento BOOLEAN NOT NULL,
                           motivo_cancelamento VARCHAR(255),
                           status VARCHAR(255) NOT NULL,
                           CONSTRAINT fk_consultas_paciente FOREIGN KEY (cd_paciente) REFERENCES pacientes(id),
                           CONSTRAINT fk_consultas_medico FOREIGN KEY (cd_medico) REFERENCES medicos(id)
);