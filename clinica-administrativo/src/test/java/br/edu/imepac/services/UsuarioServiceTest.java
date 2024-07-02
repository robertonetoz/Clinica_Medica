package br.edu.imepac.services;

import br.edu.imepac.dtos.UsuarioCreateRequest;
import br.edu.imepac.dtos.UsuarioDto;
import br.edu.imepac.models.UsuarioModel;
import br.edu.imepac.repositories.UsuarioRepository;
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

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDelete() {
        Long id = 1L;
        doNothing().when(usuarioRepository).deleteById(id);
        usuarioService.delete(id);
        verify(usuarioRepository, times(1)).deleteById(id);
    }

    @Test
    void testFindAll() {
        UsuarioModel usuario1 = new UsuarioModel();
        usuario1.setId(1L);
        usuario1.setUsuario("user1");
        usuario1.setSenha("password1");

        UsuarioModel usuario2 = new UsuarioModel();
        usuario2.setId(2L);
        usuario2.setUsuario("user2");
        usuario2.setSenha("password2");

        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario1, usuario2));

        List<UsuarioDto> result = usuarioService.findAll();

        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUsuario());
        assertEquals("user2", result.get(1).getUsuario());
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        UsuarioDto usuarioDetails = new UsuarioDto();
        usuarioDetails.setUsuario("updatedUser");
        usuarioDetails.setSenha("updatedPassword");

        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setId(id);
        usuarioModel.setUsuario("oldUser");
        usuarioModel.setSenha("oldPassword");

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioModel));
        when(usuarioRepository.save(any(UsuarioModel.class))).thenReturn(usuarioModel);

        UsuarioDto result = usuarioService.update(id, usuarioDetails);

        assertNotNull(result);
        assertEquals("updatedUser", result.getUsuario());
        assertEquals("updatedPassword", result.getSenha());
    }

    @Test
    void testUpdate_NotFound() {
        Long id = 1L;
        UsuarioDto usuarioDetails = new UsuarioDto();
        usuarioDetails.setUsuario("updatedUser");
        usuarioDetails.setSenha("updatedPassword");

        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        UsuarioDto result = usuarioService.update(id, usuarioDetails);

        assertNull(result);
    }

    @Test
    void testSave() {
        UsuarioCreateRequest usuarioRequest = new UsuarioCreateRequest();
        usuarioRequest.setUsuario("newUser");
        usuarioRequest.setSenha("newPassword");

        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setUsuario("newUser");
        usuarioModel.setSenha("newPassword");

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setUsuario("newUser");
        usuarioDto.setSenha("newPassword");

        when(modelMapper.map(usuarioRequest, UsuarioModel.class)).thenReturn(usuarioModel);
        when(usuarioRepository.save(usuarioModel)).thenReturn(usuarioModel);
        when(modelMapper.map(usuarioModel, UsuarioDto.class)).thenReturn(usuarioDto);

        UsuarioDto result = usuarioService.save(usuarioRequest);

        assertNotNull(result);
        assertEquals("newUser", result.getUsuario());
        assertEquals("newPassword", result.getSenha());
    }

    @Test
    void testFindById() {
        Long id = 1L;
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setId(id);
        usuarioModel.setUsuario("user");
        usuarioModel.setSenha("password");

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioModel));

        UsuarioDto result = usuarioService.findById(id);

        assertNotNull(result);
        assertEquals("user", result.getUsuario());
        assertEquals("password", result.getSenha());
    }

    @Test
    void testFindById_NotFound() {
        Long id = 1L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        UsuarioDto result = usuarioService.findById(id);

        assertNull(result);
    }
}