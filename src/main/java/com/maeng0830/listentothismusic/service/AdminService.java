package com.maeng0830.listentothismusic.service;

import com.maeng0830.listentothismusic.domain.Member;
import com.maeng0830.listentothismusic.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final MemberRepository memberRepository;

    // 회원 목록 조회
    public Page<Member> viewMemberList(Pageable pageable) {

        return memberRepository.findAll(pageable);
    }
}
