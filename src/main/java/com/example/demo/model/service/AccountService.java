package com.example.demo.model.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.dao.AccountRepository;
import com.example.demo.model.dto.AccountDTO;
import com.example.demo.model.entity.Account;
import com.example.demo.model.entity.Role;

@Service
public class AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public AccountDTO createAccount(AccountDTO accountDTO) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		accountDTO.setUpw(passwordEncoder.encode(accountDTO.getUpw()));
//		Role role = new Role();
//		role.setRoleName("BASIC");
//		accountDTO.setRoles(Arrays.asList(role));
		accountDTO.setId(accountRepository.save(modelMapper.map(accountDTO, Account.class)).getId());
		return accountDTO; 
	}
}
