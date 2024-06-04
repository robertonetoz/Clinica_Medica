package br.edu.imepac.services;

import br.edu.imepac.dtos.EspecialidadeDto;
import br.edu.imepac.dtos.EspecialidadeCreateRequest;
import br.edu.imepac.models.EspecialidadeModel;
import br.edu.imepac.repositories.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class EspecialidadeService {



    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public EspecialidadeDto save(EspecialidadeCreateRequest especialidadeCreateRequest) {
        EspecialidadeModel especialidadeModel = new EspecialidadeModel();

        especialidadeModel.setNome(especialidadeCreateRequest.getNome());
        especialidadeModel.setDescricao(especialidadeCreateRequest.getDescricao());


        EspecialidadeModel savedEspecialidade = especialidadeRepository.save(especialidadeModel);

        EspecialidadeDto especialidadeDto = new EspecialidadeDto();

        especialidadeDto.setId(savedEspecialidade.getId());
        especialidadeDto.setNome(savedEspecialidade.getNome());
        especialidadeDto.setDescricao(savedEspecialidade.getDescricao());


        return especialidadeDto;
    }




    //função listar todas as especialidades
    public List<EspecialidadeDto> findAll() {
        return especialidadeRepository.findAll().stream().map(especialidade -> {

            EspecialidadeDto especialidadeDto = new EspecialidadeDto();

            especialidadeDto.setId(especialidade.getId());
            especialidadeDto.setNome(especialidade.getNome());
            especialidadeDto.setDescricao(especialidade.getDescricao());


            return especialidadeDto;

        }).collect(Collectors.toList());
    }





    //buscar pelo id do convenio
    public Optional<EspecialidadeDto> findById(Long id) {
        return especialidadeRepository.findById(id).map(especialidade -> {

            EspecialidadeDto especialidadeDto = new EspecialidadeDto();

            especialidadeDto.setId(especialidade.getId());
            especialidadeDto.setNome(especialidade.getNome());
            especialidadeDto.setDescricao(especialidade.getDescricao());


            return especialidadeDto;
        });
    }




    //função update
    public EspecialidadeDto update(Long id, EspecialidadeDto especialidadeDetails) {
        Optional<EspecialidadeModel> optionalEspecialidade = especialidadeRepository.findById(id);
        if (optionalEspecialidade.isPresent()) {

            EspecialidadeModel especialidadeModel = optionalEspecialidade.get();

            especialidadeModel.setNome(especialidadeDetails.getNome());
            especialidadeModel.setDescricao(especialidadeDetails.getDescricao());
            // outros atributos

            EspecialidadeModel updatedEspecialidade = especialidadeRepository.save(especialidadeModel);

            EspecialidadeDto especialidadeDto = new EspecialidadeDto();

            especialidadeDto.setId(updatedEspecialidade.getId());
            especialidadeDto.setNome(updatedEspecialidade.getNome());
            especialidadeDto.setDescricao(updatedEspecialidade.getDescricao());


            return especialidadeDto;
        } else {
            return null;
        }
    }




    //função deletar
    public void delete(Long id) {
        especialidadeRepository.deleteById(id);


    }
}