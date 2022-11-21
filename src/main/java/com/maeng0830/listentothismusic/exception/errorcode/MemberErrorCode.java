package com.maeng0830.listentothismusic.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MemberErrorCode implements ErrorCode {

    DUPLICATE_MEMBER_EMAIL("중복된 이메일 입니다."),
    ALREADY_AUTH("이미 인증된 회원입니다."),
    INCORRECT_AUTH_LINK("유요한 인증 주소가 아닙니다."),
    INCORRECT_MEMBER_EMAIL("유효한 이메일이 아닙니다."),
    EXCEED_AUTH_TIME("인증 유효 시간을 초과했습니다. 인증 메일을 재요청 해주세요."),
    REQUIRED_LOGIN("로그인을 해주세요."),
    NON_EXISTENT_MEMBER("존재하지 않는 회원입니다."),
    NON_AUTHORITY("권한이 없습니다."),
    INCORRECT_PASSWORD_AND_REPASSWORD("비밀번호와 확인 비밀번호가 정확하지 않습니다.");


    private final String description;
}
