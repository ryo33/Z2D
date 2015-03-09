package com.ryo33.z2d.server;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.PrintStream;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.ryo33.z2d.Main;
import com.ryo33.z2d.game.GameData;
import com.ryo33.z2d.network.Packet;
import com.ryo33.z2d.network.TaskSet;
import com.ryo33.z2d.util.ConfigManager;

public class Server {

	private static final int width = 16 * 40, height = 9 * 40;
	private static final String properties = "Properties", exit = "Exit", command = "Command", status = "Server Status";
	private static final String directory = "server/";

	private PrintStream out, err;
	private ConfigManager configManager;

	private JFrame frame;
	private JPanel panel;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JMenuBar menuBar;
	private JMenu[] menus;
	private JMenuItem[] menuItems;

	private GameData data;
	private SocketManager socketManager;
	private boolean isRun;
	private Queue<TaskSet> taskSets;
	private Queue<Packet> packets;

	public Server(String name, int port) {
		initServer(name, port);
	}

	public void initServer(String name, int port) {
		initWindow(name);
		isRun = true;
		this.out = System.out;
		this.err = System.err;
		// System.setOut();
		// System.setErr();
		File serverFolder = new File(directory + name);
		if (!serverFolder.exists())
			serverFolder.mkdirs();
		this.data = GameData.getGameData(name);
		configManager = new ConfigManager(new File(directory + name + "/server" + ConfigManager.extention));
		taskSets = new ConcurrentLinkedQueue<TaskSet>();
		packets = new ConcurrentLinkedQueue<Packet>();
		socketManager = new SocketManager(taskSets, packets, configManager, port);
	}

	public void exitServer() {
		frame.dispose();
		data.saveData();
		isRun = false;
		System.setOut(this.out);
		System.setErr(this.err);
		configManager.write();
	}

	private void initWindow(String name) {
		frame = new JFrame(name + " - " + Main.title + " Server");
		panel = new JPanel();
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
		menuBar = new JMenuBar();
		menus = new JMenu[] {
		new JMenu("General"), new JMenu("Tool"), new JMenu("Help")
		};
		menuItems = new JMenuItem[] {
		new JMenuItem(properties), new JMenuItem(exit), new JMenuItem(command), new JMenuItem(status)
		};

		for (JMenu menu : menus)
			menuBar.add(menu);
		menus[0].add(menuItems[0]);
		menus[0].add(menuItems[1]);
		menus[1].add(menuItems[2]);
		menus[1].add(menuItems[3]);

		textArea.setEditable(false);
		panel.setLayout(new BorderLayout());
		panel.add(scrollPane, BorderLayout.CENTER);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		frame.add(menuBar, BorderLayout.NORTH);

		for (JMenuItem menuItem : menuItems) {
			menuItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					super.mouseReleased(e);
					Component clicked = e.getComponent();
					switch (((JMenuItem) clicked).getText()) {
					case exit:
						exitServer();
						break;
					case properties:
						break;
					case command:
						break;
					case status:
						break;
					}
				}
			});
		}
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				super.windowClosed(e);
				exitServer();
			}
		});

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(width, height);
		frame.setVisible(true);
	}

	public boolean isRun() {
		return isRun;
	}

	public static String getDirectory(String name) {
		return directory + name + "/";
	}

}
