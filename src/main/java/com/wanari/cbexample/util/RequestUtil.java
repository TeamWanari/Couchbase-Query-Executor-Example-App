package com.wanari.cbexample.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RequestUtil {

    public Map<String, String> getParameterMapWithOnlyFirstValues(HttpServletRequest request) {
        return request.getParameterMap().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue()[0]));
    }

}
