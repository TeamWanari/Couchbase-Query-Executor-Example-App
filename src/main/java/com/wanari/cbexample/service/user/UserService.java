package com.wanari.cbexample.service.user;

import com.couchbase.client.java.document.json.JsonObject;
import com.wanari.cbexample.controller.shared.dto.ErrorDto;
import com.wanari.cbexample.controller.shared.dto.SuccessfulResponseDto;
import com.wanari.cbexample.controller.user.dto.CreateUserRequestDto;
import com.wanari.cbexample.controller.user.dto.UserListResponseDto;
import com.wanari.cbexample.domain.User;
import com.wanari.cbexample.repository.UserRepository;
import com.wanari.cbexample.service.shared.BaseService;
import com.wanari.cbexample.service.user.mapper.CreateUserRequestMapper;
import com.wanari.cbexample.service.user.mapper.UserListResponseMapper;
import com.wanari.cbexample.util.sync_gateway.SyncGatewayApi;
import com.wanari.cbexample.util.sync_gateway.response.DocumentCreationResponse;
import com.wanari.utils.couchbase.CouchbaseFilter;
import com.wanari.utils.couchbase.CouchbasePage;
import io.vavr.control.Either;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.wanari.utils.couchbase.CouchbaseQueryExecutor.CONTAINS_FILTER;

@Service
public class UserService extends BaseService {

    private static final String USERNAME_REQUEST_PARAM = "username";

    private static final String USERNAME_CONTAINS_FILTER = "username" + CONTAINS_FILTER;

    private final SyncGatewayApi syncGatewayApi;
    private final UserRepository userRepository;

    private final CreateUserRequestMapper createUserRequestMapper;
    private final UserListResponseMapper userListResponseMapper;

    public UserService(SyncGatewayApi syncGatewayApi,
                       UserRepository userRepository,
                       CreateUserRequestMapper createUserRequestMapper,
                       UserListResponseMapper userListResponseMapper) {
        this.syncGatewayApi = syncGatewayApi;
        this.userRepository = userRepository;
        this.createUserRequestMapper = createUserRequestMapper;
        this.userListResponseMapper = userListResponseMapper;
    }

    public Either<ErrorDto, SuccessfulResponseDto> createUser(CreateUserRequestDto user) {

        ResponseEntity<DocumentCreationResponse> response = syncGatewayApi.createDocument(createUserRequestMapper.map(user));

        if(response.getStatusCode().is2xxSuccessful()) {
            return Either.right(emptyResponse);
        } else {
            ErrorDto errorDto = new ErrorDto();
            errorDto.setStatus(response.getStatusCodeValue());
            errorDto.setDescription("User creation failed!");
            return Either.left(errorDto);
        }
    }

    public Either<ErrorDto, CouchbasePage<UserListResponseDto>> findAll(Map<String, String> params, Pageable pageable) {
        CouchbasePage<User> users = userRepository.findAll(filtersFromParams(params), pageable);
        return Either.right(users.map(userListResponseMapper::map));
    }

    private JsonObject filtersFromParams(Map<String, String> params) {
        CouchbaseFilter filters = new CouchbaseFilter();

        filters.putIfNotEmpty(USERNAME_CONTAINS_FILTER, params.get(USERNAME_REQUEST_PARAM));

        return filters.toJsonObject();
    }

}
