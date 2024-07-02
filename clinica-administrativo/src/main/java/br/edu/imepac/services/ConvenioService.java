package br.edu.imepac.services;

import br.edu.imepac.dtos.ConvenioCreateRequest;
import br.edu.imepac.dtos.ConvenioDto;
import br.edu.imepac.models.ConvenioModel;
import br.edu.imepac.repositories.ConvenioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConvenioService {

    private ConvenioRepository convenioRepository;
    private ModelMapper modelMapper;

    public ConvenioService(ConvenioRepository convenioRepository, ModelMapper modelMapper) {
        this.convenioRepository = convenioRepository;
        this.modelMapper = modelMapper;
    }




    public void delete(Long id) {

        convenioRepository.deleteById(id);
    }


    public List<ConvenioDto> findAll() {
        List<ConvenioModel> convenios = convenioRepository.findAll();
        return convenios.stream().map(convenio -> {
            ConvenioDto convenioDto = new ConvenioDto();
            convenioDto.setId(convenio.getId());
            convenioDto.setEmpresa(convenio.getEmpresa());
            convenioDto.setCNPJ(convenio.getCNPJ());
            convenioDto.setTelefone(convenio.getTelefone());



            return convenioDto;
        }).collect(Collectors.toList());
    }

    public ConvenioDto update(Long id, ConvenioDto convenioDetails) {
        Optional<ConvenioModel> optionalConvenio = convenioRepository.findById(id);

        if (optionalConvenio.isPresent()) {
            ConvenioModel convenioModel = optionalConvenio.get();
            convenioModel.setEmpresa(convenioDetails.getEmpresa());
            convenioModel.setCNPJ(convenioDetails.getCNPJ());
            convenioModel.setTelefone(convenioDetails.getTelefone());



            ConvenioModel updatedConvenio = convenioRepository.save(convenioModel);

            ConvenioDto convenioDto = new ConvenioDto();
            convenioDto.setId(updatedConvenio.getId());
            convenioDto.setEmpresa(updatedConvenio.getEmpresa());
            convenioDto.setCNPJ(updatedConvenio.getCNPJ());
            convenioDto.setTelefone(updatedConvenio.getTelefone());

            return convenioDto;
        } else {
            return null;
        }
    }

    public ConvenioDto save(ConvenioCreateRequest convenioRequest) {
        ConvenioModel convenioModel = modelMapper.map(convenioRequest, ConvenioModel.class);
        ConvenioModel savedConvenio = convenioRepository.save(convenioModel);
        ConvenioDto convenioDto = modelMapper.map(savedConvenio, ConvenioDto.class);
        return convenioDto;
    }

    public ConvenioDto findById(Long id) {
        Optional<ConvenioModel> optionalConvenio = convenioRepository.findById(id);
        if (optionalConvenio.isPresent()) {
            ConvenioModel convenioModel = optionalConvenio.get();
            ConvenioDto convenioDto = new ConvenioDto();
            convenioDto.setId(convenioModel.getId());
            convenioDto.setEmpresa(convenioModel.getEmpresa());
            convenioDto.setCNPJ(convenioModel.getCNPJ());
            convenioDto.setTelefone(convenioModel.getTelefone());
            return convenioDto;
        } else {
            return null;
        }
    }
}