package br.edu.imepac.dtos;

import lombok.Data;

@Data
public class ProntuarioCreateRequest {
    private Long id;
    //chave primária da tabela "prontuarios";

    private int registro;
    private String historico;
    private String receituario;
    private String exames;

    //falta o id_paciente, mas nao acho que vou colocar no momento, por
    //interferencia entre os modulos;


}
