package com.maeng0830.listentothismusic.controller;

import com.maeng0830.listentothismusic.code.commentCode.CommentStatusCode;
import com.maeng0830.listentothismusic.code.postCode.PostStatusCode;
import com.maeng0830.listentothismusic.code.postCode.TagCode;
import com.maeng0830.listentothismusic.config.auth.PrincipalDetails;
import com.maeng0830.listentothismusic.domain.Comment;
import com.maeng0830.listentothismusic.domain.Member;
import com.maeng0830.listentothismusic.domain.Post;
import com.maeng0830.listentothismusic.exception.LimuException;
import com.maeng0830.listentothismusic.exception.errorcode.CommentErrorCode;
import com.maeng0830.listentothismusic.exception.errorcode.MemberErrorCode;
import com.maeng0830.listentothismusic.exception.errorcode.PostErrorCode;
import com.maeng0830.listentothismusic.repository.CommentRepository;
import com.maeng0830.listentothismusic.repository.MemberRepository;
import com.maeng0830.listentothismusic.repository.PostRepository;
import com.maeng0830.listentothismusic.service.PostService;
import java.util.List;
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
    private final CommentRepository commentRepository;

    // 게시글 등록 api(get)
    @GetMapping("/post/write")
    public String writePost(Model model, @AuthenticationPrincipal PrincipalDetails userDetails) {

        if (userDetails == null) {
            throw new LimuException(MemberErrorCode.REQUIRED_LOGIN);
        }

        memberRepository.findByEmail(userDetails.getUsername())
            .orElseThrow(() -> new LimuException(MemberErrorCode.NON_EXISTENT_MEMBER));

        model.addAttribute("genreTagList", TagCode.Genre.values());
        model.addAttribute("moodTagList", TagCode.Mood.values());
        model.addAttribute("weatherTagList", TagCode.Weather.values());

        return "/post/write";
    }

    // 게시글 등록 api(post)
    @PostMapping("/post/write")
    public String writePostSubmit(@AuthenticationPrincipal PrincipalDetails userDetails,
        Post post) {
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
    public String readPost(Model model, @RequestParam Long id,
        @AuthenticationPrincipal PrincipalDetails userDetails) {

        Post post = postService.readPost(id);

        boolean writerYn = false;
        String loginUser = "";

        if (userDetails != null && post.getWriterEmail().equals(userDetails.getUsername())) {
            loginUser = userDetails.getUsername();
            writerYn = true;
        }

        model.addAttribute("writerYn", writerYn);
        model.addAttribute("post", post);
        model.addAttribute("loginUser", loginUser);

        List<Comment> commentList = commentRepository.findByPostIdAndCommentStatusNot(id, CommentStatusCode.DELETE);

        model.addAttribute("commentList", commentList);

        return "/post/read";
    }

    // 게시글 수정 api(get)
    @GetMapping("/post/mod")
    public String modPost(Model model, @RequestParam Long id,
        @AuthenticationPrincipal PrincipalDetails userDetails) {
        if (userDetails == null) {
            throw new LimuException(MemberErrorCode.REQUIRED_LOGIN);
        }

        Post post = postRepository.findById(id)
            .orElseThrow(() -> new LimuException(PostErrorCode.NON_EXISTENT_POST));

        if (post.getPostStatus().equals(PostStatusCode.DELETE)) {
            throw new LimuException(PostErrorCode.NON_VALIDATED_POST);
        }

        if (!post.getWriterEmail().equals(userDetails.getUsername())) {
            throw new LimuException(PostErrorCode.NOT_AUTHORITY_POST);
        }

        model.addAttribute("post", post);
        model.addAttribute("genreTagList", TagCode.Genre.values());
        model.addAttribute("moodTagList", TagCode.Mood.values());
        model.addAttribute("weatherTagList", TagCode.Weather.values());

        return "/post/mod";
    }

    // 게시글 수정 api(post)
    @PostMapping("/post/mod")
    public String modPostSubmit(Model model, @RequestParam Long id,
        @AuthenticationPrincipal PrincipalDetails userDetails, Post postInput) {
        if (userDetails == null) {
            throw new LimuException(MemberErrorCode.REQUIRED_LOGIN);
        }

        Post post = postService.modPost(id, userDetails.getUsername(), postInput);

        model.addAttribute("post", post);

        return "redirect:/post/read?id=" + post.getId();
    }

    // 게시글 삭제(get)
    @GetMapping("/post/delete")
    public String deletePost(@RequestParam Long id,
        @AuthenticationPrincipal PrincipalDetails userDetails) {

        postService.deletePost(id, userDetails.getUsername());

        return "/post/delete";
    }

    // 게시글 신고(get)
    @GetMapping("/post/report")
    public String reportPost(Model model, @RequestParam Long id,
        @AuthenticationPrincipal PrincipalDetails userDetails) {
        if (userDetails == null) {
            throw new LimuException(MemberErrorCode.REQUIRED_LOGIN);
        }

        Post post = postRepository.findById(id)
            .orElseThrow(() -> new LimuException(PostErrorCode.NON_EXISTENT_POST));

        model.addAttribute("post", post);

        return "/post/report";
    }

    // 게시글 신고(post)
    @PostMapping("/post/report")
    public String reportPostSubmit(@RequestParam Long id, Post post,
        @AuthenticationPrincipal PrincipalDetails userDetails) {
        if (userDetails == null) {
            throw new LimuException(MemberErrorCode.REQUIRED_LOGIN);
        }

        postService.reportPost(id, post.getReportReason());

        return "redirect:/";
    }

    // 댓글 등록(post)
    @PostMapping("/post/comment/write")
    public String writeComment(@RequestParam Long id,
        @AuthenticationPrincipal PrincipalDetails userDetails, Comment commentInput) {
        if (userDetails == null) {
            throw new LimuException(MemberErrorCode.REQUIRED_LOGIN);
        }

        postService.writeComment(id, commentInput, userDetails.getUsername());

        postService.calculatePostMark(id);

        return "redirect:/post/read?id=" + id;
    }

    // 댓글 신고(get)
    @GetMapping("/post/comment/report")
    public String reportComment(Model model, @RequestParam Long id,
        @AuthenticationPrincipal PrincipalDetails userDetails) {
        if (userDetails == null) {
            throw new LimuException(MemberErrorCode.REQUIRED_LOGIN);
        }

        Comment comment = commentRepository.findById(id)
            .orElseThrow(() -> new LimuException(CommentErrorCode.NON_EXISTENT_COMMENT));

        model.addAttribute("comment", comment);

        return "/post/comment/report";
    }

    // 댓글 신고(post)
    @PostMapping("/post/comment/report")
    public String reportCommentSubmit(@RequestParam Long id, Comment commentInput,
        @AuthenticationPrincipal PrincipalDetails userDetails) {
        if (userDetails == null) {
            throw new LimuException(MemberErrorCode.REQUIRED_LOGIN);
        }

        Comment comment = postService.reportComment(id, commentInput.getReportReason());

        return "redirect:/post/read?id=" + comment.getPostId();
    }

    // 댓글 삭제(get)
    @GetMapping("/post/comment/delete")
    public String deleteComment(@RequestParam Long id,
        @AuthenticationPrincipal PrincipalDetails userDetails) {

        Comment comment = postService.deleteComment(id, userDetails.getUsername());

        return "redirect:/post/read?id=" + comment.getPostId();
    }

    // 댓글 수정(get)
    @GetMapping("/post/comment/mod")
    public String modeComment(Model model, @RequestParam Long id, @AuthenticationPrincipal PrincipalDetails userDetails) {
        if (userDetails == null) {
            throw new LimuException(MemberErrorCode.REQUIRED_LOGIN);
        }

        Comment comment = commentRepository.findById(id)
            .orElseThrow(() -> new LimuException(CommentErrorCode.NON_EXISTENT_COMMENT));

        model.addAttribute("comment", comment);

        return "/post/comment/mod";
    }

    // 댓글 수정(get)
    @PostMapping("/post/comment/mod")
    public String modeCommentSubmit(@RequestParam Long id, @AuthenticationPrincipal PrincipalDetails userDetails, Comment commentInput) {
        if (userDetails == null) {
            throw new LimuException(MemberErrorCode.REQUIRED_LOGIN);
        }

        Comment comment = postService.modComment(id, commentInput);

        return "redirect:/post/read?id=" + comment.getPostId();
    }
}
