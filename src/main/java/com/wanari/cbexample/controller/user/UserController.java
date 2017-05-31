package com.wanari.cbexample.controller.user;

import com.wanari.cbexample.controller.shared.BaseController;
import com.wanari.cbexample.controller.user.dto.CreateUserRequestDto;
import com.wanari.cbexample.service.user.UserService;
import com.wanari.cbexample.util.RequestUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;
    private final RequestUtil requestUtil;

    public UserController(UserService userService, RequestUtil requestUtil) {
        this.userService = userService;
        this.requestUtil = requestUtil;
    }

    @RequestMapping(value = "",
        method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequestDto user) {
        return userService.createUser(user).fold(
            this::errorToResponse,
            this::emptyResponse
        );
    }

    @RequestMapping(value = "",
        method = RequestMethod.GET)
    public ResponseEntity<?> listUsers(Pageable pageable, HttpServletRequest request) {
        return userService.findAll(requestUtil.getParameterMapWithOnlyFirstValues(request), pageable).fold(
            this::errorToResponse,
            this::toResponse
        );
    }

}
