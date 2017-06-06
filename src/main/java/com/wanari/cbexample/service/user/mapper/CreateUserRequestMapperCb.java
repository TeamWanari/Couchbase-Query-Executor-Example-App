package com.wanari.cbexample.service.user.mapper;

import com.wanari.cbexample.controller.user.dto.AddressDtoCb;
import com.wanari.cbexample.controller.user.dto.CreateUserRequestDtoCb;
import com.wanari.cbexample.domain.AddressCb;
import com.wanari.cbexample.domain.UserCb;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateUserRequestMapperCb {

    UserCb map(CreateUserRequestDtoCb dto);

    AddressCb map(AddressDtoCb address);

}
