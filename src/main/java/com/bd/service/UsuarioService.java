package com.bd.service;

import com.bd.infra.Conexao;
import com.bd.infra.Login;
import com.bd.mapper.UsuarioMapper;
import com.bd.model.Usuario;
import com.bd.model.request.UserLoginDTO;
import com.bd.model.request.UserRequest;
import com.bd.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public void cadastrarUsuario(UserRequest userRequest) {
        Usuario user = usuarioMapper.postDtoToEntity(userRequest);
        usuarioRepository.cadastrarUsuario(user);
    }

    public boolean validarUsuario(UserLoginDTO userDTO) {
        Login login = Login.getInstance();
        login.setUser(userDTO.getUsername());
        login.setSenha(userDTO.getPassword());

        return Conexao.authenticateUser();
    }
}
