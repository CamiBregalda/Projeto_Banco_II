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

    public List<VendaResponse> buscarVendas() {
        try {
            List<Venda> vendas = vendaRepository.buscarVendas();

            for (Venda venda : vendas) {
                System.out.println(venda);
            }

            List<VendaResponse> vendaResponses = new ArrayList<>();
            for (Venda venda : vendas) {
                vendaResponses.add(vendaMapper.entityToResponse(venda));
            }

            for (VendaResponse venda : vendaResponses) {
                System.out.println(venda);
            }

            System.out.println("teste");
            return vendaResponses;
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar vendas: " + e.getMessage());
        }
    }

    public VendaResponse buscarVendaPeloId(Long id) {
        try {
            Venda venda = vendaRepository.buscarVendaPeloId(id);
            return vendaMapper.entityToResponse(venda);
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar venda: " + e.getMessage());
        }
    }

    public VendaResponse atualizarVenda(Long id, VendaRequest vendaRequest) {
        Venda venda = vendaMapper.postDtoToEntity(vendaRequest);
        vendaRepository.atualizarVenda(id, venda);

        return vendaMapper.entityToResponse(venda);
    }

    public boolean deletarVenda(Long id) {
        return vendaRepository.deletarVenda(id);
    }
    private void criarLogin(UserLoginDTO userDTO) {
        Login login = Login.getInstance();
        login.setUser(userDTO.getUsername());
        login.setSenha(userDTO.getPassword());
    }

}
