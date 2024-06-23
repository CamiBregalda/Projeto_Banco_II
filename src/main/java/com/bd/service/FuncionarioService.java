package com.bd.service;

import com.bd.exception.BusinessException;
import com.bd.mapper.FuncionarioMapper;
import com.bd.model.Funcionario;
import com.bd.model.request.FuncionarioRequest;
import com.bd.model.response.FuncionarioResponse;
import com.bd.repository.BackupRepository;
import com.bd.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final BackupRepository backupRepository;
    private final FuncionarioMapper funcionarioMapper;

    public FuncionarioResponse cadastrarFuncionario(FuncionarioRequest funcionarioRequest) {
        Funcionario funcionario = funcionarioMapper.postDtoToEntity(funcionarioRequest);
        return funcionarioMapper.entityToResponse(funcionarioRepository.cadastrarFuncionario(funcionario));
    }

    public FuncionarioResponse logarFuncionario(String nome, String senha) {
        try {
            Funcionario funcionario = funcionarioRepository.logarFuncionario(nome, senha);
            return funcionarioMapper.entityToResponse(funcionario);
        } catch (Exception e) {
            throw new BusinessException("Erro ao logar funcionario: " + e.getMessage());
        }
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

    public List<FuncionarioResponse> buscarFuncionariosPeloNome(String nome) {
        try {
            return funcionarioRepository.buscarFuncionarios().stream()
                    .filter(funcionario -> funcionario.getFun_nome().contains(nome))
                    .map(funcionarioMapper::entityToResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar funcionarios: " + e.getMessage());
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
            return funcionarioRepository.buscarRoles();
        }catch (Exception e){
            throw new BusinessException("Erro ao mostrar roles: " + e.getMessage());
        }
    }

    public String concederPrivilegioGrupo(String nameGrupo, String nomeDaTabela, String[] privilegios) {
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

    public List<String> funcionarioPertenceRole(String rolename){
        try {
            return funcionarioRepository.funcionarioPertenceRole(rolename);
        }catch (Exception e){
            throw new BusinessException("Erro ao buscar funcionario por roles: " + e.getMessage());
        }
    }

    public boolean realizarBackup(String host, String port, String username, String database, String password) throws RuntimeException {
        PostgreSQLBackup backup = new PostgreSQLBackup();
        boolean response = backup.realizarBackup(host, port, username, database, password);
            
        if (response){
            backupRepository.realizarBackup();
        }
        
        return response;
    }

    public void programarBackup(LocalDateTime novoProximoBackup){
        try {
            backupRepository.programarBackup(Timestamp.valueOf(novoProximoBackup));
        } catch (Exception e) {
            throw new BusinessException("Erro ao programar backup: " + e.getMessage());
        }
    }
    
    public boolean checarBackup(){
        Timestamp proxBackup = backupRepository.checarBackup();
        return proxBackup.before(new Timestamp(System.currentTimeMillis()));
    }
}
