package br.edu.imepac.services;

import br.edu.imepac.dtos.ConvenioCreateRequest;
import br.edu.imepac.dtos.ConvenioDto;
import br.edu.imepac.models.ConvenioModel;
import br.edu.imepac.repositories.ConvenioRepository;
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
public class ConvenioServiceTest {

    @Mock
    private ConvenioRepository convenioRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ConvenioService convenioService;

    private ConvenioModel convenioModel;
    private ConvenioDto convenioDto;
    private ConvenioCreateRequest convenioCreateRequest;

    @BeforeEach
    void setUp() {
        convenioModel = new ConvenioModel();
        convenioModel.setId(1L);
        convenioModel.setEmpresa("Empresa Teste");
        convenioModel.setCNPJ("12345678901234");
        convenioModel.setTelefone("123456789");

        convenioDto = new ConvenioDto();
        convenioDto.setId(1L);
        convenioDto.setEmpresa("Empresa Teste");
        convenioDto.setCNPJ("12345678901234");
        convenioDto.setTelefone("123456789");

        convenioCreateRequest = new ConvenioCreateRequest();
        convenioCreateRequest.setEmpresa("Empresa Teste");
        convenioCreateRequest.setCNPJ("12345678901234");
        convenioCreateRequest.setTelefone("123456789");
    }

    @Test
    void testDelete() {
        doNothing().when(convenioRepository).deleteById(1L);
        convenioService.delete(1L);
        verify(convenioRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindAll() {
        when(convenioRepository.findAll()).thenReturn(Arrays.asList(convenioModel));
        List<ConvenioDto> result = convenioService.findAll();
        assertEquals(1, result.size());
        assertEquals("Empresa Teste", result.get(0).getEmpresa());
    }

    @Test
    void testUpdate() {
        when(convenioRepository.findById(1L)).thenReturn(Optional.of(convenioModel));
        when(convenioRepository.save(any(ConvenioModel.class))).thenReturn(convenioModel);

        ConvenioDto updatedDto = convenioService.update(1L, convenioDto);
        assertNotNull(updatedDto);
        assertEquals("Empresa Teste", updatedDto.getEmpresa());
    }

    @Test
    void testUpdateNotFound() {
        when(convenioRepository.findById(1L)).thenReturn(Optional.empty());
        ConvenioDto updatedDto = convenioService.update(1L, convenioDto);
        assertNull(updatedDto);
    }

    @Test
    void testSave() {
        when(modelMapper.map(convenioCreateRequest, ConvenioModel.class)).thenReturn(convenioModel);
        when(convenioRepository.save(any(ConvenioModel.class))).thenReturn(convenioModel);
        when(modelMapper.map(convenioModel, ConvenioDto.class)).thenReturn(convenioDto);

        ConvenioDto savedDto = convenioService.save(convenioCreateRequest);
        assertNotNull(savedDto);
        assertEquals("Empresa Teste", savedDto.getEmpresa());
    }

    @Test
    void testFindById() {
        when(convenioRepository.findById(1L)).thenReturn(Optional.of(convenioModel));
        ConvenioDto foundDto = convenioService.findById(1L);
        assertNotNull(foundDto);
        assertEquals("Empresa Teste", foundDto.getEmpresa());
    }

    @Test
    void testFindByIdNotFound() {
        when(convenioRepository.findById(1L)).thenReturn(Optional.empty());
        ConvenioDto foundDto = convenioService.findById(1L);
        assertNull(foundDto);
    }
}