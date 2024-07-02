package br.edu.imepac.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "convenios")
@Data

public class ConvenioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String empresa;
    private String CNPJ;
    private String telefone;



    //dados para vc usar como teste:
    // "cnpj": "16.915.915/0001-20",
    // "empresa": "Hospital Santo Antônio Ltda",
    // "telefone": "(34) 3249-1405"


    // "cnpj": "16.826.067/0001-29",
    // "empresa": "Santa Casa de Misericórdia de Araguari",
    // "telefone":"(34) 3249-1500"

    // "cnpj": "10.550.765/0001-59",
    // "empresa": "Hospital Universitário Sagrada Família | HUSF",
    //  "telefone": "(34) 3512-9000"
}