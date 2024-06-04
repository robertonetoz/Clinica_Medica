package br.edu.imepac.controllers;

import br.edu.imepac.dtos.EspecialidadeCreateRequest;
import br.edu.imepac.dtos.EspecialidadeDto;
import br.edu.imepac.services.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/especialidades")


public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;



    //função post
    @PostMapping
    public ResponseEntity<EspecialidadeDto> saveEspecialidade(@RequestBody EspecialidadeCreateRequest especialidadeCreateRequest) {
        EspecialidadeDto savedEspecialidade = especialidadeService.save(especialidadeCreateRequest);
        return new ResponseEntity<>(savedEspecialidade, HttpStatus.CREATED);
    }




    //função get
    @GetMapping
    public ResponseEntity<List<EspecialidadeDto>> listAllEspecialidades() {
        List<EspecialidadeDto> especialidades = especialidadeService.findAll();
        return new ResponseEntity<>(especialidades, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadeDto> getEspecialidadeById(@PathVariable Long id) {
        Optional<EspecialidadeDto> especialidade = especialidadeService.findById(id);
        return especialidade.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }





    //função update
    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadeDto> updateEspecialidade(@PathVariable Long id, @RequestBody EspecialidadeDto especialidadeDetails) {
        EspecialidadeDto updatedEspecialidade = especialidadeService.update(id, especialidadeDetails);
        if (updatedEspecialidade != null) {
            return new ResponseEntity<>(updatedEspecialidade, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }






    //função deletar por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspecialidade(@PathVariable Long id) {
        especialidadeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}