package ch05.client.GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ch05.client.Client;
import ch05.server.ServerGUI;

public class MainFrame extends JFrame implements ActionListener {
	MainFrame mContext = this;
	private JPanel contentPane;
	private JTextField port_txt;
	private JTextField name_txt;
	private JButton login_btn;
	private JButton join_btn;
	private JButton find_btn;
	public Client client;
	String userId;
	public HomeFrame homeFrame;
	

	public MainFrame() {
		initData();
		setInitLayout();
		addAcitonListener();

	}

	private void addAcitonListener() {
		login_btn.addActionListener(this);

	}

	private void initData() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 576);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel logo_lbl = new JLabel();
		logo_lbl.setIcon(new ImageIcon("images/Screenshot_8.png"));
		logo_lbl.setFont(new Font("굴림", Font.BOLD, 20));
		logo_lbl.setBounds(12, 10, 91, 51);
		contentPane.add(logo_lbl);

		JLabel port_lbl = new JLabel("PORT");
		port_lbl.setFont(new Font("MS PGothic", Font.BOLD, 15));
		port_lbl.setBounds(68, 224, 57, 15);
		contentPane.add(port_lbl);

		JLabel name_lbl = new JLabel("NAME");
		name_lbl.setFont(new Font("MS PGothic", Font.BOLD, 15));
		name_lbl.setBounds(68, 282, 57, 15);
		contentPane.add(name_lbl);

		port_txt = new JTextField("12345");
		port_txt.setBounds(154, 222, 116, 21);
		contentPane.add(port_txt);
		port_txt.setColumns(10);

		name_txt = new JTextField();
		name_txt.setBounds(154, 280, 116, 21);
		contentPane.add(name_txt);
		name_txt.setColumns(10);

		login_btn = new JButton();
		login_btn.setIcon(new ImageIcon("images/Screenshot_2.png"));
		login_btn.setSelectedIcon(new ImageIcon("images/Screenshot_2.png"));
		login_btn.setBounds(84, 370, 253, 42);
		contentPane.add(login_btn);

		JLabel logos_lbl = new JLabel();
		logos_lbl.setIcon(new ImageIcon("images/Screenshot_1.png"));
		logos_lbl.setBounds(147, 82, 123, 96);
		contentPane.add(logos_lbl);

		join_btn = new JButton("New button");
		join_btn.setBounds(94, 481, 97, 23);
		contentPane.add(join_btn);

		find_btn = new JButton("New button");
		find_btn.setBounds(229, 481, 97, 23);
		contentPane.add(find_btn);
		repaint();
	}

	private void setInitLayout() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == login_btn) {
			try {

				// client.user_list.add(name_txt.getText());
				for (int i = 0; i < 3; i++) {
					try {
						// System.out.println(client.user_list.get(i));
					} catch (Exception e2) {

					}

				}

				// System.out.println(client.port);
				homeFrame = new HomeFrame(this);
				// a.list.setListData(client.user_list);
				client = new Client(this);
				client.port = Integer.parseInt(port_txt.getText());
				client.connect();

				/// ------------
				userId = name_txt.getText();
				client.sendmessage(userId);
				client.user_list.add(userId);

				homeFrame.wholeUser_list.setListData(client.user_list);

				dispose();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "연결실패!", "알림", JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		new MainFrame(); 
		new MainFrame(); 		
		new ServerGUI();
	}
}