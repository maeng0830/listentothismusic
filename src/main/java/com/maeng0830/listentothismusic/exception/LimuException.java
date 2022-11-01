package com.maeng0830.listentothismusic.exception;

import com.maeng0830.listentothismusic.exception.errorcode.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LimuException extends RuntimeException {
    private ErrorCode errorCode;
    private String errorMessage;

    public LimuException(ErrorCode errorCode) {
       this.errorCode = errorCode;
       this.errorMessage = errorCode.getDescription();
    }
}
