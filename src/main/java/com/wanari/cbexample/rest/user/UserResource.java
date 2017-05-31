package com.wanari.cbexample.rest.user;

import com.wanari.cbexample.rest.shared.BaseController;
import com.wanari.cbexample.rest.user.dto.CreateUserRequestDto;
import com.wanari.cbexample.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class UserResource extends BaseController {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users",
        method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequestDto user) {
        return userService.createUser(user).fold(
            this::errorToResponse,
            this::emptyResponse
        );
    }
}
