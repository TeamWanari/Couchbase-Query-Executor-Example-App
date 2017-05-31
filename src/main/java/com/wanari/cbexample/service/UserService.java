package com.wanari.cbexample.service;

import com.wanari.cbexample.rest.shared.dto.ErrorDto;
import com.wanari.cbexample.rest.shared.dto.SuccessfulResponseDto;
import com.wanari.cbexample.rest.user.dto.CreateUserRequestDto;
import com.wanari.cbexample.service.shared.BaseService;
import com.wanari.cbexample.util.sync_gateway.SyncGatewayApi;
import com.wanari.cbexample.util.sync_gateway.response.DocumentCreationResponse;
import io.vavr.control.Either;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService {

    private final SyncGatewayApi syncGatewayApi;

    public UserService(SyncGatewayApi syncGatewayApi) {
        this.syncGatewayApi = syncGatewayApi;
    }

    public Either<ErrorDto, SuccessfulResponseDto> createUser(CreateUserRequestDto user) {
        ResponseEntity<DocumentCreationResponse> response = syncGatewayApi.createDocument(user);

        if(response.getStatusCode().is2xxSuccessful()) {
            return Either.right(emptyResponse);
        } else {
            ErrorDto errorDto = new ErrorDto();
            errorDto.setStatus(response.getStatusCodeValue());
            errorDto.setDescription("User creation failed!");
            return Either.left(errorDto);
        }
    }
}
