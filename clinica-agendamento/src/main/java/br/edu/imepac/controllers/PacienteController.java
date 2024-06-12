package br.edu.imepac.controllers;

import br.edu.imepac.dtos.PacienteCreateRequest;
import br.edu.imepac.dtos.PacienteDtos;
import br.edu.imepac.services.PacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    private static final Logger logger = LoggerFactory.getLogger(PacienteController.class);

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteDtos> savePaciente(@RequestBody PacienteCreateRequest pacienteCreateRequest) {
        logger.info("Request PacienteCreateRequest");
        PacienteDtos savedPaciente = pacienteService.save(pacienteCreateRequest);
        return new ResponseEntity<>(savedPaciente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PacienteDtos>> listAllPacientes() {
        List<PacienteDtos> pacientes = pacienteService.findAll();
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PacienteDtos> getPacienteById(@PathVariable Long id) {
        PacienteDtos pacienteDto = pacienteService.findById(id);
        if (pacienteDto != null) {
            return new ResponseEntity<>(pacienteDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PacienteDtos> updatePaciente(@PathVariable Long id, @RequestBody PacienteDtos pacienteDetails) {
        PacienteDtos updatedPaciente = pacienteService.update(id, pacienteDetails);
        if (updatedPaciente != null) {
            return new ResponseEntity<>(updatedPaciente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePaciente(@PathVariable Long id) {
        pacienteService.delete(id);
    }
}