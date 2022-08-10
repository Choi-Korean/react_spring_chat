package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;


// JWT 토큰 저장 객체

@Getter
@Setter
public class JwtRequestDto {
	
	private String email;
    private String upw;

}
