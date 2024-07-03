package br.edu.imepac.dtos;

import lombok.Data;

@Data
public class PacienteCreateRequest {

    private Long id;
    private String nome;
    private double rg;
    private String orgao_emissor;
    private double cpf;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String telefone;
    private String celular;
    private String sexo;
    private String nascimento;
    private String possui_convenio;
    private String nome_convenio;

}
