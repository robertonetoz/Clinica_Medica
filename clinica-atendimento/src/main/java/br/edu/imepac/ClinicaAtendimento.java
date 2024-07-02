package br.edu.imepac;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ClinicaAtendimento {
    public static void main (String[] args) {

        SpringApplication.run(ClinicaAtendimento.class, args);
    }
    @Bean (name = "atendimentoModelMapper")
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }

}
