package com.bd.service;

import com.bd.exception.BusinessException;
import com.bd.infra.Conexao;
import com.bd.infra.Login;
import com.bd.mapper.FuncionarioMapper;
import com.bd.mapper.VendaMapper;
import com.bd.model.Funcionario;
import com.bd.model.Venda;
import com.bd.model.request.FuncionarioRequest;
import com.bd.model.request.UserLoginDTO;
import com.bd.model.request.VendaRegistrationRequest;
import com.bd.model.request.VendaRequest;
import com.bd.model.response.FuncionarioResponse;
import com.bd.model.response.VendaResponse;
import com.bd.repository.FuncionarioRepository;
import com.bd.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;
    private final VendaMapper vendaMapper;

    public VendaResponse cadastrarVenda(VendaRegistrationRequest vendaRegistrationRequestRequest) {
        criarLogin(vendaRegistrationRequestRequest.getUserLoginDTO());
        Venda venda = vendaMapper.postDtoToEntity(vendaRegistrationRequestRequest.getVendaRequest());
        return vendaMapper.entityToResponse(vendaRepository.cadastrarVenda(venda));
    }

    public boolean validarVenda(UserLoginDTO userDTO) {
        Login login = Login.getInstance();
        login.setUser(userDTO.getUsername());
        login.setSenha(userDTO.getPassword());

        return Conexao.authenticateUser();
    }

    public List<VendaResponse> buscarVendas(UserLoginDTO userDTO) {
        try {
            criarLogin(userDTO);
            List<Venda> vendas = vendaRepository.buscarVendas();

            List<VendaResponse> vendaResponses = new ArrayList<>();
            for (Venda venda : vendas) {
                vendaResponses.add(vendaMapper.entityToResponse(venda));
            }
            return vendaResponses;
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar vendas: " + e.getMessage());
        }
    }

    public VendaResponse buscarVendaPeloId(Long id, UserLoginDTO userDTO) {
        try {
            criarLogin(userDTO);

            Venda venda = vendaRepository.buscarVendaPeloId(id);
            return vendaMapper.entityToResponse(venda);
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar venda: " + e.getMessage());
        }
    }

    public VendaResponse atualizarVenda(Long id, VendaRegistrationRequest vendaRegistrationRequest) {
        criarLogin(vendaRegistrationRequest.getUserLoginDTO());
        Venda venda = vendaMapper.postDtoToEntity(vendaRegistrationRequest.getVendaRequest());
        vendaRepository.atualizarVenda(id, venda);

        return vendaMapper.entityToResponse(venda);
    }

    public boolean deletarVenda(Long id, UserLoginDTO userDTO) {
        return vendaRepository.deletarVenda(id);
    }
    private void criarLogin(UserLoginDTO userDTO) {
        Login login = Login.getInstance();
        login.setUser(userDTO.getUsername());
        login.setSenha(userDTO.getPassword());
    }

}
