package ch05.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import ch05.client.Client;

// client 소켓과 대응하는 서버측 소켓  -- Socket
public class UserSocket extends Thread {
// 클라이언트와 연결 스트림을 처리하고 

	private boolean isRun = true;
	private boolean isOk = true;
	int no;
	// 계속 동작 ->

	BufferedReader bufferedReader;
	BufferedWriter bufferedWriter;
	String myCurrentRoomName;
//	String myCurrentUserName;
	// 의존성 컴포지션 관계
	// 클래스에 주소값이 생성될 때 연결됨
	Socket socket;
	Server server;

	String userName;

	public UserSocket(Socket socket, Server server) {
		this.server = server;
		this.socket = socket;
		initData();
	}

	private void initData() {
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			userName = bufferedReader.readLine();
			server.serverGUI.logHere_txtA.append(">>>>>" + userName + " login<<<<" + "\n");

			server.broadCast("NewUser/" + userName);

			for (int i = 0; i < server.user_list.size(); i++) {
				UserSocket uinf = server.user_list.elementAt(i);
				sendMessage("OldUser/" + uinf.userName);
			}
			for (int i = 0; i < server.room_list.size(); i++) {
				Room room = server.room_list.elementAt(i);
				sendMessage("OldRoom/" + room.roomName);
			}

			server.user_list.add(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// UserSocketThread 도 다른 작업을 해야한다면
		// while문만 둘 수 없음 따로 쓰레드로 만들어야 한다.
		new Thread(() -> {

			while (isRun) {
				// client 에서 보낸 메세지 계속 받아야한다.
				try {

					String msg = bufferedReader.readLine();
					server.serverGUI.logHere_txtA.append(userName + " : " + msg + "\n");
					inmessage(msg);

					// server.broadCast(msg);
				} catch (IOException e) {
					try {
						server.serverGUI.logHere_txtA.append(userName + " : " + "log out" + "\n");
						bufferedReader.close();
						bufferedWriter.close();
						socket.close();
						isRun = false;
						server.user_list.remove(this);
						server.room_list.remove(this);
						server.broadCast("UserOut/" + userName);
						server.broadCast("new_Room" + myCurrentRoomName);

						e.printStackTrace();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		}).start();

	}

	// server가 보내느 ㄴ메세지
	public void sendMessage(String msg) {
		try {
			bufferedWriter.write(msg + "\n");
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void inmessage(String str) {
		StringTokenizer st = new StringTokenizer(str, "/");

		String protocol = st.nextToken();
		String message = st.nextToken();

		System.out.println("서버 protocol : " + protocol);
		System.out.println("서버 message : " + message);

		if (protocol.equals("Note")) {
			System.out.println(message);
			st = new StringTokenizer(message, "@");
			String user = st.nextToken();
			String note = st.nextToken();
			// 백터에서 해당 사용자를 찾아서 쪽지를 전송한다.
			for (int i = 0; i < server.user_list.size(); i++) {
				UserSocket u = server.user_list.elementAt(i);
				// 쪽지는 반드시 찾은 사용자에게 메세지를 보내줘어야 한다.
				if (u.userName.equals(user)) {
					u.sendMessage("Note/" + userName + "@" + note);
				}
			}
		} else if (protocol.equals("NewRoomName")) {
			// 1.현재같은방이 존재하는지 확인한다.
			for (int i = 0; i < server.room_list.size(); i++) {
				Room room = server.room_list.elementAt(i);
				if (room.roomName.equals(message)) { // 만들고자하는방이름을 확인한다
					// sendMessage("CreateRoomFail/ok");
					isOk = false;
					break;
				} else {
					// sendMessage("CreateRoom/" + room.roomName);
					isOk = true;
				}
			} // end for
			if (isOk == true) {
				// 1.방을 생성한다.
				Room new_room = new Room(message, this, userName);
				// 2. 전체 방 벡터에 생성된 방을 저장한다.
				server.room_list.add(new_room);
				// 3.사용자들에게 방과 방이름을 생성되었다고 알려준다.
				// sendMessage("CreateRoom/" + message); // 자신에게 방 성공 메세지를 보낸다.
				server.broadCast("new_Room/" + message);
			}
		} else if (protocol.equals("enterRoom")) {
			st = new StringTokenizer(message, "@");
			String room = st.nextToken();
			String user = st.nextToken();
			for (int i = 0; i < server.room_list.size(); i++) {
				Room roomb = server.room_list.elementAt(i);
				
				if (roomb.roomName.equals(room)) {
					// 신규접속자를 알린다.
					roomb.roomBroadcast("Chatting/[[알림]]/" + userName + " 입장 ");
					roomb.addUser(this);
					
					server.broadCast("enterRoom/" + room);
					roomb.roomBroadcast("enterRoomName/" + user);

				}
			}
		} else if (protocol.equals("Chatting")) {
			String msg = st.nextToken();
			for (int i = 0; i < server.room_list.size(); i++) {
				Room r = server.room_list.elementAt(i);
				if (r.roomName.equals(message)) {
					r.roomBroadcast("Chatting/" + userName + "/" + msg);
				}
			}
		} else if (protocol.equals("userOut")) {
			for (int i = 0; i < server.room_list.size(); i++) {
				Room r = server.room_list.elementAt(i);
				if (r.roomName.equals(message)) {
					r.removeRoom(this);
					sendMessage("OutRoom/ok");
					break;
				}
			}

//		} else if (protocol.equals("NewRoomUser")) {
//			server.broadCast("enterRoomName/" + message);
		}

	}

}