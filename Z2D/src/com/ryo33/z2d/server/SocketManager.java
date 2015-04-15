package com.ryo33.z2d.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.StandardSocketOptions;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import javax.imageio.spi.RegisterableService;
import javax.swing.plaf.synth.Region;

import com.ryo33.z2d.Default;
import com.ryo33.z2d.network.Packet;
import com.ryo33.z2d.network.TaskSet;
import com.ryo33.z2d.util.ConfigManager;

public class SocketManager implements Runnable {

	private ConfigManager configManager;
	private HashMap<SocketChannel, Client> clients;
	private Queue<TaskSet> taskSets;
	private Queue<Packet> packets;
	private Selector selector;
	private ServerSocketChannel serverSocketChannel;

	public SocketManager(Queue<TaskSet> taskSets, Queue<Packet> packets, ConfigManager configManager, int port) {
		this.taskSets = taskSets;
		this.packets = packets;
		this.configManager = configManager;
		this.clients = new HashMap<SocketChannel, Client>();
		init(port);
		start();
	}

	private void init(int port) {
		try {
			selector = Selector.open();
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
			serverSocketChannel.configureBlocking(false);
			System.out.println(port);
			serverSocketChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(), port));
			register();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void register() throws ClosedChannelException {
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
	}

	private void start() {
		new Thread(this).start();
	}

	private void register(SocketChannel socketChannel) throws ClosedChannelException {
		socketChannel.register(selector, SelectionKey.OP_READ);
	}
	
	private void register(SocketChannel socketChannel, Packet packet) throws ClosedChannelException{
		socketChannel.register(selector, SelectionKey.OP_WRITE, packet);
	}

	@Override
	public void run() {
		try {
			while (selector.isOpen()) {
				if(selector.select() > 0){
					Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
					while (iterator.hasNext()) {
						SelectionKey selectionKey = (SelectionKey) iterator.next();
						iterator.remove();
						if (selectionKey.isAcceptable()) {
							SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
							socketChannel.configureBlocking(false);
							socketChannel.register(selector, SelectionKey.OP_READ);
							clients.put(socketChannel, new Client(socketChannel));
							System.out.println(socketChannel.toString());
							register();
						} else {
							if (selectionKey.isReadable()) {
								SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
								Packet packet = clients.get(socketChannel).recieve();
								if (packet != null) {
									packets.add(packet);
								}
								register(socketChannel);
							}
							if (selectionKey.isWritable()) {
								SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
								Packet packet = (Packet) selectionKey.attachment();
								clients.get(socketChannel).send(packet);
							}
						}
					}
				}
			}
		} catch (IOException ex) {
			System.out.println(ex.getClass());
			ex.printStackTrace();
			return;
		}
	}

	public Socket getSocket() {
		try {
			return new ServerSocket().accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean canJoin() {
		int count = 0;
		for (Iterator<Client> iterator = iterator(); iterator.hasNext();) {
			if (iterator.next().isJoined()) {
				count++;
			}
		}
		return count <= configManager.getConfig("general", "joinMax", Default.joinMax).getInt();
	}

	public Iterator<Client> iterator() {
		return clients.values().iterator();
	}

	public void update() {
		TaskSet taskSet = new TaskSet();
		while (!taskSets.isEmpty()) {
			taskSet.add(taskSets.poll());
		}
		for (int i = 0, len = clients.size(); i < len; i++) {
			if (clients.get(i).isJoined()) {
				clients.get(i).send(new Packet(taskSet));
			}
		}
	}

}
