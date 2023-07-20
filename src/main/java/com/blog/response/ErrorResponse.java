package com.blog.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * {
 *     "code": "400",
 *     "message": "잘못된 요청입니다.",
 *     "validation": {
 *         "title": "값을 입력해주세요."
 *     }
 * }
 */

@Getter
//@JsonInclude(value = JsonInclude.Include.NON_EMPTY) // null 값은 빼고 return
public class ErrorResponse {

    private final String code;
    private final String message;
    private final Map<String, String> validation;    // TODO Map 사용하지 않도록 수정해보기

    @Builder
    public ErrorResponse(String code, String message, Map<String, String> validation) {
        this.code = code;
        this.message = message;
        this.validation = validation != null ? validation : new HashMap<>();
    }

    public void addValidation(String fieldName, String errorMessage) {
        this.validation.put(fieldName, errorMessage);
    }
}
