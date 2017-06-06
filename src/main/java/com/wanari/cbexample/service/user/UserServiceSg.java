package com.wanari.cbexample.service.user;

import com.couchbase.client.java.document.json.JsonArray;
import com.wanari.cbexample.controller.shared.dto.ErrorDto;
import com.wanari.cbexample.controller.shared.dto.SuccessfulResponseDto;
import com.wanari.cbexample.controller.user.dto.CreateUserRequestDtoSg;
import com.wanari.cbexample.controller.user.dto.UserListResponseDto;
import com.wanari.cbexample.domain.AddressSg;
import com.wanari.cbexample.domain.UserCb;
import com.wanari.cbexample.domain.UserSg;
import com.wanari.cbexample.repository.UserRepositorySg;
import com.wanari.cbexample.service.shared.BaseService;
import com.wanari.cbexample.service.user.mapper.CreateUserRequestMapperSg;
import com.wanari.cbexample.service.user.mapper.UserListResponseMapperSg;
import com.wanari.cbexample.util.sync_gateway.SyncGatewayApi;
import com.wanari.cbexample.util.sync_gateway.response.DocumentCreationResponse;
import com.wanari.utils.couchbase.parameter.Parameters;
import io.vavr.control.Either;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceSg extends BaseService {

    private static final String USERNAME_REQUEST_PARAM = "username";
    private static final String AGE_FROM_REQUEST_PARAM = "ageFrom";
    private static final String AGE_TO_REQUEST_PARAM = "ageTo";
    private static final String ZIP_REQUEST_PARAM = "zip";

    private static final String USERNAME_FILTER_PARAM = "username";
    private static final String STATUS_FILTER_PARAM = "status";
    private static final String AGE_FILTER_PARAM = "age";
    private static final String ZIP_FILTER_PARAM = "address.zipCode";

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

    private Parameters filtersFromParams(Map<String, String> queryParams) {
        Parameters parameters = new Parameters();

        parameters.on(USERNAME_FILTER_PARAM)
            .contains(queryParams.get(USERNAME_REQUEST_PARAM))
            .onlyIfNonEmpty()
            .add();

        parameters.on(STATUS_FILTER_PARAM)
            .in(UserCb.Status.nonDeletedFilter())
            .andApply(JsonArray::fromJson);

        parameters.on(AGE_FILTER_PARAM)
            .from(queryParams.get(AGE_FROM_REQUEST_PARAM))
            .onlyIf(this::isPositive)
            .andApply(Long::parseLong);

        parameters.on(AGE_FILTER_PARAM)
            .to(queryParams.get(AGE_TO_REQUEST_PARAM))
            .onlyIf(this::isPositive)
            .andApply(Long::parseLong);

        parameters.on(ZIP_FILTER_PARAM)
            .is(queryParams.get(ZIP_REQUEST_PARAM))
            .onlyIf(this::isPositive)
            .andApply(Integer::parseInt);

        return parameters;
    }

    private Boolean isPositive(String maybeNumber) {
        if(maybeNumber == null) {
            return false;
        }

        return maybeNumber.matches("\\d+");
    }

    public Either<ErrorDto, SuccessfulResponseDto> addExampleUsers() {
        List<UserSg> users = new ArrayList<>();

        UserSg user1 = new UserSg();
        AddressSg address1 = new AddressSg();
        address1.setId(UUID.randomUUID().toString());
        address1.setZipCode(1119);
        address1.setStreet("Angel street");
        address1.setHouseNumber("25/b");
        user1.setAddress(address1);
        user1.setStatus(UserSg.Status.INACTIVE);
        user1.setAge(14);
        user1.setUsername("inactive user, 14 years old");
        user1.setPassword("cb-user-1-pw");
        users.add(user1);

        UserSg user2 = new UserSg();
        AddressSg address2 = new AddressSg();
        address2.setId(UUID.randomUUID().toString());
        address2.setZipCode(1089);
        address2.setStreet("Flower street");
        address2.setHouseNumber("38");
        user2.setAddress(address2);
        user2.setStatus(UserSg.Status.ACTIVE);
        user2.setAge(20);
        user2.setUsername("active user, 20 years old");
        user2.setPassword("cb-user-2-pw");
        users.add(user2);

        UserSg user3 = new UserSg();
        AddressSg address3 = new AddressSg();
        address3.setId(UUID.randomUUID().toString());
        address3.setZipCode(1134);
        address3.setStreet("Mountain street");
        address3.setHouseNumber("115/b");
        user3.setAddress(address3);
        user3.setStatus(UserSg.Status.DELETED);
        user3.setAge(40);
        user3.setUsername("deleted user 40 years old");
        user3.setPassword("cb-user-3-pw");
        users.add(user3);

        syncGatewayApi.createDocuments(users);
        return Either.right(emptyResponse);
    }
}
