package com.bd.mapper;

import com.bd.model.Funcionario;
import com.bd.model.Usuario;
import com.bd.model.request.FuncionarioRequest;
import com.bd.model.request.UserRequest;
import com.bd.model.response.FuncionarioResponse;
import com.bd.model.response.UserResponse;
import org.mapstruct.Mapper;


 @Mapper(componentModel = "spring")
 public interface FuncionarioMapper {
        Funcionario postDtoToEntity(FuncionarioRequest funcionarioRequest);

        FuncionarioResponse entityToResponse(Funcionario funcionario);

 }



