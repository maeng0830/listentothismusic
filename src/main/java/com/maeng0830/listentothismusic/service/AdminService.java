package com.maeng0830.listentothismusic.service;

import com.maeng0830.listentothismusic.code.memberCode.MemberStatusCode;
import com.maeng0830.listentothismusic.domain.Member;
import com.maeng0830.listentothismusic.exception.LimuException;
import com.maeng0830.listentothismusic.exception.errorcode.MemberErrorCode;
import com.maeng0830.listentothismusic.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
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

    // 회원 상세 정보 조회
    public Member viewMemberDetail(String email) {

        return memberRepository.findByEmail(email).orElseThrow(() -> new LimuException(
            MemberErrorCode.NON_EXISTENT_MEMBER));
    }

    // 회원 정보 수정(관리자)
    public void modMemberInfo(String email, String status) {
        Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new LimuException(MemberErrorCode.NON_EXISTENT_MEMBER));

        member.setStatus(MemberStatusCode.valueOf(status));

        memberRepository.save(member);
    }
}
