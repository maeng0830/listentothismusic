package com.maeng0830.listentothismusic.exception;

import com.maeng0830.listentothismusic.exception.errorcode.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private ErrorCode errorCode;
    private String errorMessage;

}
