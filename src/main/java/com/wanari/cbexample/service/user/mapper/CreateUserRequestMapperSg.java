package com.wanari.cbexample.service.user.mapper;

import com.wanari.cbexample.controller.user.dto.CreateUserRequestDtoSg;
import com.wanari.cbexample.domain.UserSg;
import com.wanari.cbexample.service.sync_gateway.DocumentType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateUserRequestMapperSg {
    @Mapping(target = "type", expression = "java(getDefaultType())")
    UserSg map(CreateUserRequestDtoSg dto);

    default String getDefaultType() {
        return DocumentType.USER;
    }
}
