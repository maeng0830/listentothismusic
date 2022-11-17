package com.maeng0830.listentothismusic.service;

import com.maeng0830.listentothismusic.code.commentCode.CommentStatusCode;
import com.maeng0830.listentothismusic.code.postCode.PostStatusCode;
import com.maeng0830.listentothismusic.domain.Comment;
import com.maeng0830.listentothismusic.domain.Member;
import com.maeng0830.listentothismusic.domain.Post;
import com.maeng0830.listentothismusic.exception.LimuException;
import com.maeng0830.listentothismusic.exception.errorcode.MemberErrorCode;
import com.maeng0830.listentothismusic.exception.errorcode.PostErrorCode;
import com.maeng0830.listentothismusic.repository.CommentRepository;
import com.maeng0830.listentothismusic.repository.MemberRepository;
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
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

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
        return postRepository.findByPostStatus(PostStatusCode.POST, pageable);
    }

    // 게시글 상세 조회
    // 조회수 증가 기능 포함
    public Post readPost(Long id) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new LimuException(PostErrorCode.NON_EXISTENT_POST));

        if (post.getPostStatus().equals(PostStatusCode.REPORT) || post.getPostStatus()
            .equals(PostStatusCode.DELETE)) {
            throw new LimuException(PostErrorCode.NON_VALIDATED_POST);
        }

        // 조회수 증가
        post.setHits(post.getHits() + 1);
        postRepository.save(post);

        return post;
    }

    // 게시글 수정
    public Post modPost(Long id, String userEmail, Post postInput) {

        Post post = postRepository.findById(id)
            .orElseThrow(() -> new LimuException(PostErrorCode.NON_EXISTENT_POST));

        if (post.getPostStatus().equals(PostStatusCode.DELETE)) {
            throw new LimuException(PostErrorCode.NON_VALIDATED_POST);
        }

        if (!post.getWriterEmail().equals(userEmail)) {
            throw new LimuException(PostErrorCode.NOT_AUTHORITY);
        }

        String[] linkArr = post.getMusicLink().split("/");
        String uniqueCode = linkArr[linkArr.length - 1];

        post.setTitle(postInput.getTitle());
        post.setMusicTitle(postInput.getMusicTitle());
        post.setArtist(postInput.getArtist());
        post.setMusicLink(postInput.getMusicLink());
        post.setGenre(postInput.getGenre());
        post.setMood(postInput.getMood());
        post.setWeather(postInput.getWeather());
        post.setContent(postInput.getContent());
        post.setYoutubeUniqueCode(uniqueCode);
        post.setYoutubeViewTag(YoutubeViewTag.createYoutubeTag(uniqueCode));

        postRepository.save(post);

        return post;
    }

    // 게시글 삭제
    public void deletePost(Long id, String userEmail) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new LimuException(PostErrorCode.NON_EXISTENT_POST));

        if (post.getPostStatus().equals(PostStatusCode.DELETE)) {
            throw new LimuException(PostErrorCode.NON_VALIDATED_POST);
        }

        if (!post.getWriterEmail().equals(userEmail)) {
            throw new LimuException(PostErrorCode.NOT_AUTHORITY);
        }

        post.setPostStatus(PostStatusCode.DELETE);

        postRepository.save(post);
    }

    // 게시글 신고
    public void reportPost(Long id, String reportReason) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new LimuException(PostErrorCode.NON_EXISTENT_POST));

        post.setPostStatus(PostStatusCode.REPORT);
        post.setReportReason(reportReason);
        post.setReportDtt(LocalDateTime.now());

        postRepository.save(post);
    }

    // 댓글 등록
    public void writeComment(Long id, Comment commentInput, String userEmail) {

        Member member = memberRepository.findByEmail(userEmail).orElseThrow(() -> new LimuException(
            MemberErrorCode.NON_EXISTENT_MEMBER));

        commentRepository.save(Comment.builder()
            .postId(id)
            .writerEmail(member.getEmail())
            .writerNickName(member.getNickName())
            .content(commentInput.getContent())
            .mark(commentInput.getMark())
            .regDtt(LocalDateTime.now())
            .commentStatus(CommentStatusCode.POST)
            .build());
    }

    //
}
