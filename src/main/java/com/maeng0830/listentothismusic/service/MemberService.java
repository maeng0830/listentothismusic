package com.maeng0830.listentothismusic.service;

import com.maeng0830.listentothismusic.code.memberCode.MemberAuthorityCode;
import com.maeng0830.listentothismusic.code.memberCode.MemberStatusCode;
import com.maeng0830.listentothismusic.config.Constants;
import com.maeng0830.listentothismusic.config.mail.CreateMail;
import com.maeng0830.listentothismusic.domain.Member;
import com.maeng0830.listentothismusic.exception.LimuException;
import com.maeng0830.listentothismusic.exception.errorcode.MemberErrorCode;
import com.maeng0830.listentothismusic.repository.MemberRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CreateMail createMail;

    // 회원 가입(db 저장, 가입 안내 메일 전송)
    public void join(Member member) {

        if (memberRepository.findByEmail(member.getEmail()).isPresent()) {
            throw new LimuException(MemberErrorCode.DUPLICATE_MEMBER_EMAIL);
        }

        member.setRegDtt(LocalDateTime.now());
        member.setStatus(MemberStatusCode.REQ);
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        member.setAuthority(MemberAuthorityCode.ROLE_MEMBER);
        member.setAuthYn(false);

        memberRepository.save(member);

        String mailAddress = member.getEmail();
        String subject = "LiMu(Listen To This Music) 가입을 축하합니다!";
        String text = "<h2>LiMu(Listen To This Music) 가입을 축하합니다!</h2>";

        createMail.sendMail(mailAddress, subject, text);
    }

    // 인증 메일 전송
    // 예외 처리: 이미 인증된 회원 인증 메일 전송 X
    public void authEmailSend(Member member) {

        if (member.isAuthYn()) {
            throw new LimuException(MemberErrorCode.ALREADY_AUTH);
        }

        String uuid = UUID.randomUUID().toString();

        String mailAddress = member.getEmail();
        String subject = "LiMu(Listen To This Music) 회원 가입 인증 메일";
        String text = "<h2>아래의 링크를 눌러 .LiMu 회원 가입을 완료해주세요!</h2>"
            + "<a target='_blank' href='http://localhost:8080/mail-auth-result?authKey=" + uuid
            + "'>가입 인증 완료하기</a>";

        createMail.sendMail(mailAddress, subject, text);

        member.setAuthKey(uuid);
        member.setSendAuthEmailDtt(LocalDateTime.now());

        memberRepository.save(member);
    }

    // 회원 가입 인증
    // 예외 처리: 잘못된 인증 링크, 이미 인증된 회원, 인증 메일 발송 후 5분 초과
    public void mailAuth(String uuid) {
        Member member = memberRepository.findByAuthKey(uuid)
            .orElseThrow(() -> new LimuException(MemberErrorCode.INCORRECT_AUTH_LINK));

        if (member.isAuthYn()) {
            throw new LimuException(MemberErrorCode.ALREADY_AUTH);
        } else if (member.getSendAuthEmailDtt().plusMinutes(Constants.AVAILABLE_TIME)
            .isBefore(LocalDateTime.now())) {
            throw new LimuException(MemberErrorCode.EXCEED_AUTH_TIME);
        } else {
            member.setAuthYn(true);
            member.setStatus(MemberStatusCode.ING);
            memberRepository.save(member);
        }
    }

    // 회원 정보 조회
    public Member memberInfo(String email) {

        return memberRepository.findByEmail(email)
            .orElseThrow(() -> new LimuException(MemberErrorCode.NON_EXISTENT_MEMBER));
    }
}

