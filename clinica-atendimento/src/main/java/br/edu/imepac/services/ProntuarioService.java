package br.edu.imepac.services;

import br.edu.imepac.dtos.ProntuarioDto;
import br.edu.imepac.models.ProntuarioModel;
import br.edu.imepac.repositories.ProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProntuarioService {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    public List<ProntuarioDto> findAll() {
        return prontuarioRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ProntuarioDto create(ProntuarioDto prontuarioDto) {
        ProntuarioModel prontuarioModel = convertToEntity(prontuarioDto);
        ProntuarioModel savedProntuario = prontuarioRepository.save(prontuarioModel);
        return convertToDto(savedProntuario);
    }

    public Optional<ProntuarioDto> update(Long id, ProntuarioDto prontuarioDto) {
        Optional<ProntuarioModel> prontuarioOptional = prontuarioRepository.findById(id);
        if (prontuarioOptional.isPresent()) {
            ProntuarioModel prontuarioModel = prontuarioOptional.get();
            prontuarioModel.setRegistro(prontuarioDto.getRegistro());
            prontuarioModel.setHistorico(prontuarioDto.getHistorico());
            prontuarioModel.setReceituario(prontuarioDto.getReceituario());
            prontuarioModel.setExames(prontuarioDto.getExames());
            ProntuarioModel updatedProntuario = prontuarioRepository.save(prontuarioModel);
            return Optional.of(convertToDto(updatedProntuario));
        }
        return Optional.empty();
    }

    public Optional<ProntuarioDto> findById(Long id) {
        return prontuarioRepository.findById(id).map(this::convertToDto);
    }

    public boolean delete(Long id) {
        if (prontuarioRepository.existsById(id)) {
            prontuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private ProntuarioDto convertToDto(ProntuarioModel prontuarioModel) {
        ProntuarioDto prontuarioDto = new ProntuarioDto();
        prontuarioDto.setId(prontuarioModel.getId());
        prontuarioDto.setRegistro(prontuarioModel.getRegistro());
        prontuarioDto.setHistorico(prontuarioModel.getHistorico());
        prontuarioDto.setReceituario(prontuarioModel.getReceituario());
        prontuarioDto.setExames(prontuarioModel.getExames());
        return prontuarioDto;
    }

    private ProntuarioModel convertToEntity(ProntuarioDto prontuarioDto) {
        ProntuarioModel prontuarioModel = new ProntuarioModel();
        prontuarioModel.setRegistro(prontuarioDto.getRegistro());
        prontuarioModel.setHistorico(prontuarioDto.getHistorico());
        prontuarioModel.setReceituario(prontuarioDto.getReceituario());
        prontuarioModel.setExames(prontuarioDto.getExames());
        return prontuarioModel;
    }
}


// package br.edu.imepac.services;
//
//import br.edu.imepac.dtos.ProntuarioDto;
//import br.edu.imepac.models.ProntuarioModel;
//import br.edu.imepac.repositories.ProntuarioRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//import java.util.stream.Collectors;
//
//@Service
//public class ProntuarioService {
//
//    @Autowired
//    private ProntuarioRepository prontuarioRepository;
//
//    public List<ProntuarioDto> findAll(){
//        List<ProntuarioModel> prontuarios = prontuarioRepository.findAll();
//        return prontuarios.stream().map(prontuario ->{
//            ProntuarioDto prontuarioDto = new ProntuarioDto();
//            prontuarioDto.setId(prontuario.getId());
//            prontuarioDto.setExames(prontuario.getExames());
//            prontuarioDto.setHistorico(prontuario.getHistorico());
//            prontuarioDto.setRegistro(prontuario.getRegistro());
//            prontuarioDto.setReceituario(prontuario.getReceituario());
//
//            return prontuarioDto;
//        }).collect(Collectors.toList());
//    }
//}
