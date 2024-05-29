package com.bd.mapper;

import com.bd.model.Usuario;
import com.bd.model.request.UserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario postDtoToEntity(UserRequest userRequest);
}
