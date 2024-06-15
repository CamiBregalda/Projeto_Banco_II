package com.bd.service;

import com.bd.exception.BusinessException;
import com.bd.infra.Conexao;
import com.bd.infra.Login;
import com.bd.mapper.UsuarioMapper;
import com.bd.model.Usuario;
import com.bd.model.request.UserLoginDTO;
import com.bd.model.request.UserRegistrationRequest;
import com.bd.model.request.UserRequest;
import com.bd.model.response.UserResponse;
import com.bd.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UserResponse cadastrarUsuario(UserRegistrationRequest userRequest) {
        criarLogin(userRequest.getUserLoginDTO());
        Usuario user = usuarioMapper.postDtoToEntity(userRequest.getUserRequest());
        return usuarioMapper.entityToResponse(usuarioRepository.cadastrarUsuario(user));
    }

    public List<UserResponse> buscarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioRepository.buscarUsuarios();

            List<UserResponse> userResponses = new ArrayList<>();
            for (Usuario usuario : usuarios) {
                userResponses.add(usuarioMapper.entityToResponse(usuario));
            }

            return userResponses;
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar usuários: " + e.getMessage());
        }
    }

    public UserResponse buscarUsuarioPeloId(Long id) {
        try {
            Usuario usuario = usuarioRepository.buscarUsuarioPeloId(id);
            return usuarioMapper.entityToResponse(usuario);
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar usuário: " + e.getMessage());
        }
    }

    public UserResponse atualizarUsuario(Long id, UserRequest userRequest) {
        Usuario user = usuarioMapper.postDtoToEntity(userRequest);
        usuarioRepository.atualizarUsuario(id, user);

        return usuarioMapper.entityToResponse(user);
    }

    public boolean deletarUsuario(Long id) {
        return usuarioRepository.deletarUsuario(id);
    }

    private void criarLogin(UserLoginDTO userDTO) {
        Login login = Login.getInstance();
        login.setUser(userDTO.getUsername());
        login.setSenha(userDTO.getPassword());
    }
}
