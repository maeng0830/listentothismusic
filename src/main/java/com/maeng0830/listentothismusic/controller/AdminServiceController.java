package com.maeng0830.listentothismusic.controller;

import com.maeng0830.listentothismusic.domain.Comment;
import com.maeng0830.listentothismusic.domain.Member;
import com.maeng0830.listentothismusic.domain.Post;
import com.maeng0830.listentothismusic.service.AdminService;
import com.maeng0830.listentothismusic.util.Paging;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AdminServiceController {

    private final AdminService adminService;

    @GetMapping("/admin")
    public String admin() {

        return "/admin/main";
    }

    // 회원 목록 조회
    @GetMapping("/admin/member-list")
    public String viewMemberList(Model model,
        @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Member> memberList = adminService.viewMemberList(pageable);

        Map<String, Double> pagingElement = Paging.createPagingElement(memberList);

        model.addAttribute("memberList", memberList);
        model.addAttribute("start", pagingElement.get("start"));
        model.addAttribute("last", pagingElement.get("last"));
        model.addAttribute("pageNumber", pagingElement.get("pageNumber"));
        model.addAttribute("pageSize", pagingElement.get("pageSize"));
        model.addAttribute("totalPages", pagingElement.get("totalPages"));

        return "/admin/member-list";
    }

    // 회원 상세 정보 조회
    @GetMapping("/admin/member-detail")
    public String viewMemberDetail(Model model, @RequestParam String email) {

        Member member = adminService.viewMemberDetail(email);

        model.addAttribute("member", member);

        return "/admin/member-detail";
    }

    // 회원 정보 수정(관리자)
    @PostMapping("/admin/member-detail")
    public String modMemberInfo(Member member) {
        adminService.modMemberInfo(member.getEmail(), member.getStatus().toString());

        return "redirect:/admin/member-detail?email=" + member.getEmail();
    }

    // 신고 게시글 목록 조회
    @GetMapping("/admin/reported-post-list")
    public String viewReportedPostList(Model model,
        @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Post> postList = adminService.viewReportedPostList(pageable);

        Map<String, Double> pagingElement = Paging.createPagingElement(postList);

        model.addAttribute("postList", postList);
        model.addAttribute("start", pagingElement.get("start"));
        model.addAttribute("last", pagingElement.get("last"));
        model.addAttribute("pageNumber", pagingElement.get("pageNumber"));
        model.addAttribute("pageSize", pagingElement.get("pageSize"));
        model.addAttribute("totalPages", pagingElement.get("totalPages"));

        return "/admin/reported-post-list";
    }

    // 신고 게시글 상세 정보 조회
    @GetMapping("/admin/reported-post-detail")
    public String viewReportedPostDetail(Model model, @RequestParam Long id) {
        Post post = adminService.viewReportedPostDetail(id);

        model.addAttribute("post", post);

        return "/admin/reported-post-detail";
    }

    // 신고 게시글 정보 수정(관리자)
    @PostMapping("/admin/reported-post-detail")
    public String modPostInfo(@RequestParam Long id, Post post) {
        adminService.modPostInfo(id, post.getPostStatus());

        return "redirect:/admin/reported-post-detail?id=" + id;
    }

    // 신고 댓글 목록 조회
    @GetMapping("/admin/reported-comment-list")
    public String viewReportedCommentList(Model model,
        @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Comment> commentList = adminService.viewReportedCommentList(pageable);

        Map<String, Double> pagingElement = Paging.createPagingElement(commentList);

        model.addAttribute("commentList", commentList);
        model.addAttribute("start", pagingElement.get("start"));
        model.addAttribute("last", pagingElement.get("last"));
        model.addAttribute("pageNumber", pagingElement.get("pageNumber"));
        model.addAttribute("pageSize", pagingElement.get("pageSize"));
        model.addAttribute("totalPages", pagingElement.get("totalPages"));

        return "/admin/reported-comment-list";
    }

    // 신고 댓글 상세 정보 조회
    @GetMapping("/admin/reported-comment-detail")
    public String viewReportedCommentDetail(Model model, @RequestParam Long id) {
        Comment comment = adminService.viewReportedCommentDetail(id);

        model.addAttribute("comment", comment);

        return "/admin/reported-comment-detail";
    }

    // 신고 댓글 정보 수정(관리자)
    @PostMapping("/admin/reported-comment-detail")
    public String modCommentInfo(@RequestParam Long id, Comment comment) {
        adminService.modCommentInfo(id, comment.getCommentStatus());

        return "redirect:/admin/reported-comment-detail?id=" + id;
    }
}
