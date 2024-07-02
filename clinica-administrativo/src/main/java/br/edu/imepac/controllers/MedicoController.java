package br.edu.imepac.controllers;

import br.edu.imepac.dtos.MedicoCreateRequest;
import br.edu.imepac.dtos.MedicoDto;
import br.edu.imepac.services.MedicoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    private static final Logger logger = LoggerFactory.getLogger(MedicoController.class);

    @Autowired
    private MedicoService medicoService;


    @PostMapping
    public ResponseEntity<MedicoDto> saveDoctor(@RequestBody MedicoCreateRequest medicoCreateRequest) {
        logger.info("Request MedicoCreateRequest " );
        MedicoDto savedMedico = medicoService.save(medicoCreateRequest);
        return new ResponseEntity<>(savedMedico, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MedicoDto>> listAllDoctors() {
        List<MedicoDto> medicos = medicoService.findAll();
        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MedicoDto> getDoctorById(@PathVariable Long id) {
        MedicoDto medicoDto = medicoService.findById(id);
        if (medicoDto != null) {
            return new ResponseEntity<>(medicoDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MedicoDto> updateDoctor(@PathVariable Long id, @RequestBody MedicoDto medicoDetails) {
        MedicoDto updatedMedico = medicoService.update(id, medicoDetails);
        if (updatedMedico != null) {
            return new ResponseEntity<>(updatedMedico, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDoctor(@PathVariable Long id) {
        medicoService.delete(id);
    }
}