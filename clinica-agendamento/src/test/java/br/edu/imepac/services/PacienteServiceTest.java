package br.edu.imepac.services;

import br.edu.imepac.dtos.PacienteCreateRequest;
import br.edu.imepac.dtos.PacienteDto;
import br.edu.imepac.models.PacienteModel;
import br.edu.imepac.repositories.PacienteRepository;
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
public class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PacienteService pacienteService;

    private PacienteModel pacienteModel;
    private PacienteDto pacienteDto;
    private PacienteCreateRequest pacienteCreateRequest;

    @BeforeEach
    public void setUp() {
        pacienteModel = new PacienteModel();
        pacienteModel.setId(1L);
        pacienteModel.setNome("John Doe");

        pacienteDto = new PacienteDto();
        pacienteDto.setId(1L);
        pacienteDto.setNome("John Doe");

        pacienteCreateRequest = new PacienteCreateRequest();
        pacienteCreateRequest.setNome("John Doe");
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        doNothing().when(pacienteRepository).deleteById(id);

        pacienteService.delete(id);

        verify(pacienteRepository, times(1)).deleteById(id);
    }

    @Test
    public void testFindAll() {
        when(pacienteRepository.findAll()).thenReturn(Arrays.asList(pacienteModel));
        when(modelMapper.map(any(PacienteModel.class), eq(PacienteDto.class))).thenReturn(pacienteDto);

        List<PacienteDto> result = pacienteService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getNome());
    }

    @Test
    public void testUpdate() {
        Long id = 1L;
        when(pacienteRepository.findById(id)).thenReturn(Optional.of(pacienteModel));
        when(pacienteRepository.save(any(PacienteModel.class))).thenReturn(pacienteModel);
        when(modelMapper.map(any(PacienteModel.class), eq(PacienteDto.class))).thenReturn(pacienteDto);

        PacienteDto result = pacienteService.update(id, pacienteDto);

        assertNotNull(result);
        assertEquals("John Doe", result.getNome());
    }

    @Test
    public void testUpdateNotFound() {
        Long id = 1L;
        when(pacienteRepository.findById(id)).thenReturn(Optional.empty());

        PacienteDto result = pacienteService.update(id, pacienteDto);

        assertNull(result);
    }

    @Test
    public void testSave() {
        when(modelMapper.map(any(PacienteCreateRequest.class), eq(PacienteModel.class))).thenReturn(pacienteModel);
        when(pacienteRepository.save(any(PacienteModel.class))).thenReturn(pacienteModel);
        when(modelMapper.map(any(PacienteModel.class), eq(PacienteDto.class))).thenReturn(pacienteDto);

        PacienteDto result = pacienteService.save(pacienteCreateRequest);

        assertNotNull(result);
        assertEquals("John Doe", result.getNome());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        when(pacienteRepository.findById(id)).thenReturn(Optional.of(pacienteModel));
        when(modelMapper.map(any(PacienteModel.class), eq(PacienteDto.class))).thenReturn(pacienteDto);

        PacienteDto result = pacienteService.findById(id);

        assertNotNull(result);
        assertEquals("John Doe", result.getNome());
    }

    @Test
    public void testFindByIdNotFound() {
        Long id = 1L;
        when(pacienteRepository.findById(id)).thenReturn(Optional.empty());

        PacienteDto result = pacienteService.findById(id);

        assertNull(result);
    }
}