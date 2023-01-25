package ch05.client.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ch05.client.Client;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class MakeRoomFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField roomName_txt;
	JButton ok_btn;
	JComboBox<String> roomComboBox;

	HomeFrame homeFrame;
	public RoomFrame roomFrame;
	String roomName;
	String roomSize;
	JButton cancle_btn;
	List<String> roomInfo = new ArrayList<>();

	public MakeRoomFrame(HomeFrame home) {
		this.homeFrame = home;
		setVisible(true);
		setTitle("대화방 만들기");
		
		setBounds(100, 100, 450, 227);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		ok_btn = new JButton("확인");
		ok_btn.setForeground(new Color(255, 255, 255));
		ok_btn.setOpaque(true);
		ok_btn.setBackground(new Color(100, 149, 237));
		ok_btn.setBounds(129, 136, 97, 23);
		contentPane.add(ok_btn);

		cancle_btn = new JButton("취소");
		cancle_btn.setForeground(new Color(255, 255, 255));
		cancle_btn.setOpaque(true);
		cancle_btn.setBackground(new Color(100, 149, 237));
		cancle_btn.setBounds(252, 136, 97, 23);
		contentPane.add(cancle_btn);

		roomName_txt = new JTextField();
		roomName_txt.setBounds(137, 54, 240, 21);
		contentPane.add(roomName_txt);
		roomName_txt.setColumns(10);

		JLabel roomName_lbl = new JLabel("대화방 제목");
		roomName_lbl.setBounds(42, 57, 75, 15);
		contentPane.add(roomName_lbl);

		JLabel roomMem_lbl = new JLabel("참여인원");
		roomMem_lbl.setBounds(42, 94, 75, 15);
		contentPane.add(roomMem_lbl);
		String[] a = { "인원선택", "1", "2", "3", "4", "5", "6", "7", "8" };
		roomComboBox = new JComboBox(a);
		roomComboBox.setBounds(135, 90, 78, 23);
		contentPane.add(roomComboBox);

		JLabel roomMemb_lbl = new JLabel("명 (최대 8명)");
		roomMemb_lbl.setBounds(225, 87, 107, 29);
		contentPane.add(roomMemb_lbl);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ITPS\\Desktop\\Screenshot_8.png"));
		lblNewLabel.setBounds(163, 10, 97, 34);
		contentPane.add(lblNewLabel);
		// client.getInstance();
		addActionListener();
	}

	private void addActionListener() {
		ok_btn.addActionListener(this);
		cancle_btn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok_btn) {
			if (roomName_txt.getText().length() <= 4) {
				JOptionPane.showMessageDialog(null, "방제목 4글자 이상.", "알림", JOptionPane.ERROR_MESSAGE);
			} else if (roomComboBox.getSelectedItem().equals("인원선택")) {
				JOptionPane.showMessageDialog(this, "인원을 선택해주세요");
			} else {
				dispose();
				try {
					System.out.println("방 만들기");

					roomFrame = new RoomFrame(homeFrame);
					roomName = roomName_txt.getText();
					homeFrame.list.addElement(roomName_txt.getText());
					homeFrame.mContext.client.roomUser_list.add(homeFrame.mContext.userId);
					
					
					homeFrame.mContext.client.sendmessage("NewRoomName/" + roomName );
					homeFrame.mContext.client.sendmessage("NewRoomUser/" + homeFrame.mContext.userId );
					homeFrame.mContext.client.myRoom = roomName;

					
					roomFrame.info_list.setListData(homeFrame.mContext.client.roomUser_list);

				} catch (Exception e1) {

					e1.printStackTrace();
				}

				
			}
		} 
	}

}
