package com.example.demo.model.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.entity.Account;
import com.example.demo.model.entity.Role;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountDetails implements UserDetails{
	
	private static String ROLE_PREFIX = "ROLE_";
	private Account account;
	
	// UserDetails 상속 필수 구현 함수들
	
	// Role을 authorities에 넣어주고, 추후 role에 따라 권한 제한 처리
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> role = account.getRoles();
        System.out.println(role);
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(ROLE_PREFIX + role.toString());
        Collection<GrantedAuthority> authorities = new ArrayList<>(); //List인 이유 : 여러개의 권한을 가질 수 있다
        authorities.add(authority);

        return authorities;
    }

    @Override
    public String getPassword() {
        return account.getUpw();
    }

    @Override
    public String getUsername() {
        return account.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
