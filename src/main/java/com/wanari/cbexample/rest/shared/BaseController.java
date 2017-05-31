package com.wanari.cbexample.rest.shared;

import com.wanari.cbexample.rest.shared.dto.ErrorDto;
import org.springframework.http.ResponseEntity;

public class BaseController {

    protected <T> ResponseEntity<T> emptyResponse(T body) {
        return ResponseEntity
            .ok()
            .build();
    }

    protected <T extends ErrorDto> ResponseEntity<T> errorToResponse(T error) {
        return ResponseEntity
            .status(error.getStatus())
            .body(error);
    }
}
