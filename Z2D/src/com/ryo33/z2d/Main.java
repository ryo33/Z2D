package com.ryo33.z2d;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.ByteBuffer;
import java.util.Timer;
import java.util.TimerTask;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import com.ryo33.z2d.manager.MasterManager;
import com.ryo33.z2d.wrapper.Z2DMouse;

public class Main {

	private GLFWErrorCallback errorCallback;
	private GLFWKeyCallback keyCallback;
	private GLFWMouseButtonCallback mouseButtonCallback;
	private GLFWCursorPosCallback cursorPosCallback;

	private int initWidth = 16 * 60;
	private int initHeight = 9 * 60;

	private long window;
	private MasterManager masterManager;

	public void run() {
		try {
			init();
			loop();
			glfwDestroyWindow(window);
		} finally {
			glfwTerminate();
			keyCallback.release();
			mouseButtonCallback.release();
			cursorPosCallback.release();
			errorCallback.release();
		}
	}

	private void init() {
		glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));
		if (glfwInit() != GL11.GL_TRUE)
			throw new IllegalStateException("Unable to initialize GLFW");
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);

		window = glfwCreateWindow(initWidth, initHeight, "Hello World!", NULL, NULL);
		if (window == NULL)
			throw new RuntimeException("Failed to create the GLFW window");

		glfwSetKeyCallback(window, keyCallback = GLFWKeyCallback((window, key, scancode, action, mods) -> {
			if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
				glfwSetWindowShouldClose(window, GL_TRUE);
			masterManager.eventKey(key, scancode, action, mods);
		}));
		glfwSetMouseButtonCallback(window, mouseButtonCallback = GLFWMouseButtonCallback((window, button, action, mods) -> {
			masterManager.eventMouseButton(button, action, mods);
		}));
		glfwSetCursorPosCallback(window, cursorPosCallback = GLFWCursorPosCallback((window, xpos, ypos) -> {
			masterManager.eventCursorPos(xpos, ypos);
		}));

		ByteBuffer videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (GLFWvidmode.width(videoMode) - initWidth) / 2, (GLFWvidmode.height(videoMode) - initHeight) / 2);
		Z2DMouse.setNormal(window);
		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);

		glfwShowWindow(window);

		masterManager = new MasterManager();
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				masterManager.update();
			}
		}, 10, 10);
	}

	private void loop() {
		GLContext.createFromCurrent();

		glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

		while (glfwWindowShouldClose(window) == GL_FALSE) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			masterManager.render();
			glfwSwapBuffers(window);
			glfwPollEvents();
		}
	}

	public static void main(String[] args) {
		new Main().run();
	}

}