package com.maeng0830.listentothismusic.repository;

import com.maeng0830.listentothismusic.code.commentCode.CommentStatusCode;
import com.maeng0830.listentothismusic.domain.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostIdAndCommentStatusNot(Long id, CommentStatusCode commentStatusCode);

    Optional<List<Comment>> findByPostId(Long id);

    Page<Comment> findByCommentStatus(CommentStatusCode report, Pageable pageable);
}
