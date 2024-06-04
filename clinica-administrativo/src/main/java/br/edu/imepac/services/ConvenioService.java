package br.edu.imepac.services;

import br.edu.imepac.dtos.ConvenioDto;
import br.edu.imepac.dtos.ConvenioCreateRequest;
import br.edu.imepac.models.ConvenioModel;
import br.edu.imepac.repositories.ConvenioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class ConvenioService {

    @Autowired
    private ConvenioRepository convenioRepository;



    //FUNÇÃO SALVAR
    public ConvenioDto save(ConvenioCreateRequest convenioCreateRequest) {
        ConvenioModel convenioModel = new ConvenioModel();
        convenioModel.setNome(convenioCreateRequest.getNome());
        convenioModel.setDescricao(convenioCreateRequest.getDescricao());


        ConvenioModel savedConvenio = convenioRepository.save(convenioModel);

        ConvenioDto convenioDto = new ConvenioDto();
        convenioDto.setId(savedConvenio.getId());
        convenioDto.setNome(savedConvenio.getNome());
        convenioDto.setDescricao(savedConvenio.getDescricao());


        return convenioDto;
    }





    //FUNÇÃO PARA LISTAR OS CONVENIOS
    public List<ConvenioDto> findAll() {
        return convenioRepository.findAll().stream().map(convenio -> {

            ConvenioDto convenioDto = new ConvenioDto();

            convenioDto.setId(convenio.getId());
            convenioDto.setNome(convenio.getNome());
            convenioDto.setDescricao(convenio.getDescricao());

            return convenioDto;

        }).collect(Collectors.toList());
    }







    //BUSCAR PELO ID DO CONVENIO
    public Optional<ConvenioDto> findById(Long id) {
        return convenioRepository.findById(id).map(convenio -> {

            ConvenioDto convenioDto = new ConvenioDto();

            convenioDto.setId(convenio.getId());
            convenioDto.setNome(convenio.getNome());
            convenioDto.setDescricao(convenio.getDescricao());

            return convenioDto;
        });
    }






    //FUNÇÃO UPDATE
    public ConvenioDto update(Long id, ConvenioDto convenioDetails) {
        Optional<ConvenioModel> optionalConvenio = convenioRepository.findById(id);
        if (optionalConvenio.isPresent()) {
            ConvenioModel convenioModel = optionalConvenio.get();
            convenioModel.setNome(convenioDetails.getNome());
            convenioModel.setDescricao(convenioDetails.getDescricao());
            // outros atributos

            ConvenioModel updatedConvenio = convenioRepository.save(convenioModel);

            ConvenioDto convenioDto = new ConvenioDto();
            convenioDto.setId(updatedConvenio.getId());
            convenioDto.setNome(updatedConvenio.getNome());
            convenioDto.setDescricao(updatedConvenio.getDescricao());
            // outros atributos
            return convenioDto;
        } else {
            return null;
        }
    }






    //FUNÇÃO DELETAR
    public void delete(Long id) {
        convenioRepository.deleteById(id);
    }
}
