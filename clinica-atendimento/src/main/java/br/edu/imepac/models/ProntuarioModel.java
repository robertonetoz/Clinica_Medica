package br.edu.imepac.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name = "prontuarios")
@Data
public class ProntuarioModel {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)

 private Long id;
 //chave primária da tabela "prontuarios";

 private String registro;
 private String historico;
 private String receituario;
 private String exames;


 //falta o id_paciente, mas nao acho que vou colocar no momento, por
    //interferencia entre os modulos;
}