package com.maeng0830.listentothismusic.controller;

import com.maeng0830.listentothismusic.code.memberCode.MemberAuthorityCode;
import com.maeng0830.listentothismusic.config.auth.PrincipalDetails;
import com.maeng0830.listentothismusic.domain.Comment;
import com.maeng0830.listentothismusic.domain.Member;
import com.maeng0830.listentothismusic.domain.Post;
import com.maeng0830.listentothismusic.exception.LimuException;
import com.maeng0830.listentothismusic.exception.errorcode.MemberErrorCode;
import com.maeng0830.listentothismusic.service.AdminService;
import javax.jws.WebParam.Mode;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class AdminServiceController {

    private final AdminService adminService;

    @GetMapping("/admin")
    public String admin() {

        return "/admin/main";
    }

    // 회원 목록 조회 api
    @GetMapping("/admin/member-list")
    public String viewMemberList(Model model,
        @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Member> memberList = adminService.viewMemberList(pageable);

        double start = Math.floor(
            (memberList.getPageable().getPageNumber() / memberList.getPageable().getPageSize())
                * memberList.getPageable().getPageSize() + 1);
        double last =
            start + memberList.getPageable().getPageSize() - 1 < memberList.getTotalPages()
                ? start + memberList.getPageable().getPageSize() - 1 : memberList.getTotalPages();
        int pageNumber = memberList.getPageable().getPageNumber();
        int pageSize = memberList.getPageable().getPageSize();
        int totalPages = memberList.getTotalPages();

        model.addAttribute("memberList", memberList);
        model.addAttribute("start", start);
        model.addAttribute("last", last);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", totalPages);

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

        double start = Math.floor(
            (postList.getPageable().getPageNumber() / postList.getPageable().getPageSize())
                * postList.getPageable().getPageSize() + 1);
        double last = start + postList.getPageable().getPageSize() - 1 < postList.getTotalPages()
            ? start + postList.getPageable().getPageSize() - 1 : postList.getTotalPages();
        int pageNumber = postList.getPageable().getPageNumber();
        int pageSize = postList.getPageable().getPageSize();
        int totalPages = postList.getTotalPages();

        model.addAttribute("postList", postList);
        model.addAttribute("start", start);
        model.addAttribute("last", last);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", totalPages);

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

    // 신고 게시글 목록 조회
    @GetMapping("/admin/reported-comment-list")
    public String viewReportedCommentList(Model model,
        @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Comment> commentList = adminService.viewReportedCommentList(pageable);

        double start = Math.floor(
            (commentList.getPageable().getPageNumber() / commentList.getPageable().getPageSize())
                * commentList.getPageable().getPageSize() + 1);
        double last =
            start + commentList.getPageable().getPageSize() - 1 < commentList.getTotalPages()
                ? start + commentList.getPageable().getPageSize() - 1 : commentList.getTotalPages();
        int pageNumber = commentList.getPageable().getPageNumber();
        int pageSize = commentList.getPageable().getPageSize();
        int totalPages = commentList.getTotalPages();

        model.addAttribute("commentList", commentList);
        model.addAttribute("start", start);
        model.addAttribute("last", last);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", totalPages);

        return "/admin/reported-comment-list";
    }

    // 신고 게시글 상세 정보 조회
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
