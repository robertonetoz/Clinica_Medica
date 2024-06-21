package br.edu.imepac.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Usuários")
@Data

public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String usuario;
    private String senha;

}
