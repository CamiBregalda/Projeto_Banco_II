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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;

    public FuncionarioResponse cadastrarFuncionario(FuncionarioRegistrationRequest funcionarioRegistrationRequest) {
        criarLogin(funcionarioRegistrationRequest.getUserLoginDTO());
        Funcionario funcio = funcionarioMapper.postDtoToEntity(funcionarioRegistrationRequest.getFuncionarioRequest());

        return funcionarioMapper.entityToResponse(funcionarioRepository.cadastrarFuncionario(funcio));
    }

    public List<FuncionarioResponse> buscarFuncionarios() {
        try {
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

    public FuncionarioResponse buscarFuncionarioPeloId(Long id) {
        try {
            Funcionario funcionario = funcionarioRepository.buscarFuncionarioPeloId(id);
            return funcionarioMapper.entityToResponse(funcionario);
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar funcionario: " + e.getMessage());
        }
    }

    public FuncionarioResponse buscarFuncionarioPeloNome(String nome) {
        try {
            Funcionario funcionario = funcionarioRepository.buscarFuncionarioPeloNome(nome);
            return funcionarioMapper.entityToResponse(funcionario);
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar funcionario: " + e.getMessage());
        }
    }

    public FuncionarioResponse atualizarFuncionario(Long id, FuncionarioRequest funcionarioRequest) {
        Funcionario funcio = funcionarioMapper.postDtoToEntity(funcionarioRequest);
        funcionarioRepository.atualizarFuncionario(id, funcio);
        funcio.setFun_codigo(Math.toIntExact(id));
        return funcionarioMapper.entityToResponse(funcio);
    }

    public boolean deletarFuncionario(Long id) {
        return funcionarioRepository.deletarFuncionario(id);
    }

    public void realizarBackup(){
        try {
            String comando = "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\PostgreSQL 14\\SQL Shell (psql).Ink";;
            Process exec = Runtime.getRuntime().exec( comando );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void criarLogin(UserLoginDTO userDTO) {
        Login login = Login.getInstance();
        login.setUser(userDTO.getUsername());
        login.setSenha(userDTO.getPassword());
    }

    public String cadastrarRole(String role, ArrayList<String> usernames){
        try{
            funcionarioRepository.cadastrarRole(role, (String[]) usernames.toArray());
            return "Role foi cadastrada com sucesso!";
        }catch (Exception e){
            throw new BusinessException("Erro ao cadastrar role: " + e.getMessage());
        }
    }

    public String atualizarUsersRole(String role, ArrayList<String> usernames){
        try{
            funcionarioRepository.atualizarUsersRole(role, (String[]) usernames.toArray());
            return "Role foi atualizada com sucesso!";
        }catch (Exception e){
            throw new BusinessException("Erro ao atualizar role: " + e.getMessage());
        }
    }

    public ArrayList<String> buscarRoles(){
        try {
            List<String> roles = funcionarioRepository.buscarRoles();
            return new ArrayList<>(roles);
        }catch (Exception e){
            throw new BusinessException("Erro ao mostrar roles: " + e.getMessage());
        }
    }

    public String concederPrivilegioGrupo (String nameGrupo, String nomeDaTabela, String[] privilegios) {
        try {
            funcionarioRepository.concederPrivilegioGrupo(nameGrupo, nomeDaTabela, privilegios);
            return "privilegio ao grupo foi concedido com sucesso!";
        } catch (Exception e) {
            throw new BusinessException("Erro ao conceder privilegio a grupo: " + e.getMessage());
        }

    }

    public String concederPrivilegioUsuario (String nameUsuario, String nomeDaTabela, String[] privilegios) {
        try {
            funcionarioRepository.concederPrivilegioUsuario(nameUsuario, nomeDaTabela, privilegios);
            return "privilegio ao usuario foi cadastrada com sucesso!";
        } catch (Exception e) {
            throw new BusinessException("Erro ao conceder privilegio ao usuario: " + e.getMessage());
        }

    }



}