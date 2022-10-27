package com.maeng0830.listentothismusic.domain;

import com.maeng0830.listentothismusic.code.memberCode.MemberAuthorityCode;
import com.maeng0830.listentothismusic.code.memberCode.MemberStatusCode;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member implements MemberAuthorityCode, MemberStatusCode {
    @Id
    String email;

    String userName;
    String password;
    boolean authYn; // 가입 인증 여부
    LocalDateTime regDtt;
    String status; // 상태(인증 요청, 사용, 정지, 탈퇴)
    String phone;
    String nickName;
    String authority; // ROLE_MEMBER, ROLE_ADMIN
}
