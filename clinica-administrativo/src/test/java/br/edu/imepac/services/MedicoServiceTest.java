package br.edu.imepac.services;

import br.edu.imepac.dtos.MedicoCreateRequest;
import br.edu.imepac.dtos.MedicoDto;
import br.edu.imepac.models.MedicoModel;
import br.edu.imepac.repositories.MedicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MedicoServiceTest {

    @Mock
    private MedicoRepository medicoRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private MedicoService medicoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDelete() {
        Long id = 1L;
        medicoService.delete(id);
        verify(medicoRepository, times(1)).deleteById(id);
    }

    @Test
    void testFindAll() {
        MedicoModel medico1 = new MedicoModel();
        medico1.setId(1L);
        medico1.setNome("Dr. A");
        medico1.setCrm("12345");
        medico1.setEspecialidade("Cardiology");

        MedicoModel medico2 = new MedicoModel();
        medico2.setId(2L);
        medico2.setNome("Dr. B");
        medico2.setCrm("67890");
        medico2.setEspecialidade("Neurology");

        when(medicoRepository.findAll()).thenReturn(Arrays.asList(medico1, medico2));

        List<MedicoDto> result = medicoService.findAll();

        assertEquals(2, result.size());
        assertEquals("Dr. A", result.get(0).getNome());
        assertEquals("Dr. B", result.get(1).getNome());
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        MedicoDto medicoDetails = new MedicoDto();
        medicoDetails.setNome("Updated Name");
        medicoDetails.setCrm("Updated CRM");
        medicoDetails.setEspecialidade("Updated Specialty");

        MedicoModel medicoModel = new MedicoModel();
        medicoModel.setId(id);
        medicoModel.setNome("Old Name");
        medicoModel.setCrm("Old CRM");
        medicoModel.setEspecialidade("Old Specialty");

        when(medicoRepository.findById(id)).thenReturn(Optional.of(medicoModel));
        when(medicoRepository.save(any(MedicoModel.class))).thenReturn(medicoModel);

        MedicoDto result = medicoService.update(id, medicoDetails);

        assertNotNull(result);
        assertEquals("Updated Name", result.getNome());
        assertEquals("Updated CRM", result.getCrm());
        assertEquals("Updated Specialty", result.getEspecialidade());
    }

    @Test
    void testUpdate_NotFound() {
        Long id = 1L;
        MedicoDto medicoDetails = new MedicoDto();

        when(medicoRepository.findById(id)).thenReturn(Optional.empty());

        MedicoDto result = medicoService.update(id, medicoDetails);

        assertNull(result);
    }

    @Test
    void testSave() {
        MedicoCreateRequest medicoRequest = new MedicoCreateRequest();
        medicoRequest.setNome("New Medico");
        medicoRequest.setCrm("New CRM");
        medicoRequest.setEspecialidade("New Specialty");

        MedicoModel medicoModel = new MedicoModel();
        medicoModel.setNome("New Medico");
        medicoModel.setCrm("New CRM");
        medicoModel.setEspecialidade("New Specialty");

        MedicoDto medicoDto = new MedicoDto();
        medicoDto.setNome("New Medico");
        medicoDto.setCrm("New CRM");
        medicoDto.setEspecialidade("New Specialty");

        when(modelMapper.map(medicoRequest, MedicoModel.class)).thenReturn(medicoModel);
        when(medicoRepository.save(medicoModel)).thenReturn(medicoModel);
        when(modelMapper.map(medicoModel, MedicoDto.class)).thenReturn(medicoDto);

        MedicoDto result = medicoService.save(medicoRequest);

        assertNotNull(result);
        assertEquals("New Medico", result.getNome());
        assertEquals("New CRM", result.getCrm());
        assertEquals("New Specialty", result.getEspecialidade());
    }

    @Test
    void testFindById() {
        Long id = 1L;
        MedicoModel medicoModel = new MedicoModel();
        medicoModel.setId(id);
        medicoModel.setNome("Dr. A");
        medicoModel.setCrm("12345");
        medicoModel.setEspecialidade("Cardiology");

        when(medicoRepository.findById(id)).thenReturn(Optional.of(medicoModel));

        MedicoDto result = medicoService.findById(id);

        assertNotNull(result);
        assertEquals("Dr. A", result.getNome());
        assertEquals("12345", result.getCrm());
        assertEquals("Cardiology", result.getEspecialidade());
    }

    @Test
    void testFindById_NotFound() {
        Long id = 1L;

        when(medicoRepository.findById(id)).thenReturn(Optional.empty());

        MedicoDto result = medicoService.findById(id);

        assertNull(result);
    }
}