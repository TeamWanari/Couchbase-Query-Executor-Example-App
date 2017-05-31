package com.wanari.cbexample.service.user.mapper;

import com.wanari.cbexample.controller.user.dto.UserListResponseDto;
import com.wanari.cbexample.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserListResponseMapper {

    UserListResponseDto map(User dto);
}
