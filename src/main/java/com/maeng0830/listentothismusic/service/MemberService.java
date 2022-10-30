package com.maeng0830.listentothismusic.service;

import com.maeng0830.listentothismusic.code.memberCode.MemberAuthorityCode;
import com.maeng0830.listentothismusic.code.memberCode.MemberStatusCode;
import com.maeng0830.listentothismusic.domain.Member;
import com.maeng0830.listentothismusic.repository.MemberRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void join(Member member) {
        member.setRegDtt(LocalDateTime.now());
        member.setStatus(MemberStatusCode.REQ.toString());
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        member.setAuthority(MemberAuthorityCode.ROLE_MEMBER.toString());

        memberRepository.save(member);
    }
}
