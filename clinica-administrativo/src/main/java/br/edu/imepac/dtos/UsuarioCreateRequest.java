package br.edu.imepac.dtos;


import lombok.Data;

@Data
public class UsuarioCreateRequest {

    private Long id;
    private String usuario;
    private String senha;
}
