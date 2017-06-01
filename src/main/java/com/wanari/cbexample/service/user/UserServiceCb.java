package com.wanari.cbexample.service.user;

import com.couchbase.client.java.document.json.JsonObject;
import com.wanari.cbexample.controller.shared.dto.ErrorDto;
import com.wanari.cbexample.controller.shared.dto.SuccessfulResponseDto;
import com.wanari.cbexample.controller.user.dto.CreateUserRequestDtoCb;
import com.wanari.cbexample.controller.user.dto.UserListResponseDto;
import com.wanari.cbexample.domain.UserCb;
import com.wanari.cbexample.repository.UserListRepositoryCb;
import com.wanari.cbexample.repository.UserRepositoryCb;
import com.wanari.cbexample.service.shared.BaseService;
import com.wanari.cbexample.service.user.mapper.CreateUserRequestMapperCb;
import com.wanari.cbexample.service.user.mapper.UserListResponseMapperCb;
import com.wanari.utils.couchbase.CouchbaseFilter;
import io.vavr.control.Either;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

import static com.wanari.utils.couchbase.CouchbaseQueryExecutor.CONTAINS_FILTER;

@Service
public class UserServiceCb extends BaseService {

    private static final String USERNAME_REQUEST_PARAM = "username";
    private static final String STATUS_REQUEST_PARAM = "status";

    private static final String USERNAME_CONTAINS_FILTER = "username" + CONTAINS_FILTER;
    private static final String STATUS_FILTER = "status";

    private final UserRepositoryCb userRepositoryCb;
    private final UserListRepositoryCb userListRepositoryCb;

    private final CreateUserRequestMapperCb createUserRequestMapperCb;
    private final UserListResponseMapperCb userListResponseMapperCb;

    public UserServiceCb(UserRepositoryCb userRepositoryCb,
                         UserListRepositoryCb userListRepositoryCb,
                         CreateUserRequestMapperCb createUserRequestMapperCb,
                         UserListResponseMapperCb userListResponseMapperCb) {
        this.userRepositoryCb = userRepositoryCb;
        this.userListRepositoryCb = userListRepositoryCb;
        this.createUserRequestMapperCb = createUserRequestMapperCb;
        this.userListResponseMapperCb = userListResponseMapperCb;
    }

    public Either<ErrorDto, SuccessfulResponseDto> createUser(CreateUserRequestDtoCb userDto) {
        UserCb user = createUserRequestMapperCb.map(userDto);
        user.setId(UUID.randomUUID().toString());
        userRepositoryCb.save(user);
        return Either.right(emptyResponse);
    }

    public Either<ErrorDto, Page<UserListResponseDto>> findAll(Map<String, String> params, Pageable pageable) {
        Page<UserCb> users = userListRepositoryCb.findAll(filtersFromParams(params), pageable);
        return Either.right(users.map(userListResponseMapperCb::map));
    }

    private JsonObject filtersFromParams(Map<String, String> params) {
        CouchbaseFilter filters = new CouchbaseFilter();

        filters.putIfNotEmpty(USERNAME_CONTAINS_FILTER, params.get(USERNAME_REQUEST_PARAM));
        filters.putIfNotEmpty(STATUS_FILTER, params.get(STATUS_REQUEST_PARAM));

        return filters.toJsonObject();
    }

}
