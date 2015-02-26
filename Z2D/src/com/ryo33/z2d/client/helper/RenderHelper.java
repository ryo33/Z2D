package com.ryo33.z2d.client.helper;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import java.awt.Color;

import com.ryo33.z2d.Main;
import com.ryo33.z2d.util.Box;
import com.ryo33.z2d.util.Point3D;
import com.ryo33.z2d.util.Texture;

public class RenderHelper {

	public static float fovy = 45;
	public static float zNear = 0.1F, zFar = 1000F;
	public static final int mode2D = 1, mode3D = 2, modeNothing = 0;
	public static boolean isUpdateWindowSize;

	public static int renderMode;

	public static void translate(float x, float y, float z) {
		glTranslatef(0, 0, -5);
	}

	public static void setColor(float r, float g, float b) {
		glColor3f(r, g, b);
	}

	public static void renderPlane(Point3D... points) {
		for (int i = 0, len = points.length; i < len; i++) {
			glVertex3f(points[i].x, points[i].y, points[i].z);
		}
	}

	public static void renderTexturedPlane(Point3D... points) {
		for (int i = 0, len = points.length / 2; i < len; i++) {
			glTexCoord2f(points[i + len].x, points[i + len].y);
			glVertex3f(points[i].x, points[i].y, points[i].z);
		}
	}

	public static void renderBox(Point3D a, Point3D b, Point3D c, Point3D d) {
		glBegin(GL_QUADS);
		renderPlane(a, b, c, d);
		glEnd();
	}

	public static void renderBox(Point3D a) {
		renderBox(a, 1, 1);
	}

	public static void renderBox(Box box, Object... args) {
		actionArgs(args);
		renderBox(new Point3D(box.x, box.y), box.width, box.height);
	}

	public static void renderBox(Point3D a, float width, float height) {
		glBegin(GL_QUADS);
		renderPlane(a, new Point3D(a.x + width, a.y, a.z), new Point3D(a.x + width, a.y + height, a.z), new Point3D(a.x, a.y + height, a.z));
		glEnd();
	}

	public static void renderCube(Point3D a) {
		renderCube(a, 1, 1, 1);
	}

	public static void renderCube(Point3D a, float width, float height, float depth) {
		renderCube(new Point3D(a.x, a.y, a.z + depth), new Point3D(a.x + width, a.y, a.z + depth), new Point3D(a.x + width, a.y + height, a.z + depth), new Point3D(a.x, a.y + height, a.z + depth), new Point3D(a.x, a.y, a.z), new Point3D(a.x + width, a.y, a.z), new Point3D(a.x + width, a.y + height, a.z), new Point3D(a.x, a.y + height, a.z));
	}

	public static void renderCube(Point3D a, Point3D b, Point3D c, Point3D d, Point3D e, Point3D f, Point3D g, Point3D h) {
		glBegin(GL_QUADS);
		glColor3f(1, 0, 0);
		renderPlane(a, b, c, d);

		glColor3f(0, 1, 0);
		renderPlane(d, c, g, h);

		glColor3f(0, 0, 1);
		renderPlane(h, g, f, e);

		glColor3f(1, 1, 0);
		renderPlane(e, f, b, a);

		glColor3f(0, 1, 1);
		renderPlane(e, a, d, h);

		glColor3f(1, 0, 1);
		renderPlane(b, f, g, c);
		glEnd();
	}

	public static void renderString(String str, Box box, Object... args) {
		actionArgs(args);
		glEnable(GL_TEXTURE_2D);
		Texture tex = Main.stringManager.get(str);
		float paddingX = box.width / 2 - tex.width / 2;
		float paddingY = box.height / 2 - tex.height / 2;
		glPushAttrib(GL_TEXTURE_BINDING_2D);
		tex.bind();
		glBegin(GL_QUADS);
		renderTexturedPlane(new Point3D(box.x + paddingX, box.y + paddingY), new Point3D(box.x + box.width - paddingX, box.y + paddingY), new Point3D(box.x + box.width - paddingX, box.y + tex.height + paddingY), new Point3D(box.x + paddingX, box.y + tex.height + paddingY), Point3D.p01, Point3D.p11, Point3D.p10, Point3D.p00);
		glEnd();
		glPopAttrib();
		glDisable(GL_TEXTURE_2D);
	}
	
	private static void actionArgs(Object[] args){
		for (Object arg : args) {
			if (arg instanceof Color) {
				setColor(((Color) arg).getRed(), ((Color) arg).getGreen(), ((Color) arg).getBlue());
			}
		}
	}

	public static void renderString(char[] str, Box box, Object... args) {
		actionArgs(args);
		Texture[] texes = new Texture[str.length];
		int sumWidth = 0;
		int sumHeight = 0;
		for (int i = 0, len = str.length; i < len; i++) {
			texes[i] = Main.stringManager.get(str[i]);
			sumWidth += texes[i].width;
			sumHeight += texes[i].height;
		}
		glEnable(GL_TEXTURE_2D);
		float paddingX = box.width / 2 - sumWidth / 2;
		float paddingY = box.height / 2 - sumHeight / str.length / 2;
		glPushAttrib(GL_TEXTURE_BINDING_2D);
		int offsetX = 0;
		for (Texture tex : texes) {
			tex.bind();
			glBegin(GL_QUADS);
			renderTexturedPlane(new Point3D(box.x + paddingX + offsetX, box.y + paddingY), new Point3D(box.x + paddingX + offsetX + tex.width, box.y + paddingY), new Point3D(box.x + paddingX + offsetX + tex.width, box.y + tex.height), new Point3D(box.x + paddingX + offsetX, box.y + tex.height), Point3D.p01, Point3D.p11, Point3D.p10, Point3D.p00);
			glEnd();
			offsetX += tex.width;
		}
		glPopAttrib();
		glDisable(GL_TEXTURE_2D);
	}
	
	public static Point3D getScale(Box target, Box frame){
		return null;
	}

	public static void set3D() {
		if (isUpdateWindowSize || renderMode != mode3D) {
			isUpdateWindowSize = false;
			renderMode = mode3D;
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			gluPerspective(fovy, Main.windowManager.getAspect(), zNear, zFar);
			glMatrixMode(GL_MODELVIEW);
			glLoadIdentity();
			glEnable(GL_DEPTH_TEST);
			glDepthFunc(GL_LEQUAL);
			glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		}
	}

	public static void set2D() {
		if (isUpdateWindowSize || renderMode != mode2D) {
			isUpdateWindowSize = false;
			renderMode = mode2D;
			glClearColor(0, 0, 0, 0);
			glClearDepth(1);
			glViewport(0, 0, Main.windowManager.width, Main.windowManager.height);
			glMatrixMode(GL_MODELVIEW);
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0, Main.windowManager.width, 0, Main.windowManager.height, 1, -1);
			glMatrixMode(GL_MODELVIEW);
			glLoadIdentity();
		}
	}

}
