package com.blog.api.response;

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
public class ErrorResponse {

    private final String code;
    private final String message;

    // TODO Map 사용하지 않도록 수정해보기
    private Map<String, String> validation = new HashMap<>();

    @Builder
    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public void addValidation(String fieldName, String errorMessage) {
        this.validation.put(fieldName, errorMessage);
    }

}
