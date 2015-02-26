package com.ryo33.z2d.server;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.ryo33.z2d.game.GameData;

public class Server {
	
	public final int width = 16 * 40, height = 9 * 40;
	
	public JFrame frame;
	public JPanel panel;
	public JTextArea textarea;

	public Server(GameData data){
		initWindow();
	}
	
	private void initWindow(){
		frame = new JFrame();
		panel = new JPanel();
		textarea = new JTextArea();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.add(panel);
		panel.add(textarea);
		frame.setVisible(true);
		frame.setSize(width, height);
	}
	
}
