package com.blog.service;

import com.blog.domain.Member;
import com.blog.exception.AlreadyExistEmailException;
import com.blog.exception.InvalidSigninInformation;
import com.blog.repository.MemberRepository;
import com.blog.request.Login;
import com.blog.request.Signup;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long signin(Login login) {
        Member member = memberRepository.findByEmail(login.getEmail())
                .orElseThrow(InvalidSigninInformation::new);

        SCryptPasswordEncoder encoder = new SCryptPasswordEncoder(
                16,
                8,
                1,
                32,
                64);
        var matches=  encoder.matches(login.getPassword(), member.getPassword());
        if (!matches) {
            throw new InvalidSigninInformation();
        }

        return member.getId();
    }

    public void signup(Signup signup) throws AlreadyExistEmailException {
        Optional<Member> memberOptional = memberRepository.findByEmail(signup.getEmail());
        if (memberOptional.isPresent()) {
            throw new AlreadyExistEmailException();
        }

        SCryptPasswordEncoder encoder = new SCryptPasswordEncoder(
                16,
                8,
                1,
                32,
                64);
        String encryptedPassword = encoder.encode(signup.getPassword());

        var member = Member.builder()
                .name(signup.getName())
                .password(encryptedPassword)
                .email(signup.getEmail())
                .build();
        memberRepository.save(member);
    }
}
