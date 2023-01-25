package ch05.client.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JList;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

public class RoomFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField send_txt;
	public JList<String> info_list;
	JButton send_btn;
	public JTextArea showText_txtA ;
	public HomeFrame homeFrame;
	public JButton out_btn;
//	public DefaultListModel<String> list = new DefaultListModel<>();
	
	public RoomFrame(HomeFrame homeFrame) {
		this.homeFrame = homeFrame;
		setTitle("talk on");

		setBounds(100, 100, 639, 398);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel textPane = new JPanel();
		textPane.setBounds(32, 46, 393, 303);
		contentPane.add(textPane);
		textPane.setLayout(null);

		JPanel titleRoomPane = new JPanel();
		titleRoomPane.setBackground(new Color(100, 149, 237));
		titleRoomPane.setBounds(0, 0, 393, 23);
		textPane.add(titleRoomPane);
		titleRoomPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("방 제목");
		lblNewLabel.setFont(new Font("휴먼엑스포", Font.BOLD, 12));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(12, 0, 57, 25);
		titleRoomPane.add(lblNewLabel);

		send_txt = new JTextField();
		send_txt.setBounds(12, 272, 296, 21);
		textPane.add(send_txt);
		send_txt.setColumns(10);

		send_btn = new JButton("보내기");
		send_btn.setBounds(309, 271, 72, 23);
		send_btn.setBackground(new Color(100, 149, 237));
		send_btn.setForeground(new Color(255, 255, 255));
		send_btn.setOpaque(true);
		textPane.add(send_btn);

		showText_txtA = new JTextArea();
		showText_txtA.setBorder(new LineBorder(new Color(105, 105, 105), 2));
		showText_txtA.setBounds(12, 33, 369, 238);
		textPane.add(showText_txtA);

		JPanel infoPane = new JPanel();
		infoPane.setBounds(447, 46, 164, 242);
		contentPane.add(infoPane);
		infoPane.setLayout(null);

		JPanel titleInfoPane = new JPanel();
		titleInfoPane.setBackground(new Color(100, 149, 237));
		titleInfoPane.setBounds(0, 0, 164, 24);
		infoPane.add(titleInfoPane);

		JLabel lblNewLabel_1 = new JLabel("참여자");
		lblNewLabel_1.setFont(new Font("휴먼엑스포", Font.BOLD, 12));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		titleInfoPane.add(lblNewLabel_1);

		info_list = new JList<>();
		info_list.setBorder(new LineBorder(new Color(105, 105, 105), 2));
		info_list.setBounds(10, 34, 142, 198);
		infoPane.add(info_list);

		out_btn = new JButton("나가기");
		out_btn.setOpaque(true);
		out_btn.setForeground(new Color(255, 255, 255));
		out_btn.setBackground(new Color(100, 149, 237));
		out_btn.setBounds(447, 309, 164, 40);
		contentPane.add(out_btn);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("images/Screenshot_8.png"));
		lblNewLabel_2.setBounds(12, 10, 94, 26);
		contentPane.add(lblNewLabel_2);

		setVisible(true);
		addAcitonListener();
	}

	private void addAcitonListener() {
		send_btn.addActionListener(this);
		out_btn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == send_btn) {
			
			
			homeFrame.mContext.client.sendmessage("Chatting/" + homeFrame.mContext.client.myRoom + "/" + send_txt.getText().trim());
		}else if (e.getSource() == out_btn) {
			homeFrame.mContext.client.sendmessage("userOUt/" + homeFrame.mContext.client.myRoom  );
			dispose();
			
		}

	}
}
