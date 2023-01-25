package ch05.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.RepaintManager;

import ch05.client.GUI.MainFrame;
import ch05.client.GUI.HomeFrame;
import ch05.client.GUI.RoomFrame;

public class Client {
	// Client mcontext = this;
	Socket socket;
	public BufferedWriter bufferdWriter;
	public BufferedReader bufferedReader;
	boolean flag = true;
	String ip = "localHost";
	public int port;
	public Vector<String> user_list = new Vector<String>();
	public Vector<String> room_list = new Vector<String>();
	public Vector<String> roomUser_list = new Vector<String>();
	private StringTokenizer st;
	public MainFrame mContext;
	public String myRoom;
	// public RoomFrame room;

	public Client(MainFrame mContext) {
		this.mContext = mContext;
//		this.room = room;
	}

	public Client() {

	}

	public void connect() {
		System.out.println("client socket start");
		try {
			socket = new Socket(ip, port);
			connectSocketReaderWriter();
			new Thread(() -> {
				ReadThread readThread = new ReadThread();
				readThread.run();
			}).start();

		} catch (Exception e) {

		}

	}

	public void connectSocketReaderWriter() {
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bufferdWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			// bufferedReader.readLine();

		} catch (IOException e) {
			System.out.println("234");
			close();
			e.printStackTrace();
		}

	}

	private void inmessage(String str) {

		st = new StringTokenizer(str, "/");

		String protocol = st.nextToken();
		String message = st.nextToken();

		System.out.println("클라  프로토콜 :   " + protocol);
		System.out.println("클라  메세지  :    " + message);

		if (protocol.equals("NewUser")) {
			System.out.println("new test : " + message);
			user_list.add(message);
			System.out.println("mContext :" + mContext);
			System.out.println("mContext.list :" + mContext.homeFrame.roomlist);
			mContext.homeFrame.wholeUser_list.setListData(user_list);
		} else if (protocol.equals("OldUser")) {
			user_list.add(message);
			mContext.homeFrame.wholeUser_list.setListData(user_list);

		} else if (protocol.equals("UserOut")) {
			user_list.remove(message);

		} else if (protocol.equals("Note")) {
			st = new StringTokenizer(message, "@");
			String user = st.nextToken();
			String note = st.nextToken();
			JOptionPane.showMessageDialog(null, user + " : " + note, "Message", JOptionPane.CLOSED_OPTION);
//			String[] buttons = {"답장", "닫기"};
//			JOptionPane.showOptionDialog(null, user+" : "+note, "Message", 
//	                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, "답장");
//			System.out.println();
		} else if (protocol.equals("new_Room")) {
			room_list.add(message);
			mContext.homeFrame.roomlist.setListData(room_list);
		} else if (protocol.equals("OldRoom")) {
			room_list.add(message);
			mContext.homeFrame.roomlist.setListData(room_list);
			
			
			
			
			
		} else if (protocol.equals("ErrorOutRoom")) {
			room_list.remove(message);
		} else if (protocol.equals("CreateRoom")) {
			// 방만들기가 성공했을 경우
			myRoom = message;
		} else if (protocol.equals("enterRoom")) {
			myRoom = message;
			// JOptionPane.showMessageDialog(null, myRoom + " 에 입장완료", "알림",
			// JOptionPane.INFORMATION_MESSAGE);
		} else if (protocol.equals("enterRoomName")) {
			System.out.println(roomUser_list);
			roomUser_list.add(message);
			System.out.println(message);
			try {
				System.out.println("여기 동작>??");
				System.out.println(roomUser_list);

				//mContext.homeFrame.roomFrame.info_list.add
				
				mContext.homeFrame.roomFrame.info_list.setListData(roomUser_list);
				System.out.println(roomUser_list +"이거 넣었음");
			} catch (Exception e) {
				System.out.println("오류 났어");
			//	System.out.println(mContext.homeFrame.makeRoomFrame.roomFrame);
				mContext.homeFrame.makeRoomFrame.roomFrame.info_list.setListData(roomUser_list);
			}
			// mContext.homeFrame.roomFrame.info_list.setListData(roomUser_list);
//			JOptionPane.showMessageDialog(null,   myRoom + " 에 입장완료", "알림",
//					JOptionPane.INFORMATION_MESSAGE);
		}
		
		else if (protocol.equals("Chatting")) {
			String msg = st.nextToken();

			try {
				mContext.homeFrame.roomFrame.showText_txtA.append(message + " : " + msg + "\n");
			} catch (Exception e) {
				mContext.homeFrame.makeRoomFrame.roomFrame.showText_txtA.append(message + " : " + msg + "\n");
			}

		} else if(protocol.equals("OutRoom")) {
			
			mContext.homeFrame.roomFrame.showText_txtA.append("*** (( " + myRoom + "에서 퇴장 ))***\n");
			myRoom = null;
		}
	}

	private void close() {

		flag = false;
		try {
			bufferdWriter.close();
			bufferedReader.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("adsfgh");
			e.printStackTrace();
		}

	}

	public void sendmessage(String msg) {
		try {
			bufferdWriter.write(msg + "\n");
			bufferdWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 서버에서 넘어온 데이터를 받는 녀석
	private class ReadThread implements Runnable {

		@Override
		public void run() {
			while (flag) {
				try {
					String serverMsg = bufferedReader.readLine();
					System.out.println(serverMsg);
					inmessage(serverMsg);

				} catch (IOException e) {
					flag = false;
					close();
					JOptionPane.showMessageDialog(null, "서버가 종료됨!", "알림", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
					break;
				}

			}

		}

	}// end of inner class

}// end of class