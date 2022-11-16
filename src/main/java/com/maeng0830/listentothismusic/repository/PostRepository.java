package com.maeng0830.listentothismusic.repository;

import com.maeng0830.listentothismusic.code.PostCode.PostStatusCode;
import com.maeng0830.listentothismusic.domain.Member;
import com.maeng0830.listentothismusic.domain.Post;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByPostStatus(PostStatusCode postStatusCode, Pageable pageable);
}
