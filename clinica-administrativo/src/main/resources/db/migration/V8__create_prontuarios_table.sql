CREATE TABLE prontuarios (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             registro VARCHAR(255) NOT NULL,
                             historico TEXT,
                             receituario TEXT,
                             exames TEXT,
                             cd_paciente BIGINT,
                             CONSTRAINT fk_prontuarios_paciente FOREIGN KEY (cd_paciente) REFERENCES pacientes(id)
);