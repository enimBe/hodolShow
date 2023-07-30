package com.blog.service;

import com.blog.domain.Member;
import com.blog.domain.Session;
import com.blog.exception.AlreadyExistEmailException;
import com.blog.exception.InvalidRequest;
import com.blog.exception.InvalidSigninInformation;
import com.blog.repository.MemberRepository;
import com.blog.request.Login;
import com.blog.request.Signup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.AlreadyBoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long signin(Login login) {
        Member member = memberRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
                .orElseThrow(InvalidSigninInformation::new);
        Session session = member.addSession();

        return member.getId();
    }

    public void signup(Signup signup) throws AlreadyExistEmailException {
        Optional<Member> memberOptional = memberRepository.findByEmail(signup.getEmail());
        if (memberOptional.isPresent()) {
            throw new AlreadyExistEmailException();
        }

        var member = Member.builder()
                .name(signup.getName())
                .password(signup.getPassword())
                .email(signup.getEmail())
                .build();
        memberRepository.save(member);
    }
}
