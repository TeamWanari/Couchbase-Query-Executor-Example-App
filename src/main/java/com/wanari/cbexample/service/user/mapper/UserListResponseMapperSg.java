package com.wanari.cbexample.service.user.mapper;

import com.wanari.cbexample.controller.user.dto.UserListResponseDtoSg;
import com.wanari.cbexample.domain.UserSg;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserListResponseMapperSg {

    UserListResponseDtoSg map(UserSg dto);
}
