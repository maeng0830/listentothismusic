package com.maeng0830.listentothismusic.service;

import com.maeng0830.listentothismusic.domain.Post;
import com.maeng0830.listentothismusic.repository.PostRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public void writePost(String email, Post post) {

        postRepository.save(Post.builder()
            .postDtt(LocalDateTime.now())
            .writer(email)
            .title(post.getTitle())
            .content(post.getContent())
            .build());
    }
}
