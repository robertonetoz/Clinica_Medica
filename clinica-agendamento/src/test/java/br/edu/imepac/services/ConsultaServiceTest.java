package br.edu.imepac.services;

import br.edu.imepac.dtos.ConsultaCreateRequest;
import br.edu.imepac.dtos.ConsultaDto;
import br.edu.imepac.models.ConsultaModel;
import br.edu.imepac.models.PacienteModel;
import br.edu.imepac.models.MedicoModel;
import br.edu.imepac.repositories.ConsultaRepository;
import br.edu.imepac.repositories.PacienteRepository;
import br.edu.imepac.repositories.MedicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConsultaServiceTest {

    @Mock
    private ConsultaRepository consultaRepository;

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private MedicoRepository medicoRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ConsultaService consultaService;

    private ConsultaModel consultaModel;
    private ConsultaDto consultaDto;
    private PacienteModel pacienteModel;
    private MedicoModel medicoModel;
    private ConsultaCreateRequest consultaCreateRequest;

    @BeforeEach
    public void setUp() {
        consultaModel = new ConsultaModel();
        consultaModel.setId(1L);
        consultaModel.setData("2023-10-10");
        consultaModel.setHora("10:00");
        consultaModel.setStatus("AGENDADA");

        consultaDto = new ConsultaDto();
        consultaDto.setId(1L);
        consultaDto.setData("2023-10-10");
        consultaDto.setHora("10:00");
        consultaDto.setStatus("AGENDADA");

        pacienteModel = new PacienteModel();
        pacienteModel.setId(1L);

        medicoModel = new MedicoModel();
        medicoModel.setId(1L);

        consultaCreateRequest = new ConsultaCreateRequest();
        consultaCreateRequest.setCd_paciente(1L);
        consultaCreateRequest.setCd_medico(1L);
        consultaCreateRequest.setData("2023-10-10");
        consultaCreateRequest.setHora("10:00");
    }

    @Test
    public void testAgendarConsulta_Success() {
        when(consultaRepository.existsByDataAndHora(anyString(), anyString())).thenReturn(false);
        when(pacienteRepository.findById(anyLong())).thenReturn(Optional.of(pacienteModel));
        when(medicoRepository.findById(anyLong())).thenReturn(Optional.of(medicoModel));
        when(consultaRepository.save(any(ConsultaModel.class))).thenReturn(consultaModel);
        when(modelMapper.map(any(ConsultaModel.class), eq(ConsultaDto.class))).thenReturn(consultaDto);

        ConsultaDto result = consultaService.agendarConsulta(consultaCreateRequest);

        assertNotNull(result);
        assertEquals("2023-10-10", result.getData());
        assertEquals("10:00", result.getHora());
        assertEquals("AGENDADA", result.getStatus());
    }

    @Test
    public void testAgendarConsulta_ConsultaAlreadyExists() {
        when(consultaRepository.existsByDataAndHora(anyString(), anyString())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            consultaService.agendarConsulta(consultaCreateRequest);
        });

        assertEquals("Já existe uma consulta agendada para essa data e hora.", exception.getMessage());
    }

    @Test
    public void testAgendarConsulta_PacienteOrMedicoNotFound() {
        when(consultaRepository.existsByDataAndHora(anyString(), anyString())).thenReturn(false);
        when(pacienteRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(medicoRepository.findById(anyLong())).thenReturn(Optional.of(medicoModel));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            consultaService.agendarConsulta(consultaCreateRequest);
        });

        assertEquals("Paciente ou Médico não encontrado.", exception.getMessage());
    }

    @Test
    public void testCancelarConsulta_Success() {
        when(consultaRepository.findById(anyLong())).thenReturn(Optional.of(consultaModel));
        when(consultaRepository.save(any(ConsultaModel.class))).thenReturn(consultaModel);
        when(modelMapper.map(any(ConsultaModel.class), eq(ConsultaDto.class))).thenReturn(consultaDto);

        ConsultaDto result = consultaService.cancelarConsulta(1L, "Motivo de cancelamento");

        assertNotNull(result);
        assertEquals("CANCELADA", result.getStatus());
    }

    @Test
    public void testCancelarConsulta_ConsultaNotFound() {
        when(consultaRepository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            consultaService.cancelarConsulta(1L, "Motivo de cancelamento");
        });

        assertEquals("Consulta não encontrada.", exception.getMessage());
    }

    @Test
    public void testRegistrarRetorno_Success() {
        when(consultaRepository.findById(anyLong())).thenReturn(Optional.of(consultaModel));
        when(consultaRepository.save(any(ConsultaModel.class))).thenReturn(consultaModel);
        when(modelMapper.map(any(ConsultaModel.class), eq(ConsultaDto.class))).thenReturn(consultaDto);

        ConsultaDto result = consultaService.registrarRetorno(1L, "2023-10-11", "11:00");

        assertNotNull(result);
        assertEquals("RETORNO", result.getStatus());
    }

    @Test
    public void testRegistrarRetorno_ConsultaNotFound() {
        when(consultaRepository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            consultaService.registrarRetorno(1L, "2023-10-11", "11:00");
        });

        assertEquals("Consulta não encontrada.", exception.getMessage());
    }

    @Test
    public void testFindAll() {
        when(consultaRepository.findAll()).thenReturn(Arrays.asList(consultaModel));
        when(modelMapper.map(any(ConsultaModel.class), eq(ConsultaDto.class))).thenReturn(consultaDto);

        List<ConsultaDto> result = consultaService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testFindById_Success() {
        when(consultaRepository.findById(anyLong())).thenReturn(Optional.of(consultaModel));
        when(modelMapper.map(any(ConsultaModel.class), eq(ConsultaDto.class))).thenReturn(consultaDto);

        ConsultaDto result = consultaService.findById(1L);

        assertNotNull(result);
    }

    @Test
    public void testFindById_NotFound() {
        when(consultaRepository.findById(anyLong())).thenReturn(Optional.empty());

        ConsultaDto result = consultaService.findById(1L);

        assertNull(result);
    }
}