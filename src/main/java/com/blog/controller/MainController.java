package com.blog.controller;

import com.blog.config.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String main() {
        return "🏠 메인 페이지 🏠";
    }

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        userPrincipal.getUserId();
        return "🧑‍🌾 사용자 페이지 🧑‍🌾";
    }

    @GetMapping("/admin")
    public String admin() {
        return "🧑🏻‍🔧 관리자 페이지 🧑🏻‍🔧";
    }


}
