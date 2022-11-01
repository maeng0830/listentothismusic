package com.maeng0830.listentothismusic.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MemberErrorCode implements ErrorCode {

    DUPLICATE_MEMBER_EMAIL("중복된 이메일 입니다."),
    FAIL_AUTH_MEMBER_JOIN("회원 가입 인증에 실패했습니다.");

    private final String description;
}
