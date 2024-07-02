package br.edu.imepac.services;

import br.edu.imepac.dtos.EspecialidadeCreateRequest;
import br.edu.imepac.dtos.EspecialidadeDto;
import br.edu.imepac.models.EspecialidadeModel;
import br.edu.imepac.repositories.EspecialidadeRepository;
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
public class EspecialidadeServiceTest {

    @Mock
    private EspecialidadeRepository especialidadeRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EspecialidadeService especialidadeService;

    private EspecialidadeModel especialidadeModel;
    private EspecialidadeDto especialidadeDto;
    private EspecialidadeCreateRequest especialidadeCreateRequest;

    @BeforeEach
    void setUp() {
        especialidadeModel = new EspecialidadeModel();
        especialidadeModel.setId(1L);
        especialidadeModel.setDescricao("Cardiologia");

        especialidadeDto = new EspecialidadeDto();
        especialidadeDto.setId(1L);
        especialidadeDto.setDescricao("Cardiologia");

        especialidadeCreateRequest = new EspecialidadeCreateRequest();
        especialidadeCreateRequest.setDescricao("Cardiologia");
    }

    @Test
    void testDelete() {
        doNothing().when(especialidadeRepository).deleteById(1L);
        especialidadeService.delete(1L);
        verify(especialidadeRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindAll() {
        when(especialidadeRepository.findAll()).thenReturn(Arrays.asList(especialidadeModel));
        List<EspecialidadeDto> result = especialidadeService.findAll();
        assertEquals(1, result.size());
        assertEquals("Cardiologia", result.get(0).getDescricao());
    }

    @Test
    void testUpdate() {
        when(especialidadeRepository.findById(1L)).thenReturn(Optional.of(especialidadeModel));
        when(especialidadeRepository.save(any(EspecialidadeModel.class))).thenReturn(especialidadeModel);

        EspecialidadeDto updatedDto = especialidadeService.update(1L, especialidadeDto);
        assertNotNull(updatedDto);
        assertEquals("Cardiologia", updatedDto.getDescricao());
    }

    @Test
    void testUpdate_NotFound() {
        when(especialidadeRepository.findById(1L)).thenReturn(Optional.empty());
        EspecialidadeDto updatedDto = especialidadeService.update(1L, especialidadeDto);
        assertNull(updatedDto);
    }

    @Test
    void testSave() {
        when(modelMapper.map(especialidadeCreateRequest, EspecialidadeModel.class)).thenReturn(especialidadeModel);
        when(especialidadeRepository.save(any(EspecialidadeModel.class))).thenReturn(especialidadeModel);
        when(modelMapper.map(especialidadeModel, EspecialidadeDto.class)).thenReturn(especialidadeDto);

        EspecialidadeDto savedDto = especialidadeService.save(especialidadeCreateRequest);
        assertNotNull(savedDto);
        assertEquals("Cardiologia", savedDto.getDescricao());
    }

    @Test
    void testFindById() {
        when(especialidadeRepository.findById(1L)).thenReturn(Optional.of(especialidadeModel));
        EspecialidadeDto foundDto = especialidadeService.findById(1L);
        assertNotNull(foundDto);
        assertEquals("Cardiologia", foundDto.getDescricao());
    }

    @Test
    void testFindById_NotFound() {
        when(especialidadeRepository.findById(1L)).thenReturn(Optional.empty());
        EspecialidadeDto foundDto = especialidadeService.findById(1L);
        assertNull(foundDto);
    }
}