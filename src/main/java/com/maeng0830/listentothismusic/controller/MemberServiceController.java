package com.maeng0830.listentothismusic.controller;

import com.maeng0830.listentothismusic.domain.Member;
import com.maeng0830.listentothismusic.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/join")
    public String join() {
        return "/join";
    }

    @PostMapping("/join-success")
    public String joinSuccess(Member memberInput) {

        memberService.join(memberInput);

        return "/join-success";
    }
}
