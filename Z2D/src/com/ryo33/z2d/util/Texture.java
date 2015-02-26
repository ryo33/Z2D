package com.ryo33.z2d.util;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;

public class Texture {

	public int id;
	public int width, height;

	public Texture(BufferedImage image) {
		this(image, image.getWidth(), image.getHeight());
	}

	public Texture(ByteBuffer buffer, int width, int height, boolean hasAlpha) {
		this.id = getID(buffer, this.width = width, this.height = height, hasAlpha);
	}

	public Texture(BufferedImage image, int width, int height) {
		this.width = width;
		this.height = height;
		boolean hasAlpha = image.getColorModel().hasAlpha();
//		hasAlpha = true;
		int bytes = hasAlpha ? 4 : 3;
		ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * bytes);
		int[] pixels = new int[width * height * bytes];
		image.getRGB(0, 0, width, height, pixels, 0, width);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int pixel = pixels[y * width + x];
				buffer.put((byte) ((pixel >> 16) & 0xFF));
				buffer.put((byte) ((pixel >> 8) & 0xFF));
				buffer.put((byte) (pixel & 0xFF));
				if (hasAlpha) {
					buffer.put((byte) ((pixel >> 24) & 0xFF));
				}
			}
		}
		buffer.flip();
		this.id = getID(buffer, width, height, hasAlpha);
	}

	private static int getID(ByteBuffer buffer, int width, int height, boolean hasAlpha) {
		int id = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, id);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		glTexImage2D(GL_TEXTURE_2D, 0, hasAlpha ? GL_RGBA : GL_RGB, width, height, 0, hasAlpha ? GL_RGBA : GL_RGB, GL_UNSIGNED_BYTE, buffer);
		return id;
	}

	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}

}
