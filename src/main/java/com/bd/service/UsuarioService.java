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

    public boolean validarUsuario(UserLoginDTO userDTO) {
        Login login = Login.getInstance();
        login.setUser(userDTO.getUsername());
        login.setSenha(userDTO.getPassword());

        return Conexao.authenticateUser();
    }

    public List<UserResponse> buscarUsuarios(UserLoginDTO userDTO) {
        try {
            criarLogin(userDTO);
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

    public UserResponse buscarUsuarioPeloId(Long id, UserLoginDTO userDTO) {
        try {
            criarLogin(userDTO);

            Usuario usuario = usuarioRepository.buscarUsuarioPeloId(id);
            return usuarioMapper.entityToResponse(usuario);
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar usuário: " + e.getMessage());
        }
    }

    public UserResponse atualizarUsuario(Long id, UserRegistrationRequest userRequest) {
        criarLogin(userRequest.getUserLoginDTO());
        Usuario user = usuarioMapper.postDtoToEntity(userRequest.getUserRequest());
        usuarioRepository.atualizarUsuario(id, user);

        return usuarioMapper.entityToResponse(user);
    }

    public boolean deletarUsuario(Long id, UserLoginDTO userDTO) {
        criarLogin(userDTO);
        return usuarioRepository.deletarUsuario(id);
    }

    private void criarLogin(UserLoginDTO userDTO) {
        Login login = Login.getInstance();
        login.setUser(userDTO.getUsername());
        login.setSenha(userDTO.getPassword());
    }
}
