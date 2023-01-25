package ch05.client.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import ch05.client.Client;

public class HomeFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	Client client;

	public JList<String> roomlist;
	public JList<String> wholeUser_list;
	JButton enterRoom_btn;
	JButton makeRoom_btn;
	public MainFrame mContext;
	JButton msg_btn;
	public RoomFrame roomFrame;
	public MakeRoomFrame makeRoomFrame;
	

	DefaultListModel<String> list = new DefaultListModel<>();

	public HomeFrame(MainFrame mContext) {
		this.mContext = mContext;
		System.out.println("111111111");
		setTitle("Talk On");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 533);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel infoPane = new JPanel();
		infoPane.setBounds(471, 90, 160, 219);
		contentPane.add(infoPane);
		infoPane.setLayout(null);

		JLabel infoRoom_lbl = new JLabel("대화방 정보");
		infoRoom_lbl.setFont(new Font("휴먼매직체", Font.BOLD, 15));
		infoRoom_lbl.setBounds(12, 10, 101, 27);
		infoPane.add(infoRoom_lbl);

		JLabel infoMaster_lbl = new JLabel("방장       :");
		infoMaster_lbl.setBounds(12, 48, 57, 15);
		infoPane.add(infoMaster_lbl);

		JLabel infoTime_lbl = new JLabel("개설시간 :");
		infoTime_lbl.setBounds(12, 73, 57, 15);
		infoPane.add(infoTime_lbl);

		JLabel infoUser_lbl = new JLabel("참여자    :");
		infoUser_lbl.setBounds(12, 98, 57, 15);
		infoPane.add(infoUser_lbl);

		makeRoom_btn = new JButton("대화방 만들기");
		makeRoom_btn.setForeground(new Color(255, 255, 255));
		makeRoom_btn.setBackground(new Color(100, 149, 237));
		makeRoom_btn.setBounds(471, 319, 160, 39);
		contentPane.add(makeRoom_btn);

		enterRoom_btn = new JButton("대화방 입장");
		enterRoom_btn.setForeground(new Color(255, 255, 255));
		enterRoom_btn.setBackground(new Color(100, 149, 237));
		enterRoom_btn.setBounds(471, 372, 160, 39);
		contentPane.add(enterRoom_btn);

		JLabel logo_lbl = new JLabel("New label");
		logo_lbl.setIcon(new ImageIcon("images/Screenshot_8.png"));
		logo_lbl.setBounds(24, 20, 85, 16);
		contentPane.add(logo_lbl);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(43, 64, 416, 348);
		contentPane.add(tabbedPane);

		JPanel homePane = new JPanel();
		tabbedPane.addTab("채널홈", null, homePane, null);
		homePane.setBackground(new Color(255, 255, 255));
		homePane.setLayout(null);

		JPanel titlePane = new JPanel();
		titlePane.setBackground(new Color(100, 149, 237));
		titlePane.setBounds(0, 0, 415, 25);
		homePane.add(titlePane);
		titlePane.setLayout(null);

		JLabel lblNewLabel = new JLabel("채널에 개설된 대화방 목록입니다. ");
		lblNewLabel.setBounds(10, 5, 190, 16);
		titlePane.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("휴먼모음T", Font.PLAIN, 14));
		lblNewLabel.setBackground(new Color(0, 0, 0));

		// title_list = new DefaultListModel<>();

		roomlist = new JList<>(list);
		roomlist.setVisibleRowCount(12);
		roomlist.setBackground(new Color(255, 255, 255));
		roomlist.setBorder(new LineBorder(new Color(169, 169, 169)));
		roomlist.setBounds(0, 24, 415, 295);
		homePane.add(roomlist);

		JPanel friendPane = new JPanel();

		friendPane.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("토크온 친구", null, friendPane, null);
		friendPane.setLayout(null);

		JLabel wholeUser_lbl = new JLabel("전체 유저");
		wholeUser_lbl.setBounds(31, 30, 57, 15);
		friendPane.add(wholeUser_lbl);

		wholeUser_list = new JList<>();
		wholeUser_list.setVisibleRowCount(12);
		wholeUser_list.setBorder(new LineBorder(new Color(105, 105, 105), 2));
		wholeUser_list.setBounds(30, 50, 150, 250);
		friendPane.add(wholeUser_list);

		msg_btn = new JButton("쪽지보내기");
		msg_btn.setForeground(new Color(255, 255, 255));
		msg_btn.setBackground(new Color(100, 149, 237));
		msg_btn.setBounds(236, 50, 130, 40);

		friendPane.add(msg_btn);

	 	addActionListener();
		setVisible(true);

	}

	private void addActionListener() {
		makeRoom_btn.addActionListener(this);
		msg_btn.addActionListener(this);
		enterRoom_btn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == makeRoom_btn) {
			makeRoomFrame = new MakeRoomFrame(this);
			
			
		} else if (e.getSource() == msg_btn) {
			System.out.println("쪽지보내기버튼 클릭");
			String user = (String) wholeUser_list.getSelectedValue();
			if (user == null) {
				JOptionPane.showMessageDialog(null, "대상을 선택하세요", "알림", JOptionPane.ERROR_MESSAGE);
			}
			String note = JOptionPane.showInputDialog("보낼메세지");
			if (note != null) {
				mContext.client.sendmessage("Note/" + user + "@" + note);
			}
		}else if (e.getSource() == enterRoom_btn) {
			String room = (String) roomlist.getSelectedValue();
			if (room == null) {
				JOptionPane.showMessageDialog(null, "대상을 선택하세요", "알림", JOptionPane.ERROR_MESSAGE);
			}
			roomFrame = new RoomFrame(this);
			roomFrame.info_list.setListData(mContext.client.roomUser_list);
			mContext.client.sendmessage("enterRoom/" + room + "@" + mContext.userId);
//			mContext.client.sendmessage("NewChatUser/" + mContext.userId);
			
			
		}


	}

}