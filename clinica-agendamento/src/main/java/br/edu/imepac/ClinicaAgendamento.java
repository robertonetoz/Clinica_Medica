package br.edu.imepac;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClinicaAgendamento {
    public static void main (String[] args) {

        SpringApplication.run(ClinicaAgendamento.class, args);
    }

    @Bean (name = "agendamentoModelMapper")
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }

}