package br.edu.imepac.services;

import br.edu.imepac.dtos.UsuarioCreateRequest;
import br.edu.imepac.dtos.UsuarioDto;
import br.edu.imepac.models.UsuarioModel;
import br.edu.imepac.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private ModelMapper modelMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }



    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }



    public List<UsuarioDto> findAll() {
        List<UsuarioModel> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuario -> {
            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setId(usuario.getId());
            usuarioDto.setUsuario(usuario.getUsuario());
            usuarioDto.setSenha(usuario.getSenha());

            return usuarioDto;
        }).collect(Collectors.toList());
    }




    public UsuarioDto update(Long id, UsuarioDto usuarioDetails) {
        Optional<UsuarioModel> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {
            UsuarioModel usuarioModel = optionalUsuario.get();
            usuarioModel.setUsuario(usuarioDetails.getUsuario());
            usuarioModel.setSenha(usuarioDetails.getSenha());

            UsuarioModel updatedUsuario = usuarioRepository.save(usuarioModel);

            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setId(updatedUsuario.getId());
            usuarioDto.setUsuario(updatedUsuario.getUsuario());
            usuarioDto.setSenha(updatedUsuario.getSenha());


            return usuarioDto;
        } else {
            return null;
        }
    }




    public UsuarioDto save(UsuarioCreateRequest usuarioRequest) {
        UsuarioModel usuarioModel = modelMapper.map(usuarioRequest, UsuarioModel.class);
        UsuarioModel savedUsuario = usuarioRepository.save(usuarioModel);
        UsuarioDto usuarioDto = modelMapper.map(usuarioModel    , UsuarioDto.class);
        return usuarioDto;
    }



    public UsuarioDto findById(Long id) {
        Optional<UsuarioModel> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {

            UsuarioModel usuarioModel = optionalUsuario.get();
            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setId(usuarioModel.getId());
            usuarioDto.setUsuario(usuarioModel.getUsuario());
            usuarioDto.setSenha(usuarioModel.getSenha());



            return usuarioDto;
        } else {
            return null;
        }
    }
}