package br.edu.imepac.controllers;

import br.edu.imepac.dtos.ConvenioCreateRequest;
import br.edu.imepac.dtos.ConvenioDto;
import br.edu.imepac.services.ConvenioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("convenios")
public class ConvenioController {

    private static final Logger logger = LoggerFactory.getLogger(ConvenioController.class);

    @Autowired
    private ConvenioService convenioService;


    @PostMapping
    public ResponseEntity<ConvenioDto> saveConvenio(@RequestBody ConvenioCreateRequest convenioCreateRequest) {
        logger.info("Request MedicoCreateRequest " );
        ConvenioDto savedConvenio = convenioService.save(convenioCreateRequest);
        return new ResponseEntity<>(savedConvenio, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ConvenioDto>> listAllConvenios() {
        List<ConvenioDto> convenios = convenioService.findAll();
        return new ResponseEntity<>(convenios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ConvenioDto> getConvenioById(@PathVariable Long id) {
        ConvenioDto convenioDto = convenioService.findById(id);
        if (convenioDto != null) {
            return new ResponseEntity<>(convenioDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ConvenioDto> updateConvenio(@PathVariable Long id, @RequestBody ConvenioDto convenioDetails) {
        ConvenioDto updatedConvenio = convenioService.update(id, convenioDetails);
        if (updatedConvenio != null) {
            return new ResponseEntity<>(updatedConvenio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteConvenio(@PathVariable Long id) {
        convenioService.delete(id);
    }
}