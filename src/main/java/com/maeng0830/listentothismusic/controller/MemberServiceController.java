package com.maeng0830.listentothismusic.controller;

import com.maeng0830.listentothismusic.config.auth.PrincipalDetails;
import com.maeng0830.listentothismusic.domain.Member;
import com.maeng0830.listentothismusic.exception.LimuException;
import com.maeng0830.listentothismusic.exception.errorcode.MemberErrorCode;
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
            Optional<Member> optionalMember = memberRepository.findByEmail(
                userDetails.getUsername());
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
    public String joinResult(Member memberInput) {

        memberService.join(memberInput);

        return "join-result";
    }

    // 인증 메일 전송 (이미 인증된 회원은 전송하지 않는다)
    @GetMapping("/mail-auth")
    public String mailAuth(Model model, @AuthenticationPrincipal PrincipalDetails userDetails) {
        if (userDetails == null) {
            throw new LimuException(MemberErrorCode.REQUIRED_LOGIN);
        } else {
            Member member = memberRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException());
            memberService.authEmailSend(member);
        }

        return "/mail-auth";
    }

    // 메일 인증 결과
    // 예외: 잘못된 인증 링크(authKey로 멤버 조회 불가), 이미 인증된 회원, 인증 메일 요청 후 5분 경과
    @GetMapping("/mail-auth-result")
    public String mailAuthResult(HttpServletRequest request) {
        String uuid = request.getParameter("authKey");

        memberService.mailAuth(uuid);

        return "/mail-auth-result";
    }
}
