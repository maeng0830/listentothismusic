package com.maeng0830.listentothismusic.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommentErrorCode implements ErrorCode {

    NON_EXISTENT_COMMENT("존재하지 않는 댓글입니다."),
    NON_VALIDATED_COMMENT("유효하지 않은 댓글입니다.."),
    NOT_AUTHORITY_COMMENT("해당 댓글에 대한 수정 및 삭제 권한이 없습니다.");

    private final String description;
}
