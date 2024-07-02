package br.edu.imepac.services;

import br.edu.imepac.dtos.FuncionarioCreateRequest;
import br.edu.imepac.dtos.FuncionarioDto;
import br.edu.imepac.models.FuncionarioModel;
import br.edu.imepac.repositories.FuncionarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {


    private FuncionarioRepository funcionarioRepository;
    private ModelMapper modelMapper;


    public FuncionarioService(FuncionarioRepository funcionarioRepository, ModelMapper modelMapper) {
        this.funcionarioRepository = funcionarioRepository;
        this.modelMapper = modelMapper;
    }

    public void delete(Long id) {
        funcionarioRepository.deleteById(id);
    }

    public List<FuncionarioDto> findAll() {
        List<FuncionarioModel> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream().map(funcionario -> {
            FuncionarioDto funcionarioDto = new FuncionarioDto();
            funcionarioDto.setId(funcionario.getId());
            funcionarioDto.setNome(funcionario.getNome());
            funcionarioDto.setRg(funcionario.getRg());
            funcionarioDto.setOrgaoEmissor(funcionario.getOrgaoEmissor());
            funcionarioDto.setCpf(funcionario.getCpf());
            funcionarioDto.setEndereco(funcionario.getEndereco());
            funcionarioDto.setNumero(funcionario.getNumero());
            funcionarioDto.setComplemento(funcionario.getComplemento());
            funcionarioDto.setBairro(funcionario.getBairro());
            funcionarioDto.setCidade(funcionario.getCidade());
            funcionarioDto.setEstado(funcionario.getEstado());
            funcionarioDto.setTelefone(funcionario.getTelefone());
            funcionarioDto.setCelular(funcionario.getCelular());
            funcionarioDto.setCtps(funcionario.getCtps());
            funcionarioDto.setPis(funcionario.getPis());
            funcionarioDto.setNascimento(funcionario.getNascimento());

            //todos os atributos aqui
            return funcionarioDto;
        }).collect(Collectors.toList());
    }

    public FuncionarioDto update(Long id, FuncionarioDto funcionarioDetails) {
        Optional<FuncionarioModel> optionalFuncionario = funcionarioRepository.findById(id);

        if (optionalFuncionario.isPresent()) {
            FuncionarioModel funcionarioModel = optionalFuncionario.get();
            funcionarioModel.setNome(funcionarioDetails.getNome());
            funcionarioModel.setRg(funcionarioDetails.getRg());
            funcionarioModel.setOrgaoEmissor(funcionarioDetails.getOrgaoEmissor());
            funcionarioModel.setCpf(funcionarioDetails.getCpf());
            funcionarioModel.setEndereco(funcionarioDetails.getEndereco());
            funcionarioModel.setNumero(funcionarioDetails.getNumero());
            funcionarioModel.setCidade(funcionarioDetails.getCidade());
            funcionarioModel.setBairro(funcionarioDetails.getBairro());
            funcionarioModel.setEstado(funcionarioDetails.getEstado());
            funcionarioModel.setComplemento(funcionarioDetails.getComplemento());
            funcionarioModel.setTelefone(funcionarioDetails.getTelefone());
            funcionarioModel.setCelular(funcionarioDetails.getCelular());
            funcionarioModel.setPis(funcionarioDetails.getPis());
            funcionarioModel.setCtps(funcionarioDetails.getCtps());
            funcionarioModel.setNascimento(funcionarioDetails.getNascimento());

            FuncionarioModel updatedFuncionario = funcionarioRepository.save(funcionarioModel);

            FuncionarioDto funcionarioDto = new FuncionarioDto();
            funcionarioDto.setId(updatedFuncionario.getId());
            funcionarioDto.setNome(updatedFuncionario.getNome());
            funcionarioDto.setRg(updatedFuncionario.getRg());
            funcionarioDto.setOrgaoEmissor(updatedFuncionario.getOrgaoEmissor());
            funcionarioDto.setCpf(updatedFuncionario.getCpf());
            funcionarioDto.setEndereco(updatedFuncionario.getEndereco());
            funcionarioDto.setNumero(updatedFuncionario.getNumero());
            funcionarioDto.setCidade(updatedFuncionario.getCidade());
            funcionarioDto.setBairro(updatedFuncionario.getBairro());
            funcionarioDto.setEstado(updatedFuncionario.getEstado());
            funcionarioDto.setComplemento(updatedFuncionario.getComplemento());
            funcionarioDto.setTelefone(updatedFuncionario.getTelefone());
            funcionarioDto.setCelular(updatedFuncionario.getCelular());
            funcionarioDto.setPis(updatedFuncionario.getPis());
            funcionarioDto.setCtps(updatedFuncionario.getCtps());
            funcionarioDto.setNascimento(updatedFuncionario.getNascimento());

            //outros atributos

            return funcionarioDto;
        } else {
            return null;
        }
    }

    public FuncionarioDto save(FuncionarioCreateRequest funcionarioRequest) {

        FuncionarioModel funcionarioModel = modelMapper.map(funcionarioRequest, FuncionarioModel.class);
        FuncionarioModel savedFuncionario = funcionarioRepository.save(funcionarioModel);
        FuncionarioDto funcionarioDto = modelMapper.map(savedFuncionario, FuncionarioDto.class);
        return funcionarioDto;
    }

    public FuncionarioDto findById(Long id) {
        Optional<FuncionarioModel> optionalFuncionario = funcionarioRepository.findById(id);
        if (optionalFuncionario.isPresent()) {

            FuncionarioModel funcionarioModel = optionalFuncionario.get();
            FuncionarioDto funcionarioDto = new FuncionarioDto();
            funcionarioDto.setId(funcionarioModel.getId());
            funcionarioDto.setNome(funcionarioModel.getNome());
            funcionarioDto.setRg(funcionarioModel.getRg());
            funcionarioDto.setOrgaoEmissor(funcionarioModel.getOrgaoEmissor());
            funcionarioDto.setCpf(funcionarioModel.getCpf());
            funcionarioDto.setEndereco(funcionarioModel.getEndereco());
            funcionarioDto.setNumero(funcionarioModel.getNumero());
            funcionarioDto.setComplemento(funcionarioModel.getComplemento());
            funcionarioDto.setBairro(funcionarioModel.getBairro());
            funcionarioDto.setCidade(funcionarioModel.getCidade());
            funcionarioDto.setEstado(funcionarioModel.getEstado());
            funcionarioDto.setTelefone(funcionarioModel.getTelefone());
            funcionarioDto.setCelular(funcionarioModel.getCelular());
            funcionarioDto.setCtps(funcionarioModel.getCtps());
            funcionarioDto.setPis(funcionarioModel.getPis());
            funcionarioDto.setNascimento(funcionarioModel.getNascimento());
            ///atributos
            return funcionarioDto;
        } else {
            return null;
        }
    }
}