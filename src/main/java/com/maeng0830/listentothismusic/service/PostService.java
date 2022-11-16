package com.maeng0830.listentothismusic.service;

import com.maeng0830.listentothismusic.code.PostCode.PostStatusCode;
import com.maeng0830.listentothismusic.domain.Member;
import com.maeng0830.listentothismusic.domain.Post;
import com.maeng0830.listentothismusic.exception.LimuException;
import com.maeng0830.listentothismusic.exception.errorcode.PostErrorCode;
import com.maeng0830.listentothismusic.repository.PostRepository;
import com.maeng0830.listentothismusic.util.YoutubeViewTag;
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
    public void writePost(Member member, Post post) {

        String[] linkArr = post.getMusicLink().split("/");
        String uniqueCode = linkArr[linkArr.length - 1];

        postRepository.save(Post.builder()
            .postDtt(LocalDateTime.now())
            .writerEmail(member.getEmail())
            .writerNickName(member.getNickName())
            .title(post.getTitle())
            .content(post.getContent())
            .musicTitle(post.getMusicTitle())
            .artist(post.getArtist())
            .musicLink(post.getMusicLink())
            .youtubeUniqueCode(uniqueCode)
            .youtubeViewTag(YoutubeViewTag.createYoutubeTag(uniqueCode))
            .genre(post.getGenre())
            .mood(post.getMood())
            .weather(post.getWeather())
            .postStatus(PostStatusCode.POST)
            .build());
    }

    // 게시글 목록 조회
    public Page<Post> viewPostList(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    // 게시글 상세 조회
    public Post readPost(Long id) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new LimuException(PostErrorCode.NON_EXISTENT_POST));

        if (post.getPostStatus().equals(PostStatusCode.REPORT) || post.getPostStatus()
            .equals(PostStatusCode.DELETE)) {
            throw new LimuException(PostErrorCode.NON_VALIDATED_POST);
        }

        return post;
    }
}
