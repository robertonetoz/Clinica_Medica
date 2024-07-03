package br.edu.imepac.dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class FuncionarioDto {
    private Long id;
    private String nome;
    private String rg;
    private String orgao_emissor;
    private String cpf;
    private String endereco;
    private int numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String telefone;
    private String celular;
    private String ctps;
    private String pis;
    private LocalDate nascimento;
}