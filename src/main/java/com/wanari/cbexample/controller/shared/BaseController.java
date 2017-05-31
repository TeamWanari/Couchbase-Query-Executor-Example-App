package com.wanari.cbexample.controller.shared;

import com.wanari.cbexample.controller.shared.dto.ErrorDto;
import org.springframework.http.ResponseEntity;

public class BaseController {

    protected <T> ResponseEntity<T> emptyResponse(T body) {
        return ResponseEntity
            .ok()
            .build();
    }

    protected <T> ResponseEntity<T> toResponse(T body) {
        return ResponseEntity
            .ok(body);
    }

    protected <T extends ErrorDto> ResponseEntity<T> errorToResponse(T error) {
        return ResponseEntity
            .status(error.getStatus())
            .body(error);
    }

}
