package com.maeng0830.listentothismusic.repository;

import com.maeng0830.listentothismusic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

}
