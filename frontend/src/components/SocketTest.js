import React from "react";
import { useState, useEffect } from "react";
import SockJsClient from "react-stomp";
import UsernameGenerator from "username-generator";
import Fetch from "json-fetch";
import { TalkBox } from "react-talk";
import randomstring from "randomstring";

export default function SocketTest() {
  //   randomUserId is   used    to    emulate    a   unique    user    id   for    this    demo   usage
  const randomUserName = UsernameGenerator.generateUsername("-");
  const randomUserId = randomstring.generate();
  const sendURL = "/message"; // ? 어따씀
  const [clientConnected, setClientConnected] = useState(false);
  const [messages, setMessages] = useState([]);

  const onMessageReceive = (msg, topic) => {
    //alert(JSON.stringify(msg) +    "@" + JSON.stringify(this.state.messages)+ "@" + JSON.string
    setMessages([messages, msg]);
    // this.setState((prevState) => ({
    //   messages: [...prevState.messages, msg],
    // }));
  };

  const sendMessage = (msg, selfMsg) => {
    try {
      var send_message = {
        user: selfMsg.author,
        message: selfMsg.message,
      };
      this.clientRef.sendMessage("/app/message", JSON.stringify(send_message));
      return true;
    } catch (e) {
      return false;
    }
  };

  const componentWillMount = () => {
    console.log("call history");
    Fetch("/history", {
      method: "GET",
    }).then((response) => {
      setMessages(response.body);
      //   this.setState({ messages: response.body });
    });
  };

  const wsSourceUrl = "http://localhost:808/chatting";
  return (
    <div>
      <TalkBox
        topic="/topic/public"
        currentUserId={randomUserId}
        currentUser={randomUserName}
        messages={messages}
        onSendMessage={this.sendMessage}
        connected={this.state.clientConnected}
      />
      <SockJsClient
        url={wsSourceUrl}
        topics={["/topic/public"]}
        onMessage={onMessageReceive()}
        ref={(client) => {
          this.clientRef = client;
        }}
        onConnect={() => {
          setClientConnected(true);
          //   this.setState({ clientConnected: true });
        }}
        onDisconnect={() => {
          setClientConnected(false);
          this.setState({ clientConnected: false });
        }}
        debug={false}
        style={[{ width: "100%", height: "100%" }]}
      />
    </div>
  );
}
// ** 클래스용 시작
//     constructor(props)   {
//         super(props);
//         //   randomUserId    is   used    to    emulate    a   unique    user    id   for    this    demo   usage
//         this.randomUserName = UsernameGenerator.generateUsername("-");
//         this.randomUserId = randomstring.generate();
//         this.sendURL = "/message";
//         this.state = {
//             clientConnected : false,
//             messages : [],
//         };
//     }

//     const onMessageReceive = (msg, topic)  => {
//         //alert(JSON.stringify(msg) +    "@" + JSON.stringify(this.state.messages)+ "@" + JSON.string
//         this.setState(prevState => ({
//             messages : [...prevState.messages,    msg]
//         }));
//     }

//     const sendMessage = (msg, selfMsg) => {
//         try{
//             var send_message = {
//                 "user" : selfMsg.author,
//                 "message" : selfMsg.message
//             };
//             this.clientRef.sendMessage("/app/message", JSON.stringify(send_message));
//             return true;
//         } catch(e) {
//             return false;
//         }
//     }

//     componentWillMount = () => {
//         console.log("call history");
//         Fetch("/history", {
//             method:   "GET"
//         }).then((response) => {
//             this.setState({ messages: response.body});
//         });
//     }

//     const wsSourceUrl = "http://localhost:8080/chatting";
//     return(
//         <div>
//             <TalkBox
//                 topic="/topic/public"
//                 currentUserId={this.randomUserId}
//                 currentUser={this.randomUserName} messages={this.state.messages}
//                 onSendMessage={this.sendMessage}  connected={this.state.clientConnected}/>
//             <SockJsClient
//                 url={ wsSourceUrl}
//                 topics={["/topic/public"]}
//                 onMessage={this.onMessageReceive}   ref={(client) => {this.clientRef=client}}
//                 onConnect={() => {this.setState({clientConnected:true})}}
//                 onDisconnect={() => {this.setState({clientConnected:fals})}}
//                 debug={false}
//                 style={[{width:'100%', height:'100%'}]}/>
//         </div>
//     );
// }

// ** 클래스용 끝

// 예전에 사용하려던 코드
// //client 객체 생성 및 서버주소 입력
// const sock = new SockJs("/ws");

// //stomp로 감싸기
// const stomp = StompJs.over(sock);

// //웹소켓 connect-subscribe 부분
// const stompConnect = () => {
//     try {
//         //웹소켓 연결시 stomp에서 자동으로 connect이 되었다는것을
//         //console에 보여주는데 그것을 감추기 위한 debug
//         stomp.debug = null;

//         stomp.connect(() => {    //stomp.connect(token, () => {
//         stomp.subscribe(
//             `서버주소`,
//             (data) => {
//             //데이터 파싱
//             const newMessage = JSON.parse(data.body);
//             }
//         );
//         });
//     } catch (err) {}
//     };

//웹소켓 disconnect-unsubscribe 부분
// 웹소켓을 disconnect을 따로 해주지 않으면 계속 연결되어 있어서 사용하지 않을때는 꼭 연결을 끊어주어야한다.
// const stompDisConnect = () => {
//     try {
//         stomp.debug = null;
//         stomp.disconnect(() => {
//         stomp.unsubscribe("sub-0");
//         }, token);
//     } catch (err) {}
//     };

// const SendMessage = () => {
//     stomp.debug = null;
//     const data = {
//         type: "TALK",
//         roomId: roomId,
//         sender: sender_nick,
//         message: message,
//         createdAt: now,
//     };
//     //예시 - 데이터 보낼때 json형식을 맞추어 보낸다.
//     //웹소켓 데이터 전송 부분
//     stomp.send("/pub/chat/message", token, JSON.stringify(data));
// };
