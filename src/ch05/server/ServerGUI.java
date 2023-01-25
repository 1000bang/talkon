package ch05.server;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class ServerGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField port_txt;
	private JButton serverOpen_btn;
	JTextArea logHere_txtA;
	private JButton serverClose_btn;
	public static int HOST_PORT;

	public ServerGUI() {

		initData();
		addEventListener();
	}

	private void initData() {
		setTitle("관리자페이지");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 450, 477);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel logo_lbl = new JLabel("");
		logo_lbl.setIcon(new ImageIcon("images/Screenshot_8.png"));
		logo_lbl.setBounds(149, 10, 103, 52);
		contentPane.add(logo_lbl);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(54, 136, 300, 253);
		contentPane.add(scrollPane);

		JLabel log_lbl = new JLabel("Log");
		log_lbl.setFont(new Font("굴림", Font.BOLD, 14));
		log_lbl.setBounds(44, 111, 57, 15);
		contentPane.add(log_lbl);

		JLabel port_lbl = new JLabel("Port");
		port_lbl.setFont(new Font("MS PGothic", Font.BOLD, 15));
		port_lbl.setBounds(44, 74, 40, 15);
		contentPane.add(port_lbl);

		port_txt = new JTextField("12345");
		port_txt.setBounds(107, 72, 116, 21);
		contentPane.add(port_txt);
		port_txt.setColumns(10);

		serverOpen_btn = new JButton("Server Open");
		serverOpen_btn.setBackground(new Color(100, 149, 237));
		serverOpen_btn.setForeground(new Color(255, 255, 255));
		serverOpen_btn.setOpaque(true);
		serverOpen_btn.setBounds(246, 70, 108, 24);
		contentPane.add(serverOpen_btn);

		logHere_txtA = new JTextArea();
		logHere_txtA.setFont(new Font("굴림", Font.PLAIN, 15));
		logHere_txtA.setBackground(new Color(255, 255, 255));
		logHere_txtA.setForeground(new Color(0, 0, 0));
		logHere_txtA.setBounds(54, 136, 298, 253);
		scrollPane.setViewportView(logHere_txtA);

		serverClose_btn = new JButton("Server Close");
		serverClose_btn.setOpaque(true);
		serverClose_btn.setForeground(Color.WHITE);
		serverClose_btn.setBackground(new Color(100, 149, 237));
		serverClose_btn.setBounds(246, 106, 108, 24);
		contentPane.add(serverClose_btn);
		repaint();

	}

	private void Thread() {
		new Thread(() -> {

		}).start();
		;
	}

	private void addEventListener() {
		serverOpen_btn.addActionListener(this);
		serverClose_btn.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == serverOpen_btn) {
			HOST_PORT = Integer.parseInt(port_txt.getText());
			new Server(this);
		} else if (e.getSource() == serverClose_btn) {

		}

	}

	public static void main(String[] args) {
		new ServerGUI();
	}

}
