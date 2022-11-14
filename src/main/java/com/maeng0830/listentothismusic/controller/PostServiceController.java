package com.maeng0830.listentothismusic.controller;

import com.maeng0830.listentothismusic.code.PostCode.TagCode;
import com.maeng0830.listentothismusic.config.auth.PrincipalDetails;
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

@RequiredArgsConstructor
@Controller
public class PostServiceController {

    private final PostService postService;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

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

    @PostMapping("/post/write")
    public String writePost(@AuthenticationPrincipal PrincipalDetails userDetails, Post post) {
        if (userDetails == null) {
            throw new LimuException(MemberErrorCode.REQUIRED_LOGIN);
        }

        memberRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new LimuException(MemberErrorCode.NON_EXISTENT_MEMBER));

        postService.writePost(userDetails.getUsername(), post);

        return "redirect:/";
    }
}
