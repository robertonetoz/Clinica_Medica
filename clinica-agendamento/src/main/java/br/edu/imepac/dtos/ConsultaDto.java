package br.edu.imepac.dtos;


import lombok.Data;

@Data
public class ConsultaDto {

    private Long id;

    private int registro;
    private Long cd_paciente;
    private Long cd_medico;
    private String data;
    private String hora;
    private boolean retorno;
    private boolean cancelamento;
    private String motivoCancelamento;
    private String status;

}
