package br.edu.imepac.dtos;

import lombok.Data;

@Data
public class ConvenioCreateRequest {

    private Long id;
    private String empresa;
    private String CNPJ;
    private String telefone;

}
