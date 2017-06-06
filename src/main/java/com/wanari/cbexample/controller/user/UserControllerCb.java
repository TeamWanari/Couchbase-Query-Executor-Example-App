package com.wanari.cbexample.controller.user;

import com.wanari.cbexample.controller.shared.BaseController;
import com.wanari.cbexample.controller.user.dto.CreateUserRequestDtoCb;
import com.wanari.cbexample.service.user.UserServiceCb;
import com.wanari.cbexample.util.RequestUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users/cb")
public class UserControllerCb extends BaseController {

    private final UserServiceCb userServiceCb;
    private final RequestUtil requestUtil;

    public UserControllerCb(UserServiceCb userServiceCb, RequestUtil requestUtil) {
        this.userServiceCb = userServiceCb;
        this.requestUtil = requestUtil;
    }

    @RequestMapping(value = "",
        method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequestDtoCb user) {
        return userServiceCb.createUser(user).fold(
            this::errorToResponse,
            this::emptyResponse
        );
    }

    @RequestMapping(value = "",
        method = RequestMethod.GET)
    public ResponseEntity<?> listUsers(Pageable pageable, HttpServletRequest request) {
        return userServiceCb.findAll(requestUtil.getParameterMapWithOnlyFirstValues(request), pageable).fold(
            this::errorToResponse,
            this::toResponse
        );
    }

    @RequestMapping(value = "init",
        method = RequestMethod.POST)
    public ResponseEntity<?> addExampleUsers() {
        return userServiceCb.addExampleUsers().fold(
            this::errorToResponse,
            this::emptyResponse
        );
    }

}
