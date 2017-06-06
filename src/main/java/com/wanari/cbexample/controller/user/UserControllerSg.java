package com.wanari.cbexample.controller.user;

import com.wanari.cbexample.controller.shared.BaseController;
import com.wanari.cbexample.controller.user.dto.CreateUserRequestDtoSg;
import com.wanari.cbexample.service.user.UserServiceSg;
import com.wanari.cbexample.util.RequestUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users/sg")
public class UserControllerSg extends BaseController {

    private final UserServiceSg userServiceSg;
    private final RequestUtil requestUtil;

    public UserControllerSg(UserServiceSg userServiceSg, RequestUtil requestUtil) {
        this.userServiceSg = userServiceSg;
        this.requestUtil = requestUtil;
    }

    @RequestMapping(value = "",
        method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequestDtoSg user) {
        return userServiceSg.createUser(user).fold(
            this::errorToResponse,
            this::emptyResponse
        );
    }

    @RequestMapping(value = "",
        method = RequestMethod.GET)
    public ResponseEntity<?> listUsers(Pageable pageable, HttpServletRequest request) {
        return userServiceSg.findAll(requestUtil.getParameterMapWithOnlyFirstValues(request), pageable).fold(
            this::errorToResponse,
            this::toResponse
        );
    }

    @RequestMapping(value = "/init",
        method = RequestMethod.POST)
    public ResponseEntity<?> addExampleUsers() {
        return userServiceSg.addExampleUsers().fold(
            this::errorToResponse,
            this::emptyResponse
        );
    }

}
