package br.edu.imepac.controllers;

import br.edu.imepac.dtos.ConvenioDto;
import br.edu.imepac.dtos.EspecialidadeCreateRequest;
import br.edu.imepac.dtos.EspecialidadeDto;
import br.edu.imepac.services.EspecialidadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("especialidades")
public class EspecialidadeController {

    private static final Logger logger = LoggerFactory.getLogger(EspecialidadeController.class);

    @Autowired
    private EspecialidadeService especialidadeService;




    //função de postar
    @PostMapping
    public ResponseEntity<EspecialidadeDto> saveEspecialidadeConvenio(@RequestBody EspecialidadeCreateRequest especialidadeCreateRequest) {
        logger.info("Request EspecialidadeCreateRequest " );
        EspecialidadeDto savedEspecialidade = especialidadeService.save(especialidadeCreateRequest);
        return new ResponseEntity<>(savedEspecialidade, HttpStatus.CREATED);
    }






    //pega os dados da tabela especialidae
    @GetMapping
    public ResponseEntity<List<EspecialidadeDto>> listAllEspecialidades() {
        List<EspecialidadeDto> especialidades = especialidadeService.findAll();
        return new ResponseEntity<>(especialidades, HttpStatus.OK);
    }


    //pega os dados baseado no id informado
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EspecialidadeDto> getEspecialidadeById(@PathVariable Long id) {
        EspecialidadeDto especialidadeDto = especialidadeService.findById(id);
        if (especialidadeDto != null) {
            return new ResponseEntity<>(especialidadeDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




    //atualiza o dado baseado no id informado
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EspecialidadeDto> updateEspecialidade(@PathVariable Long id, @RequestBody EspecialidadeDto especialidadeDetails) {
        EspecialidadeDto updatedEspecialidade = especialidadeService.update(id, especialidadeDetails);
        if (updatedEspecialidade != null) {
            return new ResponseEntity<>(updatedEspecialidade, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    //deleta o dado pelo id informado
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEspecialidade(@PathVariable Long id) {
        especialidadeService.delete(id);
    }
}