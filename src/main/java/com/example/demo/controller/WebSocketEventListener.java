package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.example.demo.model.dto.AccountDTO;
import com.example.demo.model.dto.ChatDTO;
import com.example.demo.model.entity.Account;

//@Component // 스프링 빈 등록
//public class WebSocketEventListener {
	
//	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);
//
//    @Autowired
//    private SimpMessageSendingOperations messagingTemplate;
//    
//    // controller에서 addUser로 clien 참여 메서드 적용했기에 여기서는 loggin만
//    @EventListener
//    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
//        logger.info("Received a new web socket connection");
//    }
//    
//    // 웹소켓세션에서 사용자 이름 추출 및 연결된 모든 클라이언트에 사용자 퇴장 broadcast
//    @EventListener
//    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//
//        String user = (String) headerAccessor.getSessionAttributes().get("username");
//        if(user != null) {
//            logger.info("User Disconnected : " + user);
//
//            ChatDTO msg = new ChatDTO();
//            Account u = new Account();
//            u.setUid(user);
//            msg.setContent("LEAVE");
//            msg.setUid(u);
//
//            messagingTemplate.convertAndSend("/topic/public", msg);
//        }
//    }
//}
