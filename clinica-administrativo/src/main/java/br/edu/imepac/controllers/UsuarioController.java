package br.edu.imepac.controllers;

import br.edu.imepac.dtos.MedicoCreateRequest;
import br.edu.imepac.dtos.MedicoDto;
import br.edu.imepac.dtos.UsuarioCreateRequest;
import br.edu.imepac.dtos.UsuarioDto;
import br.edu.imepac.services.MedicoService;
import br.edu.imepac.services.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("usuários")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<UsuarioDto> saveUser(@RequestBody UsuarioCreateRequest usuarioCreateRequest) {
        logger.info("Request UsuarioCreateRequest " );
        UsuarioDto savedUsuario = usuarioService.save(usuarioCreateRequest);
        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<UsuarioDto>> ListAllUsuarios(){
        List<UsuarioDto> usuarios = usuarioService.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UsuarioDto> getUserById(@PathVariable Long id) {
        UsuarioDto usuarioDto = usuarioService.findById(id);
        if (usuarioDto != null) {
            return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UsuarioDto> updateUsers(@PathVariable Long id, @RequestBody UsuarioDto usuarioDetails) {
        UsuarioDto updatedUsuario = usuarioService.update(id, usuarioDetails);
        if (updatedUsuario != null) {
            return new ResponseEntity<>(updatedUsuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUsers(@PathVariable Long id) {
        usuarioService.delete(id);
    }
}