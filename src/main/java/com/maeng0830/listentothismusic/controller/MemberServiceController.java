package com.maeng0830.listentothismusic.controller;

import com.maeng0830.listentothismusic.domain.Member;
import com.maeng0830.listentothismusic.exception.LimuException;
import com.maeng0830.listentothismusic.service.MemberService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberServiceController {

    private final MemberService memberService;

    @Autowired
    public MemberServiceController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String main() {
        return "/index";
    }

    @GetMapping("/member")
    public @ResponseBody String member() {
        return "member page";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin() {
        return "admin page";
    }

    // 로그인 페이지
    @GetMapping("/login-form")
    public String loginForm() {
        return "/login-form";
    }

    // 회원가입 페이지
    @GetMapping("/join-form")
    public String join() {
        return "/join-form";
    }

    // 회원 가입 결과(성공, 실패_중복아이디)
    @PostMapping("/join-result")
    public String joinResult(Model model, Member memberInput) {

        boolean result = true;

        try {
            memberService.join(memberInput);
        } catch (LimuException e) {
            result = false;
        }

        model.addAttribute("result", result);

        return "join-result";
    }

    // @Secured({"ROLE_MEMBER", "ROLE_ADMIN"}) 해당 요청 메소드에 멤버, 관리자 권한을 가진 사람만 접근 가능하게 간단하게 설정
    @GetMapping("/member/info")
    public @ResponseBody String memberInfo() {
        return "개인 정보";
    }

    // 가입 인증
    @GetMapping("/mail-auth")
    public String mailAuth(Model model, HttpServletRequest request) {
        String uuid = request.getParameter("authKey");

        boolean result = true;

        try {
            memberService.mailAuth(uuid);
        } catch (LimuException e) {
            result = false;
        }

        model.addAttribute("result", result);

        return "/mail-auth";
    }
}
