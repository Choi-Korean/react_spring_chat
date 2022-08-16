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
    public String login(JwtRequestDto jwtRequestDto, HttpServletRequest req) {
		System.out.println(jwtRequestDto.getEmail());
        try {
        	// Header name print 해보기. Postman으로 보내면 referer 없지만, 브라우저로 보내면 있음
//        	req.getHeaderNames().asIterator().forEachRemaining(a -> System.out.println(a));
        	// Referer : 현재 요청 들어온 페이지의 이전 페이지에 대한 주소정보 등 포함
//        	System.out.println(req.getHeader("referer"));
        	req.getSession().setAttribute("prevPage", req.getHeader("referer"));
            return accountService.login(jwtRequestDto);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
	
	@PostMapping("/create")
	public AccountDTO create(AccountDTO accountDTO, Model model, MultipartFile file, HttpServletRequest req) {
		req.getHeaderNames().asIterator().forEachRemaining(a -> System.out.println(a));
		return accountService.createAccount(accountDTO);
	}
}
