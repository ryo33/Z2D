package com.ryo33.z2d.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Queue;

import com.ryo33.z2d.network.Packet;
import com.ryo33.z2d.network.TaskSocket;

public class NetworkManager implements Runnable {
	
	private TaskSocket socket;
	private Queue<Packet> packets;
	private Selector selector;
	private InetAddress address;
	private int port;
	
	public NetworkManager(Queue<Packet> packets, InetAddress address, int port){
		this.packets = packets;
		this.address = address;
		this.port = port;
		init();
	}

	public void init() {
		try {
			this.selector = Selector.open();
			SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(address, port));
			socketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
			socketChannel.configureBlocking(false);
			socketChannel.register(selector, SelectionKey.OP_READ);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		try {
			while (selector.select() > 0) {
				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
				while (iterator.hasNext()) {
					SelectionKey selectionKey = (SelectionKey) iterator.next();
					iterator.remove();
					if (selectionKey.isReadable()) {
						packets.add(socket.recieve());
					}
				}
			}
		} catch (IOException e) {
			System.out.println(e.getClass());
			e.printStackTrace();
			return;
		}
	}

	public String getName() {
		return address.getHostAddress() + ":" + port;
	}

}
