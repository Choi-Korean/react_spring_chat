package com.example.demo.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public ResponseEntity login(JwtRequestDto jwtRequestDto, HttpServletRequest req, HttpServletResponse res) {
		System.out.println(jwtRequestDto.getEmail());
        try {
        	// Header name print 해보기. Postman으로 보내면 referer 없지만, 브라우저로 보내면 있음
//        	req.getHeaderNames().asIterator().forEachRemaining(a -> System.out.println(a));
        	// Referer : 현재 요청 들어온 페이지의 이전 페이지에 대한 주소정보 등 포함
        	// 위 refer 의존 안좋을 거 같고, 작동 안해서 삭제함. django때처럼 reponse entity 이용해서 하자
//        	HttpHeaders headers = new HttpHeaders();
//        	headers.add("Location", "localhost:808");   
//        	headers.add("user", accountService.login(jwtRequestDto));
        	String username = accountService.login(jwtRequestDto);
        	if(username != null){
        		jwtRequestDto.setUpw(null);
        		return new ResponseEntity(jwtRequestDto, HttpStatus.OK);
        	}else {
        		return new ResponseEntity(HttpStatus.NOT_FOUND);
        	}
        	
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
	
	@PostMapping("/create")
	public AccountDTO create(AccountDTO accountDTO, Model model, MultipartFile file, HttpServletRequest req) {
		req.getHeaderNames().asIterator().forEachRemaining(a -> System.out.println(a));
		return accountService.createAccount(accountDTO);
	}
	
	
	// 로그인여부 확인
	@GetMapping("/islogined")
    @ResponseBody
    public ResponseEntity isLogined(Principal principal) {
		System.out.println(principal);
		try {
			JwtRequestDto user = new JwtRequestDto();
			user.setEmail(principal.getName());
			return new ResponseEntity(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
    }
	
	@GetMapping("/logout")
	public void logout(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(req, res, auth);
		}
	}

	
}
