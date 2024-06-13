package com.bd.mapper;


import com.bd.model.Fornecedor;
import com.bd.model.request.FornecedorRequest;
import com.bd.model.response.FornecedorResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FornecedorMapper {
    Fornecedor postDtoToEntity(FornecedorRequest fornecedorRequest);

     FornecedorResponse entityToResponse(Fornecedor fornecedores);
}
