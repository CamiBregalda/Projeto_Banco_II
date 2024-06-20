package com.bd.service;

import com.bd.exception.BusinessException;
import com.bd.infra.Conexao;
import com.bd.infra.Login;
import com.bd.mapper.FornecedorMapper;
import com.bd.mapper.UsuarioMapper;
import com.bd.model.Fornecedor;
import com.bd.model.Funcionario;
import com.bd.model.Usuario;
import com.bd.model.request.FornecedorRegistrationRequest;
import com.bd.model.request.FornecedorRequest;
import com.bd.model.request.UserLoginDTO;
import com.bd.model.request.UserRequest;
import com.bd.model.response.FornecedorResponse;
import com.bd.model.response.FuncionarioResponse;
import com.bd.model.response.UserResponse;
import com.bd.repository.FornecedorRepository;
import com.bd.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FornecedorService {
    private final FornecedorRepository fornecedorRepository;
    private final FornecedorMapper fornecedorMapper;

    public FornecedorResponse cadastrarFornecedor(FornecedorRegistrationRequest fornecedorRequest) {
        criarLogin(fornecedorRequest.getUserLoginDTO());
        Fornecedor fornecedor = fornecedorMapper.postDtoToEntity(fornecedorRequest.getFornecedorRequest());
        return fornecedorMapper.entityToResponse(fornecedorRepository.cadastrarFornecedor(fornecedor));
    }

    public List<FornecedorResponse> buscarFornecedores() {
        try {
            List<Fornecedor> fornecedores = fornecedorRepository.buscarFornecedores();
            List<FornecedorResponse> fornecedorResponses = new ArrayList<>();
            for (Fornecedor fornecedor : fornecedores) {
                fornecedorResponses.add(fornecedorMapper.entityToResponse(fornecedor));
            }
            return fornecedorResponses;
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar fornecedores: " + e.getMessage());
        }
    }

    public FornecedorResponse buscarFornecedorPeloId(Long id) {
        try {
            Fornecedor fornecedor = fornecedorRepository.buscarFornecedorPeloId(id);
            return fornecedorMapper.entityToResponse(fornecedor);
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar fornecedores: " + e.getMessage());
        }
    }

    public FornecedorResponse buscarFornecedorPeloNome(String descricao) {
        try {
            Fornecedor fornecedor = fornecedorRepository.buscarFornecedorPeloNome(descricao);
            return fornecedorMapper.entityToResponse(fornecedor);
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar fornecedor: " + e.getMessage());
        }
    }

    public List<FornecedorResponse> buscarFornecedoresPeloNome(String descricao) {
        try {
            return fornecedorRepository.buscarFornecedores().stream()
                    .filter(fornecedor -> !fornecedor.getFor_descricao().contains(descricao))
                    .map(fornecedorMapper::entityToResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar fornecedores: " + e.getMessage());
        }
    }

    public FornecedorResponse atualizarFornecedor(Long id, FornecedorRequest fornecedorRequest) {
        Fornecedor fornecedor = fornecedorMapper.postDtoToEntity(fornecedorRequest);
        fornecedorRepository.atualizarFornecedor(id, fornecedor);
        fornecedor.setFor_codigo(Math.toIntExact(id));
        return fornecedorMapper.entityToResponse(fornecedor);
    }

    public boolean deletarFornecedor(Long id) {
        return fornecedorRepository.deletarFornecedor(id);
    }

    private void criarLogin(UserLoginDTO userDTO) {
        Login login = Login.getInstance();
        login.setUser(userDTO.getUsername());
        login.setSenha(userDTO.getPassword());
    }


}
