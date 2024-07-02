package br.edu.imepac.services;

import br.edu.imepac.dtos.ProntuarioDto;
import br.edu.imepac.models.ProntuarioModel;
import br.edu.imepac.repositories.ProntuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProntuarioServiceTest {

    @Mock
    private ProntuarioRepository prontuarioRepository;

    @InjectMocks
    private ProntuarioService prontuarioService;

    private ProntuarioModel prontuarioModel;
    private ProntuarioDto prontuarioDto;

    @BeforeEach
    void setUp() {
        prontuarioModel = new ProntuarioModel();
        prontuarioModel.setId(1L);
        prontuarioModel.setRegistro("Registro");
        prontuarioModel.setHistorico("Historico");
        prontuarioModel.setReceituario("Receituario");
        prontuarioModel.setExames("Exames");

        prontuarioDto = new ProntuarioDto();
        prontuarioDto.setId(1L);
        prontuarioDto.setRegistro("Registro");
        prontuarioDto.setHistorico("Historico");
        prontuarioDto.setReceituario("Receituario");
        prontuarioDto.setExames("Exames");
    }

    @Test
    void testFindAll() {
        when(prontuarioRepository.findAll()).thenReturn(Arrays.asList(prontuarioModel));

        List<ProntuarioDto> result = prontuarioService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(prontuarioDto.getId(), result.get(0).getId());
        verify(prontuarioRepository, times(1)).findAll();
    }

    @Test
    void testCreate() {
        when(prontuarioRepository.save(any(ProntuarioModel.class))).thenReturn(prontuarioModel);

        ProntuarioDto result = prontuarioService.create(prontuarioDto);

        assertNotNull(result);
        assertEquals(prontuarioDto.getId(), result.getId());
        verify(prontuarioRepository, times(1)).save(any(ProntuarioModel.class));
    }

    @Test
    void testUpdate() {
        when(prontuarioRepository.findById(1L)).thenReturn(Optional.of(prontuarioModel));
        when(prontuarioRepository.save(any(ProntuarioModel.class))).thenReturn(prontuarioModel);

        Optional<ProntuarioDto> result = prontuarioService.update(1L, prontuarioDto);

        assertTrue(result.isPresent());
        assertEquals(prontuarioDto.getId(), result.get().getId());
        verify(prontuarioRepository, times(1)).findById(1L);
        verify(prontuarioRepository, times(1)).save(any(ProntuarioModel.class));
    }

    @Test
    void testUpdateNotFound() {
        when(prontuarioRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<ProntuarioDto> result = prontuarioService.update(1L, prontuarioDto);

        assertFalse(result.isPresent());
        verify(prontuarioRepository, times(1)).findById(1L);
        verify(prontuarioRepository, times(0)).save(any(ProntuarioModel.class));
    }

    @Test
    void testFindById() {
        when(prontuarioRepository.findById(1L)).thenReturn(Optional.of(prontuarioModel));

        Optional<ProntuarioDto> result = prontuarioService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(prontuarioDto.getId(), result.get().getId());
        verify(prontuarioRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        when(prontuarioRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<ProntuarioDto> result = prontuarioService.findById(1L);

        assertFalse(result.isPresent());
        verify(prontuarioRepository, times(1)).findById(1L);
    }

    @Test
    void testDelete() {
        when(prontuarioRepository.existsById(1L)).thenReturn(true);
        doNothing().when(prontuarioRepository).deleteById(1L);

        boolean result = prontuarioService.delete(1L);

        assertTrue(result);
        verify(prontuarioRepository, times(1)).existsById(1L);
        verify(prontuarioRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteNotFound() {
        when(prontuarioRepository.existsById(1L)).thenReturn(false);

        boolean result = prontuarioService.delete(1L);

        assertFalse(result);
        verify(prontuarioRepository, times(1)).existsById(1L);
        verify(prontuarioRepository, times(0)).deleteById(1L);
    }
}