package com.maeng0830.listentothismusic.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
@Controller
public class GlobalExceptionHandler {

    @ExceptionHandler(LimuException.class)
    public ModelAndView limuExceptionHandler(Model model, LimuException e) {
        log.error("{} is occurred.", e.getErrorCode() + ": " + e.getErrorMessage());

        model.addAttribute("errorCode", e.getErrorCode());
        model.addAttribute("message", e.getErrorMessage());

        return new ModelAndView("/error/exception-result");
    }
}
