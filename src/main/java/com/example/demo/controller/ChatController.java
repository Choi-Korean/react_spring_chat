package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dao.ChattingHistoryDAO;
import com.example.demo.model.dto.ChatDTO;
import com.example.demo.model.dto.ChattingMessage;
import com.example.demo.model.service.Receiver;
import com.example.demo.model.service.Sender;

@RestController
@CrossOrigin
public class ChatController {
	
	@Autowired
    private Sender sender;

    @Autowired
    private Receiver receiver;

    @Autowired
    private ChattingHistoryDAO chattingHistoryDAO;

    private static String BOOT_TOPIC = "kafka-chatting";

    //// "url/app/message"로 들어오는 메시지를 "/topic/public"을 구독하고있는 사람들에게 송신
    @MessageMapping("/message")//@MessageMapping works for WebSocket protocol communication. This defines the URL mapping.
    //@SendTo("/topic/public")//websocket subscribe topic& direct send
    public void sendMessage(ChattingMessage message) throws Exception {
        message.setTimeStamp(System.currentTimeMillis());
        chattingHistoryDAO.save(message);
        sender.send(BOOT_TOPIC, message);

    }

    @RequestMapping("/history")
    public List<ChattingMessage> getChattingHistory() throws Exception {
        System.out.println("history!");
        return chattingHistoryDAO.get();
    }

    @MessageMapping("/file")
    @SendTo("/topic/chatting")
    public ChattingMessage sendFile(ChattingMessage message) throws Exception {
        return new ChattingMessage(message.getFileName(), message.getRawData(), message.getUser());
    }
	
//	// WebSocketConfig에서 /app을 통해 일로 넘어오고, 그 중 아래 매핑된 것들은 sendMessage로 라우팅
//	// 그리고 매핑시킨 url로 메시지 전송
//	@MessageMapping("/chat.sendMessage")
//    @SendTo("/chat/public")
//    public ChatDTO sendMessage(@Payload ChatDTO msg) {
//        return msg;
//    }
//
//    @MessageMapping("/chat.addUser")
//    @SendTo("/chat/public")
//    public ChatDTO addUser(@Payload ChatDTO msg, SimpMessageHeaderAccessor headerAccessor){
//        headerAccessor.getSessionAttributes().put("user", msg.getUid());
//        return msg;
//    }
}
