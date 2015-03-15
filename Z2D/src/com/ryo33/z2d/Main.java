package com.ryo33.z2d;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.ByteBuffer;
import java.util.Timer;
import java.util.TimerTask;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWWindowCloseCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import com.ryo33.z2d.client.helper.MouseHelper;
import com.ryo33.z2d.client.helper.RenderHelper;
import com.ryo33.z2d.client.manager.StringManager;
import com.ryo33.z2d.client.manager.UMasterManager;
import com.ryo33.z2d.client.manager.WindowManager;
import com.ryo33.z2d.util.Debug;

public class Main {

	private GLFWErrorCallback errorCallback;
	private GLFWKeyCallback keyCallback;
	private GLFWMouseButtonCallback mouseButtonCallback;
	private GLFWCursorPosCallback cursorPosCallback;
	private GLFWWindowSizeCallback windowSizeCallback;
	private GLFWWindowCloseCallback windowCloseCallback;

	public static int initWidth = 16 * 60;
	public static int initHeight = 9 * 60;
	public static final String title = "Z2D";

	private long window;

	private UMasterManager masterManager;

	public static WindowManager windowManager;
	public static StringManager stringManager;

	public void run() {
		try {
			init();
			loop();
			glfwDestroyWindow(window);
			keyCallback.release();
			mouseButtonCallback.release();
			cursorPosCallback.release();
			windowSizeCallback.release();
			windowCloseCallback.release();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			glfwTerminate();
			errorCallback.release();
			System.exit(0);
		}
	}

	private void init() {
		windowManager = new WindowManager(window, initWidth, initHeight);
		stringManager = new StringManager();

		masterManager = new UMasterManager();

		glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));
		if (glfwInit() != GL11.GL_TRUE)
			throw new IllegalStateException("Unable to initialize GLFW");

		initWindow();
		initCallBack();

		MouseHelper.setNormal(window);

		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				masterManager.update();
			}
		}, 10, 10);
	}

	private void initWindow() {
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		window = glfwCreateWindow(initWidth, initHeight, title, NULL, NULL);
		if (window == NULL)
			throw new RuntimeException("Failed to create the GLFW window");
		ByteBuffer videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (GLFWvidmode.width(videoMode) - initWidth) / 2, (GLFWvidmode.height(videoMode) - initHeight) / 2);
		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);

		glfwShowWindow(window);
	}

	private void initCallBack() {
		glfwSetKeyCallback(window, keyCallback = GLFWKeyCallback((window, key, scancode, action, mods) -> {
			if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
				glfwSetWindowShouldClose(window, GL_TRUE);
			if (key == GLFW_KEY_F2 && action == GLFW_RELEASE) {
				windowManager.toggleFullScreen();
			}
			masterManager.eventKey(key, scancode, action, mods);
		}));
		glfwSetMouseButtonCallback(window, mouseButtonCallback = GLFWMouseButtonCallback((window, button, action, mods) -> {
			masterManager.eventMouseButton(button, action, mods);
		}));
		glfwSetCursorPosCallback(window, cursorPosCallback = GLFWCursorPosCallback((window, xpos, ypos) -> {
			masterManager.eventCursorPos(xpos, windowManager.height - ypos + 1);
		}));
		glfwSetWindowSizeCallback(window, windowSizeCallback = GLFWWindowSizeCallback((window, width, height) -> {
			RenderHelper.isUpdateWindowSize = true;
			windowManager.setSize(width, height);
			windowManager.redoLayout(null);
			masterManager.eventWindowSize();
		}));
		glfwSetWindowCloseCallback(window, windowCloseCallback = GLFWWindowCloseCallback((sam) -> {
			System.exit(0);
		}));
	}

	private void loop() {
		GLContext.createFromCurrent();
		// Enable transparency
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		// Enable front side only
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);

		glShadeModel(GL_SMOOTH);
		while (glfwWindowShouldClose(window) == GL_FALSE) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			masterManager.render();
			glfwSwapBuffers(window);
			glfwPollEvents();
		}
	}

	public static void main(String[] args) {
		// debug mode on
		Debug.isDebug = true;
		new Main().run();
	}

}