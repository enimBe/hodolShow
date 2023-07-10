package com.blog.api.controller;

import com.blog.api.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController // Controller + ResponseBody
public class PostController {

    // Http Method
    // GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD, TRACE, CONNECT
    // 글 등록
    // POST Method

    @PostMapping("/posts")
    public Map<String, String> post(@RequestBody @Valid PostCreate params){

        // 1. 매번 메서드마다 값을 검증해야한다.
        //      > 개발자가 까먹을 수 있다
        //      > 검증 부분에서 버그가 발생할 여지가 높다.
        //      > 지겹다. (간지가 안난다)
        // 2. 응답값에 HashMap -> 응답 클래스를 만드는게 좋다
        // 3. 여러개의 에러처리 힘듬
        // 4. 세 번이상의 반복작업은 피해야 한다. - 코드 && 개발에 관한 모든것 -> 자동화

//        if (result.hasErrors()) {
//            List<FieldError> fieldErrors = result.getFieldErrors();
//            FieldError firstFieldError = fieldErrors.get(0);
//            String fieldName = firstFieldError.getField(); // title
//            String errorMessage = firstFieldError.getDefaultMessage(); // error message
//
//            Map<String, String> error = new HashMap<>();
//            error.put(fieldName, errorMessage);
//            return error;
//        }

        return Map.of();
    }



}
