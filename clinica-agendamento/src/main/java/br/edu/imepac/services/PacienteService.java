package br.edu.imepac.services;

import br.edu.imepac.dtos.PacienteCreateRequest;
import br.edu.imepac.dtos.PacienteDtos;
import br.edu.imepac.models.PacienteModels;
import br.edu.imepac.repositories.PacienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;

    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
    }

    public void delete(Long id) {
        pacienteRepository.deleteById(id);
    }

    public List<PacienteDtos> findAll() {
        List<PacienteModels> pacientes = pacienteRepository.findAll();
        return pacientes.stream().map(paciente -> modelMapper.map(paciente, PacienteDtos.class)).collect(Collectors.toList());
    }

    public PacienteDtos update(Long id, PacienteDtos pacienteDetails) {
        Optional<PacienteModels> optionalPaciente = pacienteRepository.findById(id);

        if (optionalPaciente.isPresent()) {
            PacienteModels pacienteModel = optionalPaciente.get();
            pacienteModel.setNome(pacienteDetails.getNome());
            pacienteModel.setRg(pacienteDetails.getRg());
            pacienteModel.setOrgaoEmissor(pacienteDetails.getOrgaoEmissor());
            pacienteModel.setCpf(pacienteDetails.getCpf());
            pacienteModel.setEndereco(pacienteDetails.getEndereco());
            pacienteModel.setNumero(pacienteDetails.getNumero());
            pacienteModel.setComplemento(pacienteDetails.getComplemento());
            pacienteModel.setBairro(pacienteDetails.getBairro());
            pacienteModel.setCidade(pacienteDetails.getCidade());
            pacienteModel.setEstado(pacienteDetails.getEstado());
            pacienteModel.setTelefone(pacienteDetails.getTelefone());
            pacienteModel.setCelular(pacienteDetails.getCelular());
            pacienteModel.setSexo(pacienteDetails.getSexo());
            pacienteModel.setNascimento(pacienteDetails.getNascimento());
            pacienteModel.setPossuiConvenio(pacienteDetails.getPossuiConvenio());
            pacienteModel.setNomeConvenio(pacienteDetails.getNomeConvenio());

            PacienteModels updatedPaciente = pacienteRepository.save(pacienteModel);

            return modelMapper.map(updatedPaciente, PacienteDtos.class);
        } else {
            return null;
        }
    }

    public PacienteDtos save(PacienteCreateRequest pacienteRequest) {
        PacienteModels pacienteModel = modelMapper.map(pacienteRequest, PacienteModels.class);
        PacienteModels savedPaciente = pacienteRepository.save(pacienteModel);
        return modelMapper.map(savedPaciente, PacienteDtos.class);
    }

    public PacienteDtos findById(Long id) {
        Optional<PacienteModels> optionalPaciente = pacienteRepository.findById(id);
        return optionalPaciente.map(pacienteModel -> modelMapper.map(pacienteModel, PacienteDtos.class)).orElse(null);
    }
}
