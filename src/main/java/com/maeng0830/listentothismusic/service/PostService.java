package com.maeng0830.listentothismusic.service;

import com.maeng0830.listentothismusic.code.PostCode.PostStatusCode;
import com.maeng0830.listentothismusic.domain.Post;
import com.maeng0830.listentothismusic.repository.PostRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    // 게시글 등록
    public void writePost(String email, Post post) {

        postRepository.save(Post.builder()
            .postDtt(LocalDateTime.now())
            .writer(email)
            .title(post.getTitle())
            .content(post.getContent())
            .musicTitle(post.getMusicTitle())
            .artist(post.getArtist())
            .musicLink(post.getMusicLink())
            .genre(post.getGenre())
            .mood(post.getMood())
            .weather(post.getWeather())
            .postStatus(PostStatusCode.POST)
            .build());
    }

    // 게시글 조회
    public Page<Post> viewPostList(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}
