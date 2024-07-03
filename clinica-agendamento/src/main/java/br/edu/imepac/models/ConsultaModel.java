package br.edu.imepac.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "consultas")
@Data
public class ConsultaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int registro;

    @ManyToOne
    @JoinColumn(name = "cd_paciente", referencedColumnName = "id")
    private PacienteModel paciente;

    @ManyToOne
    @JoinColumn(name = "cd_medico", referencedColumnName = "id")
    private MedicoModel medico;

    private String data;
    private String hora;
    private boolean retorno;
    private boolean cancelamento;
    private String motivo_cancelamento;
    private String status;
}


    //obs: a coluna "cd_paciente" e "cd_medico" já estão sendo adicionadas a tabela Consultas;




//A anotação @JoinColumn é usada no JPA (Java Persistence API) para especificar a coluna
// que será usada para unir duas tabelas em um relacionamento de banco de dados.
// Ela é frequentemente usada em conjunto com anotações de relacionamento como @ManyToOne,
// @OneToMany, @OneToOne, e @ManyToMany para definir a chave estrangeira que estabelece a
// ligação entre as tabelas.

//-------------------------------------------------------------------------------------------------------
//A anotação @JoinColumn especifica que a coluna cd_paciente na tabela consultas
// será usada como chave estrangeira para referenciar a coluna id na tabela
// pacientes.

//-------------------------------------------------------------------------------------------------------
//Atributos Comuns da @JoinColumn
//
// name: Especifica o nome da coluna na tabela de banco de dados que será usada como chave estrangeira.
//
// referencedColumnName: Especifica o nome da coluna na tabela referenciada que será usada para a junção.
//          Se não for especificado, o JPA assume que a coluna referenciada é a chave primária da tabela
//          referenciada.

// nullable: Define se a coluna pode conter valores nulos. O valor padrão é true.
// unique: Define se a coluna deve ter valores únicos. O valor padrão é false.
// insertable: Define se a coluna deve ser incluída em instruções SQL INSERT. O valor padrão é true.
// updatable: Define se a coluna deve ser incluída em instruções SQL UPDATE. O valor padrão é true.