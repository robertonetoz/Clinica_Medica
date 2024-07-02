package br.edu.imepac.controllers;

import br.edu.imepac.dtos.ProntuarioDto;
import br.edu.imepac.models.ProntuarioModel;
import br.edu.imepac.services.ProntuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("prontuarios")
public class ProntuarioController {

    private static final Logger logger = LoggerFactory.getLogger(ProntuarioController.class);

    @Autowired
    private ProntuarioService prontuarioService;  // Injeção de dependência do serviço

    @GetMapping
    public ResponseEntity<List<ProntuarioDto>> listAllProntuarios() {
        List<ProntuarioDto> prontuarios = prontuarioService.findAll();
        return new ResponseEntity<>(prontuarios, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProntuarioDto> createProntuario(@RequestBody ProntuarioDto prontuarioDto) {
        ProntuarioDto createdProntuario = prontuarioService.create(prontuarioDto);
        return new ResponseEntity<>(createdProntuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProntuarioDto> updateProntuario(@PathVariable Long id, @RequestBody ProntuarioDto prontuarioDto) {
        Optional<ProntuarioDto> updatedProntuario = prontuarioService.update(id, prontuarioDto);
        return updatedProntuario.map(prontuario -> new ResponseEntity<>(prontuario, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProntuarioDto> getProntuarioById(@PathVariable Long id) {
        Optional<ProntuarioDto> prontuario = prontuarioService.findById(id);
        return prontuario.map(prontuarioDto -> new ResponseEntity<>(prontuarioDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProntuario(@PathVariable Long id) {
        boolean isDeleted = prontuarioService.delete(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}