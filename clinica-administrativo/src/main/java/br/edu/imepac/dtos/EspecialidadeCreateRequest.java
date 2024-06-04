package br.edu.imepac.dtos;

import lombok.Data;

@Data
public class EspecialidadeCreateRequest {

    private String id;
    private String nome;
    private String descricao;

}