package com.maeng0830.listentothismusic.repository;

import com.maeng0830.listentothismusic.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByAuthKey(String uuid);
}
