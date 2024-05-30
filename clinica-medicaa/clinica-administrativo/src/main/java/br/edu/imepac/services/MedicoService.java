package br.edu.imepac.services;

import org.springframework.stereotype.Service;
import br.edu.imepac.dtos.MedicoDto;
import br.edu.imepac.dtos.MedicoCreateRequest;
import br.edu.imepac.models.MedicoModel;
import br.edu.imepac.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public void delete(Long id) {
        medicoRepository.deleteById(id);
    }

    public List<MedicoDto> findAll() {
        List<MedicoModel> medicos = medicoRepository.findAll();
        return medicos.stream().map(medico -> {
            MedicoDto medicoDto = new MedicoDto();
            medicoDto.setId(medico.getId());
            medicoDto.setNome(medico.getNome());
            medicoDto.setCrm(medico.getCrm());
            return medicoDto;
        }).collect(Collectors.toList());
    }

    public MedicoDto update(Long id, MedicoDto medicoDetails) {
        Optional<MedicoModel> optionalMedico = medicoRepository.findById(id);

        if (optionalMedico.isPresent()) {
            MedicoModel medicoModel = optionalMedico.get();
            medicoModel.setNome(medicoDetails.getNome());
            medicoModel.setCrm(medicoDetails.getCrm());

            MedicoModel updatedMedico = medicoRepository.save(medicoModel);

            MedicoDto medicoDto = new MedicoDto();
            medicoDto.setId(updatedMedico.getId());
            medicoDto.setNome(updatedMedico.getNome());
            medicoDto.setCrm(updatedMedico.getCrm());

            return medicoDto;
        } else {
            return null;
        }
    }

    public MedicoDto save(MedicoCreateRequest medicoRequest) {
        MedicoModel medicoModel = new MedicoModel();
        medicoModel.setNome(medicoRequest.getNome());
        medicoModel.setCrm(medicoRequest.getCrm());
        medicoModel.setSenha(medicoRequest.getSenha());

        MedicoModel savedMedico = medicoRepository.save(medicoModel);

        MedicoDto medicoDto = new MedicoDto();
        medicoDto.setId(savedMedico.getId());
        medicoDto.setNome(savedMedico.getNome());
        medicoDto.setCrm(savedMedico.getCrm());

        return medicoDto;
    }

    public MedicoDto findById(Long id) {
        Optional<MedicoModel> optionalMedico = medicoRepository.findById(id);
        if (optionalMedico.isPresent()) {
            MedicoModel medicoModel = optionalMedico.get();
            MedicoDto medicoDto = new MedicoDto();
            medicoDto.setId(medicoModel.getId());
            medicoDto.setNome(medicoModel.getNome());
            medicoDto.setCrm(medicoModel.getCrm());
            return medicoDto;
        } else {
            return null;
        }
    }
}