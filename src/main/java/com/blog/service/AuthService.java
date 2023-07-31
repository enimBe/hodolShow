package com.blog.service;

import com.blog.domain.Member;
import com.blog.exception.AlreadyExistEmailException;
import com.blog.repository.MemberRepository;
import com.blog.request.Signup;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(Signup signup) throws AlreadyExistEmailException {
        Optional<Member> memberOptional = memberRepository.findByEmail(signup.getEmail());
        if (memberOptional.isPresent()) {
            throw new AlreadyExistEmailException();
        }

        String encryptedPassword = passwordEncoder.encode(signup.getPassword());

        var member = Member.builder()
                .name(signup.getName())
                .password(encryptedPassword)
                .email(signup.getEmail())
                .build();
        memberRepository.save(member);
    }
}
