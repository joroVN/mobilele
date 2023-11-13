package com.softuni.mobilele.model.mapper;

import com.softuni.mobilele.model.OfferEntity;
import com.softuni.mobilele.model.dto.AddOfferDTO;
import com.softuni.mobilele.model.dto.OfferDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "model", ignore = true)
    @Mapping(target = "id", ignore = true)
    OfferEntity offerDtoToOfferEntity(AddOfferDTO addOfferDTO);
    @Mapping(source = "model.name", target = "model")
    OfferDetailDTO offerEntityToOfferDetailDTO(OfferEntity offerEntity);


}
