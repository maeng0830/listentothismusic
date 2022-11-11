package com.maeng0830.listentothismusic.repository;

import com.maeng0830.listentothismusic.domain.Member;
import com.maeng0830.listentothismusic.domain.Post;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
