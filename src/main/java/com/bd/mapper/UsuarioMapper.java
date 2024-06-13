package com.bd.mapper;

import com.bd.model.Usuario;
import com.bd.model.request.UserRequest;
import com.bd.model.response.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario postDtoToEntity(UserRequest userRequest);

    UserResponse entityToResponse(Usuario usuario);
}
