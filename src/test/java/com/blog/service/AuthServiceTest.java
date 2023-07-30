package com.blog.service;

import com.blog.crypto.PasswordEncoder;
import com.blog.domain.Member;
import com.blog.exception.AlreadyExistEmailException;
import com.blog.exception.InvalidSigninInformation;
import com.blog.repository.MemberRepository;
import com.blog.request.Login;
import com.blog.request.Signup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class AuthServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        assertNotNull(member.getPassword());
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

    @Test
    @DisplayName("로그인 성공")
    void test3() {
        // given
        String encryptedPassword = passwordEncoder.encrypt("1234");

        Member member = Member.builder()
                .email("enimbe99@gmail.com")
                .password(encryptedPassword)
                .name("서예주")
                .build();
        memberRepository.save(member);

        Login login = Login.builder()
                .email("enimbe99@gmail.com")
                .password("1234")
                .build();

        // when
        Long memberId = authService.signin(login);

        // then
        assertNotNull(memberId);
    }

    @Test
    @DisplayName("비밀번호 틀림")
    void test4() {
        // given
        Signup signup = Signup.builder()
                .email("enimbe99@gmail.com")
                .password("1234")
                .name("서예주")
                .build();
        authService.signup(signup);

        Login login = Login.builder()
                .email("enimbe99@gmail.com")
                .password("5678")
                .build();

        // expected
        assertThrows(InvalidSigninInformation.class,
                () -> authService.signin(login));
    }

}