package com.maeng0830.listentothismusic.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PostErrorCode implements ErrorCode {

    NON_EXISTENT_POST("존재하지 않는 게시글입니다."),
    NON_VALIDATED_POST("유효하지 않은 게시글입니다.");

    private final String description;
}
