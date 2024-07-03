package br.edu.imepac.services;

import br.edu.imepac.dtos.ConsultaCreateRequest;
import br.edu.imepac.dtos.ConsultaDto;
import br.edu.imepac.models.ConsultaModel;
import br.edu.imepac.models.PacienteModel;
import br.edu.imepac.models.MedicoModel;
import br.edu.imepac.repositories.ConsultaRepository;
import br.edu.imepac.repositories.PacienteRepository;
import br.edu.imepac.repositories.MedicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ConsultaDto agendarConsulta(ConsultaCreateRequest consultaCreateRequest) {
        if (consultaRepository.existsByDataAndHora(consultaCreateRequest.getData(), consultaCreateRequest.getHora())) {
            throw new RuntimeException("Já existe uma consulta agendada para essa data e hora.");
        }
        ConsultaModel consulta = new ConsultaModel();
        Optional<PacienteModel> paciente = pacienteRepository.findById(consultaCreateRequest.getCd_paciente());
        Optional<MedicoModel> medico = medicoRepository.findById(consultaCreateRequest.getCd_medico());
        if (paciente.isPresent() && medico.isPresent()) {
            consulta.setPaciente(paciente.get());
            consulta.setMedico(medico.get());
            consulta.setData(consultaCreateRequest.getData());
            consulta.setHora(consultaCreateRequest.getHora());
            consulta.setStatus("AGENDADA");
            ConsultaModel savedConsulta = consultaRepository.save(consulta);
            return modelMapper.map(savedConsulta, ConsultaDto.class);
        } else {
            throw new RuntimeException("Paciente ou Médico não encontrado.");
        }
    }




    public ConsultaDto cancelarConsulta(Long id, String motivo_cancelamento) {
        Optional<ConsultaModel> consultaOptional = consultaRepository.findById(id);
        if (consultaOptional.isPresent()) {
            ConsultaModel consulta = consultaOptional.get();
            consulta.setStatus("CANCELADA");
            consulta.setMotivo_cancelamento(motivo_cancelamento);
            ConsultaModel savedConsulta = consultaRepository.save(consulta);
            return modelMapper.map(savedConsulta, ConsultaDto.class);
        } else {
            throw new RuntimeException("Consulta não encontrada.");
        }
    }

    public ConsultaDto registrarRetorno(Long id, String data, String hora) {
        Optional<ConsultaModel> consultaOptional = consultaRepository.findById(id);
        if (consultaOptional.isPresent()) {
            ConsultaModel consulta = consultaOptional.get();
            consulta.setData(data);
            consulta.setHora(hora);
            consulta.setStatus("RETORNO");
            ConsultaModel savedConsulta = consultaRepository.save(consulta);
            return modelMapper.map(savedConsulta, ConsultaDto.class);
        } else {
            throw new RuntimeException("Consulta não encontrada.");
        }}


    public List<ConsultaDto> findAll() {
        List<ConsultaModel> consultas = consultaRepository.findAll();
        return consultas.stream()
                .map(consulta -> modelMapper.map(consulta, ConsultaDto.class))
                .collect(Collectors.toList());
    }




    public ConsultaDto findById(Long id) {
        Optional<ConsultaModel> consultaOptional = consultaRepository.findById(id);
        if (consultaOptional.isPresent()) {
            return modelMapper.map(consultaOptional.get(), ConsultaDto.class);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        consultaRepository.deleteById(id);

    }
}