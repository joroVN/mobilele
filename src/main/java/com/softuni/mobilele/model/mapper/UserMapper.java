package com.softuni.mobilele.model.mapper;

import com.softuni.mobilele.model.UserEntity;
import com.softuni.mobilele.model.dto.UserRegisterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "active", constant = "true")
    UserEntity userDtoToUserEntity(UserRegisterDTO userRegisterDTO);
}
