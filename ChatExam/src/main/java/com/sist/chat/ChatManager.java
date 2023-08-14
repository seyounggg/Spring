package com.sist.chat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat/chat-ws")
public class ChatManager {
	private static List<Session> users=new ArrayList<Session>();
	// NodeJS => React
	@OnOpen // 연결 요청시에 처리
	public void onOpen(Session session) {
		users.add(session);
		System.out.println("클라이언트 접속..."+session.getId());
	}
	
	@OnClose // 연결 종료시 처리
	public void onClose(Session session) {
		users.remove(session);
		System.out.println("클라이언트 퇴장..."+session.getId());
	}
	
	@OnMessage // chat
	public void onMessage(String message, Session session) throws Exception{
		System.out.println("수신 메세지..."+message);
		// Iterator 사용
		Iterator<Session> it=users.iterator();
		System.out.println("현재 인원:"+users.size());
		while(it.hasNext()) {
			it.next().getBasicRemote().sendText(message);
			System.out.println(session.getId()+"전송");
		}
		// for 사용
		/*
		for(Session s:users) {
			s.getBasicRemote().sendText(message);
			System.out.println(s.getId()+"전송");
		}*/
	}
}
