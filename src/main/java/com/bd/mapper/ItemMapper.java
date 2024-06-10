package com.bd.mapper;

import com.bd.model.Item;
import com.bd.model.Venda;
import com.bd.model.request.ItemRequest;
import com.bd.model.request.VendaRequest;
import com.bd.model.response.ItemResponse;
import com.bd.model.response.VendaResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item postDtoToEntity(ItemRequest itemRequest);

    ItemResponse entityToResponse(Item item);
}
