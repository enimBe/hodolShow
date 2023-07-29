package com.blog.service;

import com.blog.domain.Member;
import com.blog.domain.Session;
import com.blog.exception.InvalidSigninInformation;
import com.blog.repository.MemberRepository;
import com.blog.request.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    public String signin(Login login) {
        Member member = memberRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
                .orElseThrow(InvalidSigninInformation::new);
        Session session = member.addSession();

        return session.getAccessToken();
    }


}
