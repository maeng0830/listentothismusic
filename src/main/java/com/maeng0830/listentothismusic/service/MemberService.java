package com.maeng0830.listentothismusic.service;

import com.maeng0830.listentothismusic.code.memberCode.MemberAuthorityCode;
import com.maeng0830.listentothismusic.code.memberCode.MemberStatusCode;
import com.maeng0830.listentothismusic.config.mail.Mail;
import com.maeng0830.listentothismusic.domain.Member;
import com.maeng0830.listentothismusic.repository.MemberRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Mail mail;

    // 회원 가입(db 저장, 가입 인증 메일 전송)
    public void join(Member member) {
        String uuid = UUID.randomUUID().toString();

        member.setRegDtt(LocalDateTime.now());
        member.setStatus(MemberStatusCode.REQ.toString());
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        member.setAuthority(MemberAuthorityCode.ROLE_MEMBER.toString());
        member.setAuthKey(uuid);
        member.setAuthYn(false);

        memberRepository.save(member);

        String mailUrl = member.getEmail();
        String subject = "LiMu(Listen To This Music) 가입을 축하합니다!";
        String text = "<h2>LiMu(Listen To This Music) 가입을 축하합니다!</h2>"
            + "<a target='_blank' href='http://localhost:8080/mail-auth?authKey=" + uuid + "'>가입 인증 완료하기</a>";

        mail.sendMail(mailUrl, subject, text);
    }

    // 회원 가입 인증
    public boolean mailAuth(String uuid) {
        Optional<Member> optionalMember = memberRepository.findByAuthKey(uuid);

        if (!optionalMember.isPresent()) {
            return false;
        } else {
            Member member = optionalMember.get();

            member.setAuthYn(true);
            member.setStatus(MemberStatusCode.ING.toString());
            memberRepository.save(member);

            return true;
        }
    }
}
