package br.edu.imepac.dtos;


import lombok.Data;

@Data
public class UsuarioDto {

    private Long id;
    private String usuario;
    private String senha;
}
