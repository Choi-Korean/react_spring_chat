package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker//@EnableWebSocketMessageBroker is used to enable our WebSocket server
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	// 웹소켓 서버 연결 엔드포인트
	//  SockJS는 웹 소켓을 지원하지 않는 브라우저에 폴백 옵션을 활성화에 사용
	// FallBack : 기능이 제대로 동작하지 않을 때, 대처하는 기능 or 동작
	// STOMP : Simple Text Oriented Messaging Protocol
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
    	registry.addEndpoint("/chatting").setAllowedOrigins("*").withSockJS(); // 웹소켓 연결주소
    	// : url//chatting
//        registry.addEndpoint("/ws").withSockJS(); // 웹소켓 연결주소
//        .setAllowedOrigins("*").withSockJS();
    }
    
    // A 클라이언트 -> B 클라이언트 메시지 라우팅에 필요한 메시지 브로커 구성
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
    	// /app 으로 시작하는 메시지가 message-handling methods 라우팅 된다 명시
        registry.setApplicationDestinationPrefixes("/app");
        // /topic 시작 메시지가 메시지 브로커로 라우팅 
        // 메시지 브로커는 특정 주제와 연결된 모든 클라이언트에게 메시지를 broadcast 
        // 브로드캐스팅은 송신 호스트가 전송한 데이터가 네트워크에 연결된 모든 호스트에 전송되는 방식을 의미
        registry.enableSimpleBroker("/topic");
    }
}
