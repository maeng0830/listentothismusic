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

@Controller
@RequiredArgsConstructor
public class AdminServiceController {

    private final AdminService adminService;

    @GetMapping("/admin/member-list")
    public String viewMemberList(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Member> members = adminService.viewMemberList(pageable);

        double start = Math.floor((members.getNumber()/10) * 10 + 1);
        double last = start + 9 < members.getTotalPages() ? start + 9 : members.getTotalPages();

        model.addAttribute("members", members);
        model.addAttribute("start", start);
        model.addAttribute("last", last);

        return "/admin/member-list";
    }
}
