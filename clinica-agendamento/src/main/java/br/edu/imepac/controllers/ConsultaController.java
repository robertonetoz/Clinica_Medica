package br.edu.imepac.controllers;

import br.edu.imepac.dtos.ConsultaCreateRequest;
import br.edu.imepac.dtos.ConsultaDto;

import br.edu.imepac.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;



    @GetMapping
    public ResponseEntity<List<ConsultaDto>> listAllConsultas() {
        List<ConsultaDto> consultas = consultaService.findAll();
        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDto> getConsultaById(@PathVariable Long id) {
        ConsultaDto consultaDto = consultaService.findById(id);
        if (consultaDto != null) {
            return new ResponseEntity<>(consultaDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping
    public ResponseEntity<ConsultaDto> agendarConsulta(@RequestBody ConsultaCreateRequest consultaCreateRequest) {
        ConsultaDto consultaDto = consultaService.agendarConsulta(consultaCreateRequest);
        return new ResponseEntity<>(consultaDto, HttpStatus.CREATED);
    }




    @PutMapping("/{id}/cancelar")
    public ResponseEntity<ConsultaDto> cancelarConsulta(@PathVariable Long id, @RequestParam String motivoCancelamento) {
        ConsultaDto consultaDto = consultaService.cancelarConsulta(id, motivoCancelamento);
        return new ResponseEntity<>(consultaDto, HttpStatus.OK);
    }


    @PutMapping("/{id}/retorno")
    public ResponseEntity<ConsultaDto> registrarRetorno(@PathVariable Long id, @RequestParam String data, @RequestParam String hora) {
        ConsultaDto consultaDto = consultaService.registrarRetorno(id, data, hora);
        return new ResponseEntity<>(consultaDto, HttpStatus.OK);
    }
}