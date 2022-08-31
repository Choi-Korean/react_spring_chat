import React, { useState, useEffect } from 'react';
import parse from 'html-react-parser'
import { Link } from 'react-router-dom';

import SocketTest from './SocketTest'

export default function Chat() {

    const [chat, setChat] = useState("");
    const [chatList, setChatList] = useState("");

    const handleSendButtonPressed = () => {
        SocketTest();
        setChatList(chatList +
                `<div >
                    <span><b>data.mid</b></span> [data.date]<br/>
                    <span>${chat}</span>
                </div>`
            );
        setChat("");
        
        // fetch("/api/account/logout") // , requestOptions)
        //     .then((response) => response)
        //     .then((data) => {
        //         window.localStorage.removeItem('user');
        //         window.location.reload();
        //     }
        // );
    }

    const handleChat = (e) => {
        setChat(e.target.value);
    };

    const renderChat = () => {
        console.log(chatList);
        return parse(chatList);
    };

    return (
        <div id='chatt'>
            <h1>WebSocket Chatting</h1>
            {/* <input type='text' id='mid' value='홍길동'> */}
            {/* <input type='button' value='로그인' id='btnLogin'> */}
            <br/>
            <div id='talk'>
                {renderChat()}
            </div>
            <div id='sendZone'>
                <textarea id='chat' value={chat} onChange={(e) => handleChat(e)}></textarea>
                <button type="submit" className="btn-outline-primary" onClick={(e) => handleSendButtonPressed(chat)}>전송</button>
            </div>
	    </div>

    )
}