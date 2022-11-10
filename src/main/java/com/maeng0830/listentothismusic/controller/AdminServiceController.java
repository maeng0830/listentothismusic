package com.maeng0830.listentothismusic.controller;

import com.maeng0830.listentothismusic.domain.Member;
import com.maeng0830.listentothismusic.service.AdminService;
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

    @GetMapping("/admin/member-list")
    public String viewMemberList(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Member> memberList = adminService.viewMemberList(pageable);

        double start = Math.floor((memberList.getPageable().getPageNumber() / memberList.getPageable().getPageSize())
            * memberList.getPageable().getPageSize() + 1);
        double last = start + memberList.getPageable().getPageSize() - 1 < memberList.getTotalPages()
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

    @GetMapping("/admin/member-detail")
    public String viewMemberDetail(Model model, @RequestParam String email) {

        Member member = adminService.viewMemberDetail(email);

        model.addAttribute("member", member);

        return "/admin/member-detail";
    }

    @PostMapping("/admin/member-detail")
    public String modMemberInfo(Member member) {
        adminService.modMemberInfo(member.getEmail(), member.getStatus().toString());

        return "redirect:/admin/member-detail?email=" + member.getEmail();
    }
}
