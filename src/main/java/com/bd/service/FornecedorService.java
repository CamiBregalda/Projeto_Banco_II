package com.bd.service;

import com.bd.exception.BusinessException;
import com.bd.infra.Conexao;
import com.bd.infra.Login;
import com.bd.mapper.FornecedorMapper;
import com.bd.mapper.UsuarioMapper;
import com.bd.model.Fornecedor;
import com.bd.model.Usuario;
import com.bd.model.request.FornecedorRequest;
import com.bd.model.request.UserLoginDTO;
import com.bd.model.request.UserRequest;
import com.bd.model.response.FornecedorResponse;
import com.bd.model.response.UserResponse;
import com.bd.repository.FornecedorRepository;
import com.bd.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FornecedorService {
    private final FornecedorRepository fornecedorRepository;
    private final FornecedorMapper fornecedorMapper;


    public boolean validarFornecedor(UserLoginDTO userDTO) {
        Login login = Login.getInstance();
        login.setUser(userDTO.getUsername());
        login.setSenha(userDTO.getPassword());

        return Conexao.authenticateUser();
    }


    public FornecedorResponse cadastrarFornecedor(FornecedorRequest fornecedorRequest) {
        Fornecedor fornecedor = fornecedorMapper.postDtoToEntity(fornecedorRequest);
        return fornecedorMapper.entityToResponse(fornecedorRepository.cadastrarFornecedor(fornecedor));
    }



    public List<FornecedorResponse> buscarFornecedores(UserLoginDTO userDTO) {
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

    public FornecedorResponse buscarFornecedorPeloId(Long id, UserLoginDTO userDTO) {
        try {
            Fornecedor fornecedor = fornecedorRepository.buscarFornecedorPeloId(id);
            return fornecedorMapper.entityToResponse(fornecedor);
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar fornecedores: " + e.getMessage());
        }
    }

    public FornecedorResponse atualizarFornecedor(Long id, FornecedorRequest fornecedorRequest, UserLoginDTO userDTO) {
        Fornecedor fornecedor = fornecedorMapper.postDtoToEntity(fornecedorRequest);
        fornecedorRepository.atualizarFornecedor(id, fornecedor);

        return fornecedorMapper.entityToResponse(fornecedor);
    }

    public boolean deletarFornecedor(Long id, UserLoginDTO userDTO) {
        return fornecedorRepository.deletarFornecedor(id);
    }

    private void criarLogin(UserLoginDTO userDTO) {
        Login login = Login.getInstance();
        login.setUser(userDTO.getUsername());
        login.setSenha(userDTO.getPassword());
    }


}
