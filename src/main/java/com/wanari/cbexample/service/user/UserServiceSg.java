package com.wanari.cbexample.service.user;

import com.couchbase.client.java.document.json.JsonObject;
import com.wanari.cbexample.controller.shared.dto.ErrorDto;
import com.wanari.cbexample.controller.shared.dto.SuccessfulResponseDto;
import com.wanari.cbexample.controller.user.dto.CreateUserRequestDtoSg;
import com.wanari.cbexample.controller.user.dto.UserListResponseDto;
import com.wanari.cbexample.domain.UserSg;
import com.wanari.cbexample.repository.UserRepositorySg;
import com.wanari.cbexample.service.shared.BaseService;
import com.wanari.cbexample.service.user.mapper.CreateUserRequestMapperSg;
import com.wanari.cbexample.service.user.mapper.UserListResponseMapperSg;
import com.wanari.cbexample.util.sync_gateway.SyncGatewayApi;
import com.wanari.cbexample.util.sync_gateway.response.DocumentCreationResponse;
import com.wanari.utils.couchbase.CouchbaseFilter;
import io.vavr.control.Either;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.wanari.utils.couchbase.CouchbaseQueryExecutor.CONTAINS_FILTER;

@Service
public class UserServiceSg extends BaseService {

    private static final String USERNAME_REQUEST_PARAM = "username";
    private static final String STATUS_REQUEST_PARAM = "status";

    private static final String USERNAME_CONTAINS_FILTER = "username" + CONTAINS_FILTER;
    private static final String STATUS_FILTER = "status";

    private final SyncGatewayApi syncGatewayApi;
    private final UserRepositorySg userRepositorySg;

    private final CreateUserRequestMapperSg createUserRequestMapperSg;
    private final UserListResponseMapperSg userListResponseMapperSg;

    public UserServiceSg(SyncGatewayApi syncGatewayApi,
                         UserRepositorySg userRepositorySg,
                         CreateUserRequestMapperSg createUserRequestMapperSg,
                         UserListResponseMapperSg userListResponseMapperSg) {
        this.syncGatewayApi = syncGatewayApi;
        this.userRepositorySg = userRepositorySg;
        this.createUserRequestMapperSg = createUserRequestMapperSg;
        this.userListResponseMapperSg = userListResponseMapperSg;
    }

    public Either<ErrorDto, SuccessfulResponseDto> createUser(CreateUserRequestDtoSg user) {

        ResponseEntity<DocumentCreationResponse> response = syncGatewayApi.createDocument(createUserRequestMapperSg.map(user));

        if(response.getStatusCode().is2xxSuccessful()) {
            return Either.right(emptyResponse);
        } else {
            ErrorDto errorDto = new ErrorDto();
            errorDto.setStatus(response.getStatusCodeValue());
            errorDto.setDescription("User creation failed!");
            return Either.left(errorDto);
        }
    }

    public Either<ErrorDto, Page<UserListResponseDto>> findAll(Map<String, String> params, Pageable pageable) {
        Page<UserSg> users = userRepositorySg.findAll(filtersFromParams(params), pageable);
        return Either.right(users.map(userListResponseMapperSg::map));
    }

    private JsonObject filtersFromParams(Map<String, String> params) {
        CouchbaseFilter filters = new CouchbaseFilter();

        filters.putIfNotEmpty(USERNAME_CONTAINS_FILTER, params.get(USERNAME_REQUEST_PARAM));
        filters.putIfNotEmpty(STATUS_FILTER, params.get(STATUS_REQUEST_PARAM));

        return filters.toJsonObject();
    }

}
