package br.edu.imepac.controllers;

import br.edu.imepac.dtos.FuncionarioCreateRequest;
import br.edu.imepac.dtos.FuncionarioDto;
import br.edu.imepac.services.FuncionarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("funcionarios")
public class FuncionarioController {
    private static final Logger logger = LoggerFactory.getLogger(FuncionarioController.class);

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<FuncionarioDto> saveFuncionario(@RequestBody FuncionarioCreateRequest funcionarioCreateRequest) {
        logger.info("Request FuncionarioCreateRequest ");
        FuncionarioDto savedFuncionario = funcionarioService.save(funcionarioCreateRequest);
        return new ResponseEntity<>(savedFuncionario, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDto>> listAllFuncionarios() {
        List<FuncionarioDto> funcionarios = funcionarioService.findAll();
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FuncionarioDto> getFuncionarioById(@PathVariable Long id) {
        FuncionarioDto funcionarioDto = funcionarioService.findById(id);
        if (funcionarioDto != null) {
            return new ResponseEntity<>(funcionarioDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FuncionarioDto> updateFuncionario(@PathVariable Long id, @RequestBody FuncionarioDto funcionarioDetails) {
        FuncionarioDto updatedFuncionario = funcionarioService.update(id, funcionarioDetails);
        if (updatedFuncionario != null) {
            return new ResponseEntity<>(updatedFuncionario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFuncionario(@PathVariable Long id) {
        funcionarioService.delete(id);

    }
}

