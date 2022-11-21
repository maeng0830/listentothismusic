package com.maeng0830.listentothismusic.domain;

import com.maeng0830.listentothismusic.code.commentCode.CommentStatusCode;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId; // 게시글 번호

    private String writerEmail; // 댓글 작성자 이메일
    private String writerNickName; // 댓글 작성자 닉네임

    @Lob
    private String content; // 본문

    private Integer mark; // 평점

    private LocalDateTime regDtt; // 등록 일시

    private LocalDateTime reportDtt; // 신고 일시
    private String reportReason; // 신고 사유
    @Enumerated(EnumType.STRING)
    private CommentStatusCode commentStatus; // 댓글 상태
}
