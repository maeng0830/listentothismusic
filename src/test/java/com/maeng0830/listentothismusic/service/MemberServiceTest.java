package com.maeng0830.listentothismusic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.maeng0830.listentothismusic.code.memberCode.MemberStatusCode;
import com.maeng0830.listentothismusic.config.Constants;
import com.maeng0830.listentothismusic.config.mail.CreateMail;
import com.maeng0830.listentothismusic.domain.Member;
import com.maeng0830.listentothismusic.exception.LimuException;
import com.maeng0830.listentothismusic.exception.errorcode.MemberErrorCode;
import com.maeng0830.listentothismusic.repository.MemberRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Spy
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private CreateMail createMail;

    @DisplayName("회원 가입 성공")
    @Test
    void joinSuccess() {
        // given
        Member member = Member.builder()
            .email("test@naver.com")
            .password(bCryptPasswordEncoder.encode("123456"))
            .build();

        given(memberRepository.save(member))
            .willReturn(member);

        // when
        Member resultMember = memberService.join(member);

        // then
        Assertions.assertThat(member).isEqualTo(resultMember);
    }

    @DisplayName("회원 가입 실패-중복 아이디")
    @Test
    void joinFailed() {
        // given
        Member member = Member.builder()
            .email("test@naver.com")
            .password(bCryptPasswordEncoder.encode("123456"))
            .build();

        given(memberRepository.findByEmail(anyString())).willReturn(Optional.of(member));

        // when
        LimuException limuException = assertThrows(LimuException.class,
            () -> memberService.join(member));

        // then
        assertEquals(MemberErrorCode.DUPLICATE_MEMBER_EMAIL, limuException.getErrorCode());
    }

    @DisplayName("인증 메일 전송 성공")
    @Test
    void authEmailSend() {
        // given
        Member member = Member.builder()
            .email("test@naver.com")
            .authYn(false)
            .build();

        given(memberRepository.save(member)).willReturn(member);

        // when
        Member resultMember = memberService.authEmailSend(member);

        // then
        assertNotNull(resultMember.getAuthKey());
        assertNotNull(resultMember.getSendAuthEmailDtt());
    }

    @DisplayName("회원 가입 인증 성공")
    @Test
    void mailAuth() {
        // given
        String uuid = String.valueOf(UUID.randomUUID());

        given(memberRepository.findByAuthKey(uuid)).willReturn(
            Optional.of(Member.builder().authKey(uuid).authYn(false)
                .sendAuthEmailDtt(LocalDateTime.now())
                .build()));

        // when
        Member resultMember = memberService.mailAuth(uuid);

        // then
        assertEquals(resultMember.isAuthYn(), true);
        assertEquals(resultMember.getStatus(), MemberStatusCode.ING);
    }

    @DisplayName("회원 가입 인증 실패-잘못된 인증 링크")
    @Test
    void mailAuthFailed_incorrectLink() {
        // given
        String uuid = String.valueOf(UUID.randomUUID());

        given(memberRepository.findByAuthKey(uuid)).willReturn(Optional.empty());

        // when
        LimuException limuException = assertThrows(LimuException.class,
            () -> memberService.mailAuth(uuid));

        // then
        assertEquals(limuException.getErrorCode(), MemberErrorCode.INCORRECT_AUTH_LINK);
    }

    @DisplayName("회원 가입 인증 실패-이미 인증된 회원")
    @Test
    void mailAuthFailed_alreadyAuth() {
        // given
        String uuid = String.valueOf(UUID.randomUUID());

        given(memberRepository.findByAuthKey(uuid)).willReturn(
            Optional.of(Member.builder().authKey(uuid).authYn(true)
                .sendAuthEmailDtt(LocalDateTime.now())
                .build()));

        // when
        LimuException limuException = assertThrows(LimuException.class,
            () -> memberService.mailAuth(uuid));

        // then
        assertEquals(limuException.getErrorCode(), MemberErrorCode.ALREADY_AUTH);
    }

    @DisplayName("회원 가입 인증 실패-인증 메일 발송 후 5분 초과")
    @Test
    void mailAuthFailed_availableTime() {
        // given
        String uuid = String.valueOf(UUID.randomUUID());

        given(memberRepository.findByAuthKey(uuid)).willReturn(
            Optional.of(Member.builder().authKey(uuid).authYn(false)
                .sendAuthEmailDtt(LocalDateTime.now().minusMinutes(Constants.AVAILABLE_TIME))
                .build()));

        // when
        LimuException limuException = assertThrows(LimuException.class,
            () -> memberService.mailAuth(uuid));

        // then
        assertEquals(limuException.getErrorCode(), MemberErrorCode.EXCEED_AUTH_TIME);
    }

    @DisplayName("회원 상세 정보 조회 성공")
    @Test
    void memberInfoSuccess() {
        // given
        Member member = Member.builder()
            .email("test@naver.com")
            .password("123456")
            .build();

        given(memberRepository.findByEmail(member.getEmail()))
            .willReturn(Optional.of(member));

        // when
        Member findMember = memberService.memberInfo(member.getEmail());

        // then
        Assertions.assertThat(findMember).isEqualTo(member);

    }

    @DisplayName("회원 정보 수정 성공")
    @Test
    void memberInfoModSuccess() {
        // given
        Member newMember = Member.builder()
            .nickName("testNickname")
            .password("123456")
            .phone("01099999999")
            .build();

        newMember.setNickName("modNickname");
        newMember.setPhone("01088888888");

        given(memberRepository.findByEmail(newMember.getEmail())).willReturn(
            Optional.of(newMember));

        given(memberRepository.save(newMember)).willReturn(newMember);

        // when
        Member resultMember = memberService.memberInfoMod(newMember);

        // then
        assertEquals(resultMember, newMember);
    }

    @DisplayName("회원 정보 수정 실패-존재하지 않는 회원")
    @Test
    void memberInfoModFailed() {
        // given
        Member newMember = Member.builder()
            .nickName("testNickname")
            .password("123456")
            .phone("01099999999")
            .build();

        given(memberRepository.findByEmail(newMember.getEmail())).willReturn(Optional.empty());

        // when
        LimuException limuException = assertThrows(LimuException.class,
            () -> memberService.memberInfoMod(newMember));

        // then
        assertEquals(MemberErrorCode.NON_EXISTENT_MEMBER, limuException.getErrorCode());
    }
}