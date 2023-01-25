package ch05.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import ch05.client.Client;

public class Server {

	// BufferedWriter bufferedWriter;
	Server server = this;
	ServerSocket serverSocket;
	public static final String HOST_IP = "localHost";

	// vector 를 쓰는이유 멀티쓰레드 환경에서 동기화를 함 싱크로나이즈드
	ServerGUI serverGUI;
	public Vector<UserSocket> user_list = new Vector<UserSocket>();
	public Vector<Room> room_list = new Vector<Room>();
	public Vector<Room> room_Userlist = new Vector<Room>();

	public Server(ServerGUI serverGUI) {
		this.serverGUI = serverGUI;
		initData();
	}

	private void initData() {
		try {
			serverGUI.logHere_txtA.append(">>>>>" + serverGUI.HOST_PORT + " open<<<<" + "\n");
			serverSocket = new ServerSocket(serverGUI.HOST_PORT);

			new Thread(() -> {
				while (true) {
					try {
						Socket socket = serverSocket.accept();
						// 여기서 유저소켓스레드를 생성해야한다.
						UserSocket userSocket = new UserSocket(socket, server);
						userSocket.start();

						// 벡터에 연결된 유저정보 소켓을 담아서 관리하자 !!
//						user_list.add(userSocket);
//						serverGUI.logHere_txtA.append(">>>>>" + user_list.get(user_list.size() - 1) + " login<<<<"+"\n");

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void broadCast(String msg) {
		for (int i = 0; i < user_list.size(); i++) {
			UserSocket user = user_list.elementAt(i);
			user.sendMessage(msg);
		}
	}

}// end of class