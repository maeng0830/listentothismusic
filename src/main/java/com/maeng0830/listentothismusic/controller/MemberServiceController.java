package com.maeng0830.listentothismusic.controller;

import com.maeng0830.listentothismusic.config.auth.PrincipalDetails;
import com.maeng0830.listentothismusic.domain.Member;
import com.maeng0830.listentothismusic.domain.Post;
import com.maeng0830.listentothismusic.dto.SearchDto;
import com.maeng0830.listentothismusic.dto.WithdrawDto;
import com.maeng0830.listentothismusic.exception.LimuException;
import com.maeng0830.listentothismusic.exception.errorcode.MemberErrorCode;
import com.maeng0830.listentothismusic.repository.MemberRepository;
import com.maeng0830.listentothismusic.service.MemberService;
import com.maeng0830.listentothismusic.service.PostService;
import com.maeng0830.listentothismusic.util.Paging;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberServiceController {

    private final MemberService memberService;
    private final PostService postService;
    private final MemberRepository memberRepository;

    // 로그인 상태, 인증 X 상태인 경우 인증 메일 요청 링크 활성화
    @GetMapping("/")
    public String main(Model model, @AuthenticationPrincipal PrincipalDetails userDetails,
        @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Member member = null;

        boolean loginYn = false;
        boolean authYn = false;

        // 로그인 여부 확인
        if (userDetails != null) {
            loginYn = true;
            member = memberRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new LimuException(MemberErrorCode.NON_EXISTENT_MEMBER));
            // 인증 여부 확인
            if (member.isAuthYn()) {
                authYn = true;
            }
        }

        model.addAttribute("loginYn", loginYn);
        model.addAttribute("authYn", authYn);

        Page<Post> postList = postService.viewPostList(pageable);

        Map<String, Double> pagingElement = Paging.createPagingElement(postList);

        model.addAttribute("postList", postList);
        model.addAttribute("start", pagingElement.get("start"));
        model.addAttribute("last", pagingElement.get("last"));
        model.addAttribute("pageNumber", pagingElement.get("pageNumber"));
        model.addAttribute("pageSize", pagingElement.get("pageSize"));
        model.addAttribute("totalPages", pagingElement.get("totalPages"));

        return "/index";
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
            Member member = memberRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException());
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

    // 회원 정보 조회(사용자)
    @GetMapping("/member/info")
    public String memberInfo(Model model, @AuthenticationPrincipal PrincipalDetails userDetails) {
        Member member = null;

        if (userDetails == null) {
            throw new LimuException(MemberErrorCode.REQUIRED_LOGIN);
        } else {
            member = memberService.memberInfo(userDetails.getUsername());
        }

        model.addAttribute("member", member);

        return "/member/info";
    }

    // 회원 정보 수정(사용자)
    @PostMapping("/member/info")
    public String memberInfoMod(Member memberInput) {

        memberService.memberInfoMod(memberInput);

        return "redirect:/member/info";
    }

    // 회원 탈퇴(Get)
    @GetMapping("/member/withdraw")
    public String memberWithdraw() {

        return "/member/withdraw";
    }

    // 회원 탈퇴(Post)
    @PostMapping("/member/withdraw")
    public String memberWithdrawSubmit(@AuthenticationPrincipal PrincipalDetails userDetails, WithdrawDto withdrawDto) {

        memberService.withdraw(userDetails.getUsername(), withdrawDto.getPassword(), withdrawDto.getRePassword());

        return "redirect:/logout";
    }

    // 검색 게시글 조회
    @GetMapping("/search")
    public String search(Model model, @AuthenticationPrincipal PrincipalDetails userDetails,
        SearchDto searchDto,
        @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Member member = null;

        boolean loginYn = false;
        boolean authYn = false;

        // 로그인 여부 확인
        if (userDetails != null) {
            loginYn = true;
            member = memberRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new LimuException(MemberErrorCode.NON_EXISTENT_MEMBER));
            // 인증 여부 확인
            if (member.isAuthYn()) {
                authYn = true;
            }
        }

        model.addAttribute("loginYn", loginYn);
        model.addAttribute("authYn", authYn);

        Page<Post> postList = postService.searchPostList(searchDto.getSearchValue(), pageable);

        Map<String, Double> pagingElement = Paging.createPagingElement(postList);

        model.addAttribute("postList", postList);
        model.addAttribute("start", pagingElement.get("start"));
        model.addAttribute("last", pagingElement.get("last"));
        model.addAttribute("pageNumber", pagingElement.get("pageNumber"));
        model.addAttribute("pageSize", pagingElement.get("pageSize"));
        model.addAttribute("totalPages", pagingElement.get("totalPages"));

        return "/search";
    }
}
