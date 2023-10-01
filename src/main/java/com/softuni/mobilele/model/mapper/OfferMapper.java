package com.softuni.mobilele.model.mapper;

import com.softuni.mobilele.model.OfferEntity;
import com.softuni.mobilele.model.dto.AddOfferDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    OfferEntity addOfferDtoToOfferEntity(AddOfferDTO addOfferDTO);

}
