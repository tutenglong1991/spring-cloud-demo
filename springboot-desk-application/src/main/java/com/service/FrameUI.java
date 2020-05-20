package com.service;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FrameUI extends JFrame {
	/**
	 * 
	 */
	@Autowired
	private TestComponent test;

	private static final long serialVersionUID = 1L;
	private JTextField hostTxt;
	private JTextField userNameTxt;
	private JPasswordField passwordTxt;
	private JButton connBtn;
	private JButton disConnBtn;

	private JTextField subcribeTxt;
	private JButton subBtn;
	private JButton cancelSubBtn;
	private JTextArea subShowTxt;

	private JTextField topicTxt;
	private JTextField msgTxt;
	private JButton publishBtn;
	private JTextArea publishTxt;

	public void initUI() {
		hostTxt = new JTextField("tcp://127.0.0.1:61613");
		hostTxt.setPreferredSize(new Dimension(200, 30));
		userNameTxt = new JTextField("admin");
		userNameTxt.setPreferredSize(new Dimension(100, 30));
		passwordTxt = new JPasswordField("password");
		passwordTxt.setPreferredSize(new Dimension(100, 30));
		connBtn = new JButton("Connect");
		connBtn.setPreferredSize(new Dimension(100, 30));
		disConnBtn = new JButton("Disconnect");
		disConnBtn.setPreferredSize(new Dimension(100, 30));
		JPanel topPanel = new JPanel();
		// topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(hostTxt);
		topPanel.add(userNameTxt);
		topPanel.add(passwordTxt);
		topPanel.add(connBtn);
		topPanel.add(disConnBtn);
		add(topPanel, BorderLayout.NORTH);

		subcribeTxt = new JTextField("wether");
		subcribeTxt.setPreferredSize(new Dimension(300, 30));
		subBtn = new JButton("Subscribe");
		subBtn.setPreferredSize(new Dimension(110, 30));
		cancelSubBtn = new JButton("CancelSubscrible");
		cancelSubBtn.setPreferredSize(new Dimension(200, 30));
		subShowTxt = new JTextArea();
		subShowTxt.setPreferredSize(new Dimension(620, 300));
		JPanel centerPanel = new JPanel(new BorderLayout());
		JPanel centerTopPanel = new JPanel();
		centerTopPanel.add(subcribeTxt, BorderLayout.NORTH);
		centerTopPanel.add(subBtn);
		centerTopPanel.add(cancelSubBtn);
		centerPanel.add(centerTopPanel, BorderLayout.NORTH);
		JPanel centerCenterPanel = new JPanel();
		centerCenterPanel.add(subShowTxt);
		centerPanel.add(centerCenterPanel, BorderLayout.CENTER);

		topicTxt = new JTextField("/wether");
		topicTxt.setPreferredSize(new Dimension(250, 30));
		msgTxt = new JTextField("");
		msgTxt.setPreferredSize(new Dimension(250, 30));
		publishBtn = new JButton("publish");
		publishBtn.setPreferredSize(new Dimension(115, 30));
		publishTxt = new JTextArea();
		publishTxt.setPreferredSize(new Dimension(620, 300));
		JPanel bottomPanel = new JPanel(new BorderLayout());
		JPanel bottomTopPanel = new JPanel();
		JPanel bottomCenterPanel = new JPanel();
		bottomTopPanel.add(topicTxt);
		bottomTopPanel.add(msgTxt);
		bottomTopPanel.add(publishBtn);
		bottomCenterPanel.add(publishTxt);
		bottomPanel.add(bottomTopPanel, BorderLayout.NORTH);
		bottomPanel.add(bottomCenterPanel, BorderLayout.CENTER);

		JPanel center = new JPanel(new GridLayout(2, 1));
		center.add(centerPanel);
		center.add(bottomPanel);
		add(center, BorderLayout.CENTER);

		initEvent();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setLocationRelativeTo(null);
		setSize(1000, 800);
		validate();
		setVisible(true);
	}

	private void initEvent() {
		connBtn.addActionListener(e -> {

		});
		disConnBtn.addActionListener(e -> {

		});
		subBtn.addActionListener(e -> {

		});
		cancelSubBtn.addActionListener(e -> {

		});
		publishBtn.addActionListener(e -> {
			setPubMsg(test.getTestMsg());
		});
	}

	public void setMsg(String msg) {
		subShowTxt.append(msg + "\n");
	}

	public void setPubMsg(String msg) {
		publishTxt.append(msg + "\n");
	}

	public String getURL() {
		return hostTxt.getText();
	}

	public String getUserName() {
		return userNameTxt.getText();
	}

	public char[] getPassword() {
		return passwordTxt.getPassword();
	}

	public String getTopic() {
		return subcribeTxt.getText();
	}

	public String getClientId() {
		return "1234";
	}

}
