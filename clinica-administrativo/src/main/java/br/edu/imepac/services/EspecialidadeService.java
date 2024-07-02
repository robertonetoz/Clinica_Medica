package br.edu.imepac.services;

import br.edu.imepac.dtos.EspecialidadeCreateRequest;
import br.edu.imepac.dtos.EspecialidadeDto;
import br.edu.imepac.models.EspecialidadeModel;
import br.edu.imepac.repositories.EspecialidadeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EspecialidadeService {




    private EspecialidadeRepository especialidadeRepository;
    private ModelMapper modelMapper;



    public EspecialidadeService(EspecialidadeRepository especialidadeRepository, ModelMapper modelMapper) {
        this.especialidadeRepository = especialidadeRepository;
        this.modelMapper = modelMapper;
    }



    public void delete(Long id) {

        especialidadeRepository.deleteById(id);
    }




    public List<EspecialidadeDto> findAll() {
        List<EspecialidadeModel> especialidades = especialidadeRepository.findAll();
        return especialidades.stream().map(especialidade -> {
            EspecialidadeDto especialidadeDto = new EspecialidadeDto();
            especialidadeDto.setId(especialidade.getId());
            especialidadeDto.setDescricao(especialidade.getDescricao());
            return especialidadeDto;
        }).collect(Collectors.toList());
    }




    public EspecialidadeDto update(Long id, EspecialidadeDto especialidadeDetails) {
        Optional<EspecialidadeModel> optionalEspecialidade = especialidadeRepository.findById(id);

        if (optionalEspecialidade.isPresent()) {
            EspecialidadeModel especialidadeModel = optionalEspecialidade.get();
            especialidadeModel.setDescricao(especialidadeDetails.getDescricao());

            EspecialidadeModel updatedEspecialidade = especialidadeRepository.save(especialidadeModel);

            EspecialidadeDto especialidadeDto = new EspecialidadeDto();
            especialidadeDto.setId(updatedEspecialidade.getId());
            especialidadeDto.setDescricao(updatedEspecialidade.getDescricao());

            return especialidadeDto;
        } else {
            return null;
        }
    }





    public EspecialidadeDto save(EspecialidadeCreateRequest especialidadeRequest) {
        EspecialidadeModel especialidadeModel = modelMapper.map(especialidadeRequest, EspecialidadeModel.class);
        EspecialidadeModel savedEspecialidade = especialidadeRepository.save(especialidadeModel);
        EspecialidadeDto especialidadeDto = modelMapper.map(savedEspecialidade, EspecialidadeDto.class);
        return especialidadeDto;
    }

    public EspecialidadeDto findById(Long id) {
        Optional<EspecialidadeModel> optionalEspecialidade = especialidadeRepository.findById(id);
        if (optionalEspecialidade.isPresent()) {
            EspecialidadeModel especialidadeModel = optionalEspecialidade.get();
            EspecialidadeDto especialidadeDto = new EspecialidadeDto();
            especialidadeDto.setId(especialidadeModel.getId());
            especialidadeDto.setDescricao(especialidadeModel.getDescricao());
            return especialidadeDto;
        } else {
            return null;
        }
    }
}
