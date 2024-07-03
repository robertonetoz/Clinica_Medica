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

 @ManyToOne
 @JoinColumn(name = "cd_paciente", referencedColumnName = "id")
 private PacienteModel paciente; // relacionamento com a entidade PacienteModel

}

