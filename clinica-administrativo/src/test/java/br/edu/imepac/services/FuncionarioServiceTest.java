package br.edu.imepac.services;

import br.edu.imepac.dtos.FuncionarioCreateRequest;
import br.edu.imepac.dtos.FuncionarioDto;
import br.edu.imepac.models.FuncionarioModel;
import br.edu.imepac.repositories.FuncionarioRepository;
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
public class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FuncionarioService funcionarioService;

    private FuncionarioModel funcionarioModel;
    private FuncionarioDto funcionarioDto;
    private FuncionarioCreateRequest funcionarioCreateRequest;

    @BeforeEach
    void setUp() {
        funcionarioModel = new FuncionarioModel();
        funcionarioModel.setId(1L);
        funcionarioModel.setNome("John Doe");
        // Set other attributes...

        funcionarioDto = new FuncionarioDto();
        funcionarioDto.setId(1L);
        funcionarioDto.setNome("John Doe");
        // Set other attributes...

        funcionarioCreateRequest = new FuncionarioCreateRequest();
        funcionarioCreateRequest.setNome("John Doe");
        // Set other attributes...
    }

    @Test
    void testDelete() {
        Long id = 1L;
        doNothing().when(funcionarioRepository).deleteById(id);
        funcionarioService.delete(id);
        verify(funcionarioRepository, times(1)).deleteById(id);
    }

    @Test
    void testFindAll() {
        when(funcionarioRepository.findAll()).thenReturn(Arrays.asList(funcionarioModel));
        List<FuncionarioDto> result = funcionarioService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getNome());
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(funcionarioModel));
        when(funcionarioRepository.save(any(FuncionarioModel.class))).thenReturn(funcionarioModel);

        FuncionarioDto result = funcionarioService.update(id, funcionarioDto);
        assertNotNull(result);
        assertEquals("John Doe", result.getNome());
    }

    @Test
    void testUpdate_NotFound() {
        Long id = 1L;
        when(funcionarioRepository.findById(id)).thenReturn(Optional.empty());

        FuncionarioDto result = funcionarioService.update(id, funcionarioDto);
        assertNull(result);
    }

    @Test
    void testSave() {
        when(modelMapper.map(any(FuncionarioCreateRequest.class), eq(FuncionarioModel.class))).thenReturn(funcionarioModel);
        when(funcionarioRepository.save(any(FuncionarioModel.class))).thenReturn(funcionarioModel);
        when(modelMapper.map(any(FuncionarioModel.class), eq(FuncionarioDto.class))).thenReturn(funcionarioDto);

        FuncionarioDto result = funcionarioService.save(funcionarioCreateRequest);
        assertNotNull(result);
        assertEquals("John Doe", result.getNome());
    }

    @Test
    void testFindById() {
        Long id = 1L;
        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(funcionarioModel));

        FuncionarioDto result = funcionarioService.findById(id);
        assertNotNull(result);
        assertEquals("John Doe", result.getNome());
    }

    @Test
    void testFindById_NotFound() {
        Long id = 1L;
        when(funcionarioRepository.findById(id)).thenReturn(Optional.empty());

        FuncionarioDto result = funcionarioService.findById(id);
        assertNull(result);
    }
}