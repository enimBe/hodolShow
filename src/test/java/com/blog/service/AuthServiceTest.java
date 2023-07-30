package com.blog.service;

import com.blog.domain.Member;
import com.blog.exception.AlreadyExistEmailException;
import com.blog.repository.MemberRepository;
import com.blog.request.Login;
import com.blog.request.Signup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.rmi.AlreadyBoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AuthService authService;

    @AfterEach
    void clean() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입 성공")
    void test1() {
        // given
        Signup signup = Signup.builder()
                .email("enimbe99@gmail.com")
                .password("1234")
                .name("서예주")
                .build();

        // when
        authService.signup(signup);

        //then
        assertEquals(1, memberRepository.count());

        Member member = memberRepository.findAll().iterator().next();
        assertEquals("enimbe99@gmail.com", member.getEmail());
        assertEquals("1234", member.getPassword());
        assertEquals("서예주", member.getName());
    }

    @Test
    @DisplayName("회원가입 시 중복된 이메일")
    void test2() {
        // given
        Member member = Member.builder()
                .email("enimbe99@gmail.com")
                .password("1234")
                .name("바보")
                .build();
        memberRepository.save(member);

        Signup signup = Signup.builder()
                .email("enimbe99@gmail.com")
                .password("1234")
                .name("서예주")
                .build();

        // expected
        assertThrows(AlreadyExistEmailException.class, () -> authService.signup(signup));
    }

}