package br.edu.imepac.controllers;

import br.edu.imepac.dtos.ProntuarioDto;
import br.edu.imepac.models.ProntuarioModel;
import br.edu.imepac.repositories.ProntuarioRepository;
import br.edu.imepac.services.ProntuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}