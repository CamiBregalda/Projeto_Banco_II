package com.bd.mapper;

import com.bd.model.Produto;
import com.bd.model.request.ProdutoRequest;
import com.bd.model.response.ProdutoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    Produto postDtoToEntity(ProdutoRequest produtoRequest);

    ProdutoResponse entityToResponse(Produto produtos);
}
