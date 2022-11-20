package com.maeng0830.listentothismusic.service;

import com.maeng0830.listentothismusic.code.commentCode.CommentStatusCode;
import com.maeng0830.listentothismusic.code.memberCode.MemberStatusCode;
import com.maeng0830.listentothismusic.code.postCode.PostStatusCode;
import com.maeng0830.listentothismusic.domain.Comment;
import com.maeng0830.listentothismusic.domain.Member;
import com.maeng0830.listentothismusic.domain.Post;
import com.maeng0830.listentothismusic.exception.LimuException;
import com.maeng0830.listentothismusic.exception.errorcode.CommentErrorCode;
import com.maeng0830.listentothismusic.exception.errorcode.MemberErrorCode;
import com.maeng0830.listentothismusic.exception.errorcode.PostErrorCode;
import com.maeng0830.listentothismusic.repository.CommentRepository;
import com.maeng0830.listentothismusic.repository.MemberRepository;
import com.maeng0830.listentothismusic.repository.PostRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    // 회원 목록 조회
    public Page<Member> viewMemberList(Pageable pageable) {

        return memberRepository.findAll(pageable);
    }

    // 회원 상세 정보 조회
    public Member viewMemberDetail(String email) {

        return memberRepository.findByEmail(email).orElseThrow(() -> new LimuException(
            MemberErrorCode.NON_EXISTENT_MEMBER));
    }

    // 회원 정보 수정(관리자)
    public void modMemberInfo(String email, String status) {
        Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new LimuException(MemberErrorCode.NON_EXISTENT_MEMBER));

        member.setStatus(MemberStatusCode.valueOf(status));

        memberRepository.save(member);
    }

    // 신고 게시글 목록 조회
    public Page<Post> viewReportedPostList(Pageable pageable) {
        return postRepository.findByPostStatus(PostStatusCode.REPORT, pageable);
    }

    // 신고 게시글 상세 조회
    public Post viewReportedPostDetail(Long id) {

        return postRepository.findById(id).orElseThrow(() -> new LimuException(PostErrorCode.NON_EXISTENT_POST));
    }

    // 신고 게시글 정보 수정
    public void modPostInfo(Long id, PostStatusCode status) {
        Post post = postRepository.findById(id).orElseThrow(() -> new LimuException(PostErrorCode.NON_EXISTENT_POST));

        post.setPostStatus(status);

        postRepository.save(post);
    }

    // 신고 댓글 목록 조회
    public Page<Comment> viewReportedCommentList(Pageable pageable) {
        return commentRepository.findByCommentStatus(CommentStatusCode.REPORT, pageable);
    }

    // 신고 댓글 상세 조회
    public Comment viewReportedCommentDetail(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new LimuException(CommentErrorCode.NON_EXISTENT_COMMENT));
    }

    // 신고 댓글 정보 수정
    public void modCommentInfo(Long id, CommentStatusCode commentStatus) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new LimuException(CommentErrorCode.NON_EXISTENT_COMMENT));

        comment.setCommentStatus(commentStatus);

        commentRepository.save(comment);
    }
}
