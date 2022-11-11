package com.maeng0830.listentothismusic.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
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
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 글 번호

    private String writer; // 작성자

    private String title; // 글 제목
    @Lob
    private String content; // 본문

    private String musicLink; // 음악 링크
    private String musicSubject; // 음악 제목
    private String artist; // 아티스트명
    private LocalDateTime postDtt; // 작성 일시

    private LocalDateTime reportDtt; // 신고 일시
    private boolean reportYn; // 신고 처리 여부
}
