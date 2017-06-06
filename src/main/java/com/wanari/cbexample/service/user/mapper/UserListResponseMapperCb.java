package com.wanari.cbexample.service.user.mapper;

import com.wanari.cbexample.controller.user.dto.AddressDtoCb;
import com.wanari.cbexample.controller.user.dto.UserListResponseDto;
import com.wanari.cbexample.domain.AddressCb;
import com.wanari.cbexample.domain.UserCb;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserListResponseMapperCb {

    UserListResponseDto map(UserCb user);

    AddressDtoCb map(AddressCb address);
}
