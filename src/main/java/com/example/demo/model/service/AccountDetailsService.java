package com.example.demo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.dao.AccountRepository;
import com.example.demo.model.dto.AccountDetails;
import com.example.demo.model.entity.Account;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccountDetailsService implements UserDetailsService {
	
	@Autowired
	private AccountRepository accountRepository;

	// 상속 메서드 명이 Username이지만, 실제로는 email로 진행할 것임
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Account account;
		
		try {
			account = accountRepository.findAccountByEmail(username);
		} catch (Exception e) {
			throw new UsernameNotFoundException("유효하지 않은 사용자입니다.");
		}
	    
	    return new AccountDetails(account);
	}

}
