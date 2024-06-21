package br.edu.imepac.services;

import br.edu.imepac.dtos.ProntuarioDto;
import br.edu.imepac.models.ProntuarioModel;
import br.edu.imepac.repositories.ProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class ProntuarioService {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    public List<ProntuarioDto> findAll(){
        List<ProntuarioModel> prontuarios = prontuarioRepository.findAll();
        return prontuarios.stream().map(prontuario ->{
            ProntuarioDto prontuarioDto = new ProntuarioDto();
            prontuarioDto.setId(prontuario.getId());
            prontuarioDto.setExames(prontuario.getExames());
            prontuarioDto.setHistorico(prontuario.getHistorico());
            prontuarioDto.setRegistro(prontuario.getRegistro());
            prontuarioDto.setReceituario(prontuario.getReceituario());

            return prontuarioDto;
        }).collect(Collectors.toList());
    }
}

