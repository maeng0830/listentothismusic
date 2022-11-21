package com.maeng0830.listentothismusic.service;

import com.maeng0830.listentothismusic.config.mail.CreateMail;
import com.maeng0830.listentothismusic.domain.Member;
import com.maeng0830.listentothismusic.exception.LimuException;
import com.maeng0830.listentothismusic.exception.errorcode.MemberErrorCode;
import com.maeng0830.listentothismusic.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private CreateMail createMail;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Test
    void join_success() {
        Member newMember = Member.builder()
            .id(123L)
            .email("testMember@naver.com")
            .password("12345")
            .build();

        memberService.join(newMember);

        Member findMember = memberRepository.findById(newMember.getId()).orElseThrow(() -> new LimuException(
            MemberErrorCode.NON_EXISTENT_MEMBER));

        Assertions.assertThat(newMember.getEmail()).isEqualTo(findMember.getEmail());
    }

    @Test
    void authEmailSend() {
    }

    @Test
    void mailAuth() {
    }

    @Test
    void memberInfo() {
    }

    @Test
    void memberInfoMod() {
    }
}