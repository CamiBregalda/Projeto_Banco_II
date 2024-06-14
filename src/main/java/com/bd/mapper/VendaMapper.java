package com.bd.mapper;

import com.bd.model.Usuario;
import com.bd.model.Venda;
import com.bd.model.request.UserRequest;
import com.bd.model.request.VendaRequest;
import com.bd.model.response.UserResponse;
import com.bd.model.response.VendaResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VendaMapper {

    Venda postDtoToEntity(VendaRequest vendaRequest);

    VendaResponse entityToResponse(Venda venda);
}
