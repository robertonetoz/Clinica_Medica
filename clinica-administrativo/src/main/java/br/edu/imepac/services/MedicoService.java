package br.edu.imepac.services;

import br.edu.imepac.dtos.MedicoCreateRequest;
import br.edu.imepac.dtos.MedicoDto;
import br.edu.imepac.models.MedicoModel;
import br.edu.imepac.repositories.MedicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    private MedicoRepository medicoRepository;
    private ModelMapper modelMapper;

    public MedicoService(MedicoRepository medicoRepository, ModelMapper modelMapper) {
        this.medicoRepository = medicoRepository;
        this.modelMapper = modelMapper;
    }

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
            medicoDto.setEspecialidade(medico.getEspecialidade());

            return medicoDto;
        }).collect(Collectors.toList());
    }

    public MedicoDto update(Long id, MedicoDto medicoDetails) {
        Optional<MedicoModel> optionalMedico = medicoRepository.findById(id);

        if (optionalMedico.isPresent()) {
            MedicoModel medicoModel = optionalMedico.get();
            medicoModel.setNome(medicoDetails.getNome());
            medicoModel.setCrm(medicoDetails.getCrm());
            medicoModel.setEspecialidade(medicoDetails.getEspecialidade());

            MedicoModel updatedMedico = medicoRepository.save(medicoModel);

            MedicoDto medicoDto = new MedicoDto();
            medicoDto.setId(updatedMedico.getId());
            medicoDto.setNome(updatedMedico.getNome());
            medicoDto.setCrm(updatedMedico.getCrm());
            medicoDto.setEspecialidade(updatedMedico.getEspecialidade());

            return medicoDto;
        } else {
            return null;
        }
    }

    public MedicoDto save(MedicoCreateRequest medicoRequest) {
        MedicoModel medicoModel = modelMapper.map(medicoRequest, MedicoModel.class);
        MedicoModel savedMedico = medicoRepository.save(medicoModel);
        MedicoDto medicoDto = modelMapper.map(savedMedico, MedicoDto.class);
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
            medicoDto.setEspecialidade(medicoModel.getEspecialidade());
            return medicoDto;
        } else {
            return null;
        }
    }
}