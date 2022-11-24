package com.maeng0830.listentothismusic.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.maeng0830.listentothismusic.repository.MemberRepository;
import com.maeng0830.listentothismusic.service.MemberService;
import com.maeng0830.listentothismusic.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class MemberServiceControllerTest {

    @InjectMocks
    private MemberServiceController memberServiceController;

    @Mock
    private MemberService memberService;

    @Mock
    private PostService postService;

    @Mock
    private MemberRepository memberRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(memberServiceController).build();
    }

    @DisplayName("사용자 목록 조회")
    @Test
    void memberInfo() {
        // given

        // when

        // then
    }


}