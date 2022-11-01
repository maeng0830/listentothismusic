package com.maeng0830.listentothismusic.config.auth;

import com.maeng0830.listentothismusic.domain.Member;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// Security Session 안에 Authentication 객체, Authentication 객체 안에 UserDetails 객체
// UserDetails 객체에는 로그인한 member의 정보가 담겨져 있다.
// 최종적으로 Authentication 객체 안에 member의 정보를 담아서 Security Session에 넘겨줘야함.

public class PrincipalDetails implements UserDetails {

    private final Member member;

    public PrincipalDetails(Member member) {
        this.member = member;
    }

    // member의 권한을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getAuthority().toString();
            }
        });

        return collection;
    }

    // member의 패스워드 리턴
    @Override
    public String getPassword() {
        return member.getPassword();
    }

    // member의 아이디 리턴
    @Override
    public String getUsername() {
        return member.getEmail();
    }

    // member의 만료 여부를 리턴
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // member의 정지 여부 리턴
    // member의 status를 통해 로직 구성하면 될 듯
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // member의 패스워드 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // member의 활성화 여부
    // 인증 관련 로직 필요
    @Override
    public boolean isEnabled() {
        return true;
    }
}
