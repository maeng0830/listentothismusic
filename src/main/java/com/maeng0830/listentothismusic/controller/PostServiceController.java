package com.maeng0830.listentothismusic.controller;

import com.maeng0830.listentothismusic.code.PostCode.TagCode;
import com.maeng0830.listentothismusic.config.auth.PrincipalDetails;
import com.maeng0830.listentothismusic.domain.Member;
import com.maeng0830.listentothismusic.domain.Post;
import com.maeng0830.listentothismusic.exception.LimuException;
import com.maeng0830.listentothismusic.exception.errorcode.MemberErrorCode;
import com.maeng0830.listentothismusic.repository.MemberRepository;
import com.maeng0830.listentothismusic.repository.PostRepository;
import com.maeng0830.listentothismusic.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class PostServiceController {

    private final PostService postService;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    // 게시글 등록 api(get)
    @GetMapping("/post/write")
    public String writePost(Model model, @AuthenticationPrincipal PrincipalDetails userDetails) {

        if (userDetails == null) {
            throw new LimuException(MemberErrorCode.REQUIRED_LOGIN);
        }

        memberRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new LimuException(MemberErrorCode.NON_EXISTENT_MEMBER));

        model.addAttribute("genreTagList", TagCode.Genre.values());
        model.addAttribute("moodTagList", TagCode.Mood.values());
        model.addAttribute("weatherTagList", TagCode.Weather.values());

        return "/post/write";
    }

    // 게시글 등록 api(post)
    @PostMapping("/post/write")
    public String writePost(@AuthenticationPrincipal PrincipalDetails userDetails, Post post) {
        if (userDetails == null) {
            throw new LimuException(MemberErrorCode.REQUIRED_LOGIN);
        }

        Member member = memberRepository.findByEmail(userDetails.getUsername())
            .orElseThrow(() -> new LimuException(MemberErrorCode.NON_EXISTENT_MEMBER));

        postService.writePost(member, post);

        return "redirect:/";
    }

    // 게시글 상세 조회 api
    @GetMapping("/post/read")
    public String readPost(Model model, @RequestParam Long id, @AuthenticationPrincipal PrincipalDetails userDetails) {

        Post post = postService.readPost(id);

        boolean writerYn = false;

        if (post.getWriterEmail().equals(userDetails.getUsername())) {
            writerYn = true;
        }

        model.addAttribute("writerYn", writerYn);
        model.addAttribute("post", post);

        return "/post/read";
    }

    // 게시글 수정 api(get)

    // 게시글 수정 api(post)
}
