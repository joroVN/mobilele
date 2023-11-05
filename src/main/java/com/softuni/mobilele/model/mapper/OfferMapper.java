package com.softuni.mobilele.model.mapper;

import com.softuni.mobilele.model.OfferEntity;
import com.softuni.mobilele.model.dto.AddOfferDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "model", ignore = true)
    @Mapping(target = "id", ignore = true)
    OfferEntity addOfferDtoToOfferEntity(AddOfferDTO addOfferDTO);


}
