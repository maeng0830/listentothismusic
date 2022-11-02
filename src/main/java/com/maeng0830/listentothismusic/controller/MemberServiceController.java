package com.maeng0830.listentothismusic.controller;

import com.maeng0830.listentothismusic.config.auth.PrincipalDetails;
import com.maeng0830.listentothismusic.domain.Member;
import com.maeng0830.listentothismusic.exception.LimuException;
import com.maeng0830.listentothismusic.repository.MemberRepository;
import com.maeng0830.listentothismusic.service.MemberService;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MemberServiceController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    // 로그인 상태, 인증 X 상태인 경우 인증 메일 요청 링크 활성화
    @GetMapping("/")
    public String main(Model model, @AuthenticationPrincipal PrincipalDetails userDetails) {
        boolean result = true;

        if (userDetails != null) {
            Optional<Member> optionalMember = memberRepository.findByEmail(userDetails.getUsername());
            if (optionalMember.isPresent()) {
                if (!optionalMember.get().isAuthYn()) {
                    result = false;
                }
            }
        }

        model.addAttribute("result", result);

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

    // 인증 메일 전송 (이미 인증된 회원은 전송하지 않는다)
    @GetMapping("/mail-auth")
    public String mailAuth(Model model, @AuthenticationPrincipal PrincipalDetails userDetails) {
        Optional<Member> optionalMember = memberRepository.findByEmail(userDetails.getUsername());

        boolean result = true;

        if (optionalMember.isPresent()) {
            try {
                memberService.authEmailSend(optionalMember.get());
            } catch (LimuException e) {
                result = false;
            }
        }

        model.addAttribute("result", result);

        return "/mail-auth";
    }

    // 메일 인증 결과
    // 예외: 잘못된 인증 링크(authKey로 멤버 조회 불가), 이미 인증된 회원, 인증 메일 요청 후 5분 경과
    @GetMapping("/mail-auth-result")
    public String mailAuthResult(Model model, HttpServletRequest request) {
        String uuid = request.getParameter("authKey");

        boolean result = true;
        String failCause = "";

        try {
           memberService.mailAuth(uuid);
        } catch (LimuException e) {
            result = false;
            failCause = e.getErrorMessage();
        }

        model.addAttribute("result", result);
        model.addAttribute("failCause", failCause);

        return "/mail-auth-result";
    }

    // @Secured({"ROLE_MEMBER", "ROLE_ADMIN"}) 해당 요청 메소드에 멤버, 관리자 권한을 가진 사람만 접근 가능하게 간단하게 설정
    @GetMapping("/member/info")
    public @ResponseBody String memberInfo() {
        return "개인 정보";
    }

    @GetMapping("/mail-auth")
    public String mailAuth(Model model, HttpServletRequest request) {
        String uuid = request.getParameter("authKey");

        boolean result = memberService.mailAuth(uuid);

        model.addAttribute("result", result);

        return "/mail-auth";
    }
}
