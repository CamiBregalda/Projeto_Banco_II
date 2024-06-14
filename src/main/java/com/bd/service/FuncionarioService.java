package com.bd.service;

import com.bd.exception.BusinessException;
import com.bd.infra.Conexao;
import com.bd.infra.Login;
import com.bd.mapper.FuncionarioMapper;
import com.bd.model.Funcionario;
import com.bd.model.request.FuncionarioRegistrationRequest;
import com.bd.model.request.FuncionarioRequest;
import com.bd.model.request.UserLoginDTO;
import com.bd.model.response.FuncionarioResponse;
import com.bd.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class FuncionarioService {


    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;

    public FuncionarioResponse cadastrarFuncionario(FuncionarioRegistrationRequest funcionarioRegistrationRequest) {
        criarLogin(funcionarioRegistrationRequest.getUserLoginDTO());
        System.out.println(funcionarioRegistrationRequest.getFuncionarioRequest().toString());
        Funcionario funcio = funcionarioMapper.postDtoToEntity(funcionarioRegistrationRequest.getFuncionarioRequest());
        System.out.println(funcio.toString());

       FuncionarioResponse teste = funcionarioMapper.entityToResponse(funcionarioRepository.cadastrarFuncionario(funcio));
        System.out.println(teste.toString());
        return teste;
        //return funcionarioMapper.entityToResponse(funcionarioRepository.cadastrarFuncionario(funcio));
    }

    public boolean validarFuncionario(UserLoginDTO userDTO) {
        Login login = Login.getInstance();
        login.setUser(userDTO.getUsername());
        login.setSenha(userDTO.getPassword());

        return Conexao.authenticateUser();
    }

    public List<FuncionarioResponse> buscarFuncionarios(UserLoginDTO userDTO) {
        try {
            criarLogin(userDTO);
            List<Funcionario> funcionarios = funcionarioRepository.buscarFuncionarios();

            List<FuncionarioResponse> funcionarioResponses = new ArrayList<>();
            for (Funcionario funcionario : funcionarios) {
                funcionarioResponses.add(funcionarioMapper.entityToResponse(funcionario));
            }
            return funcionarioResponses;
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar funcionarios: " + e.getMessage());
        }
    }

    public FuncionarioResponse buscarFuncionarioPeloId(Long id, UserLoginDTO userDTO) {
        try {
            criarLogin(userDTO);

            Funcionario funcionario = funcionarioRepository.buscarFuncionarioPeloId(id);
            return funcionarioMapper.entityToResponse(funcionario);
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar funcionario: " + e.getMessage());
        }
    }

    public FuncionarioResponse atualizarFuncionario(Long id, FuncionarioRegistrationRequest funcionarioRegistrationRequest) {
        criarLogin(funcionarioRegistrationRequest.getUserLoginDTO());
        Funcionario funcio = funcionarioMapper.postDtoToEntity(funcionarioRegistrationRequest.getFuncionarioRequest());
        funcionarioRepository.atualizarFuncionario(id, funcio);
        funcio.setFun_codigo(Math.toIntExact(id));
        return funcionarioMapper.entityToResponse(funcio);
    }

    public boolean deletarFuncionario(Long id, UserLoginDTO userDTO) {
        return funcionarioRepository.deletarFuncionario(id);
    }
    private void criarLogin(UserLoginDTO userDTO) {
        Login login = Login.getInstance();
        login.setUser(userDTO.getUsername());
        login.setSenha(userDTO.getPassword());
    }

}