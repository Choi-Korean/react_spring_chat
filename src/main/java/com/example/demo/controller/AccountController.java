package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.dto.AccountDTO;
import com.example.demo.model.dto.JwtRequestDto;
import com.example.demo.model.service.AccountService;

//@CrossOrigin(origins="http://localhost:3000") // 필요한가? proxy 설정헀는데 난
@RestController
@RequestMapping("/api/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping(value="/login", produces=MediaType.APPLICATION_JSON_VALUE)
    public String login(JwtRequestDto jwtRequestDto) {
        try {
            return accountService.login(jwtRequestDto);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
	
	@PostMapping("/create")
	public AccountDTO create(AccountDTO accountDTO, Model model, MultipartFile file, HttpServletRequest req) {
		System.out.println(accountDTO);
		return accountService.createAccount(accountDTO);
	}
}
