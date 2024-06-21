package br.edu.imepac.dtos;


import lombok.Data;

import java.time.LocalDate;

@Data
public class FuncionarioDto {

    private Long id;
    private String nome;
    private double rg;
    private String orgaoEmissor;
    private double cpf;
    private String endereco;
    private int numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private double telefone;
    private double celular;
    private double ctps;
    private double pis;
    private LocalDate nascimento;


}