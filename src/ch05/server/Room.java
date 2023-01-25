package ch05.server;

import java.util.Vector;

public class Room {

	String roomName;
	String roomUserName;
	Vector<UserSocket> room_user = new Vector<UserSocket>();
	UserSocket userSocket;

	public Room(String roomName, UserSocket u, String roomUserName) {
		this.roomName = roomName;
		this.room_user.add(u);
		// 와우 대박. ㅋㅋ
		this.userSocket = u;
		u.myCurrentRoomName = roomName;
		u.userName = roomUserName;
	}

	public void roomBroadcast(String str) { // 현재방의 모든 사람들에게 알린다.
		for (int i = 0; i < room_user.size(); i++) {
			UserSocket u = room_user.elementAt(i);
			u.sendMessage(str);
		}
	}

	public void addUser(UserSocket u) {
		room_user.add(u);
	}

	@Override
	public String toString() {
		return roomName;
	}

	public void removeRoom(UserSocket u) {
		room_user.remove(u);
		boolean empty = room_user.isEmpty();
		if (empty) {
			for (int i = 0; i < userSocket.server.room_list.size(); i++) {
				Room r = userSocket.server.room_list.elementAt(i);
				if (r.roomName.equals(roomName)) {
					userSocket.server.room_list.remove(this);
					userSocket.server.broadCast("EmptyRoom/" + roomName);
					userSocket.server.broadCast("UserData_Updata/ok");
					break;
				}
			}
		}
	}
}
