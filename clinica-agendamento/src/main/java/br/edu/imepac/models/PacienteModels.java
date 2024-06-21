    package br.edu.imepac.models;


    import jakarta.persistence.*;
    import lombok.Data;

    @Entity
    @Table(name = "pacientes")
    @Data
    public class PacienteModels {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Long id;
        private String nome;
        private int rg;
        private String orgaoEmissor;
        private int cpf;
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
