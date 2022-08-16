package com.example.demo.model.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.dao.AccountRepository;
import com.example.demo.model.dto.AccountDTO;
import com.example.demo.model.dto.AccountDetails;
import com.example.demo.model.dto.JwtRequestDto;
import com.example.demo.model.entity.Account;
import com.example.demo.model.entity.Role;

import lombok.AllArgsConstructor;

@Service
//@Transactional
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private AuthenticationManager authenticationManager;

    public String login(JwtRequestDto jwtRequestDto) throws Exception {
    	Authentication authentication = null;
    	try {
    		authentication = authenticationManager.authenticate(
    				new UsernamePasswordAuthenticationToken(jwtRequestDto.getEmail(), jwtRequestDto.getUpw()));
		} catch (Exception e) {
			System.out.println(e);
		}
    	
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();
        return accountDetails.getUsername();
    }
	
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
