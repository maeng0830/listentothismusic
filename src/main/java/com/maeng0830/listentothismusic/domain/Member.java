package com.maeng0830.listentothismusic.domain;

import com.maeng0830.listentothismusic.code.memberCode.MemberAuthorityCode;
import com.maeng0830.listentothismusic.code.memberCode.MemberStatusCode;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email; // id
    private String userName; // 이름
    private String password; // 비밀번호
    private String authKey; // 인증 키
    private LocalDateTime sendAuthEmailDtt; // 인증 메일 발송 시간
    private boolean authYn; // 가입 인증 여부
    private LocalDateTime regDtt; // 가입 날짜

    @Enumerated(EnumType.STRING)
    private MemberStatusCode status; // 상태(인증 요청, 사용, 정지, 탈퇴)

    private String phone; // 연락처
    private String nickName; // 닉네임

    @Enumerated(EnumType.STRING)
    private MemberAuthorityCode authority; // ROLE_MEMBER, ROLE_ADMIN
}
