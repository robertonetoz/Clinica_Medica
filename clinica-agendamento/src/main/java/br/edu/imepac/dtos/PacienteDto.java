package br.edu.imepac.dtos;

import lombok.Data;

@Data
public class PacienteDto {

    private Long id;
    private String nome;
    private double rg;
    private String orgaoEmissor;
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
    private String possuiConvenio;
    private String nomeConvenio;


}
