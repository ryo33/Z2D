package com.ryo33.z2d.client.manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.ryo33.z2d.util.Texture;

public class StringManager {

	public static int fontSize = 24;

	private HashMap<Character, Texture> characters;
	private HashMap<String, Texture> strings;

	public StringManager() {
		characters = new HashMap<Character, Texture>();
		strings = new HashMap<String, Texture>();
	}

	public Texture get(char c) {
		Texture tex = characters.get(c);
		if (tex == null) {
			tex = create(c);
			characters.put(c, tex);
		}
		return tex;
	}

	public Texture get(String str) {
		Texture tex = strings.get(str);
		if (tex == null) {
			tex = create(str);
			strings.put(str, tex);
		}
		return tex;
	}

	public Texture create(char c) {
		return create(((Character)c).toString());
	}

	public Texture create(String str) {
		BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D graphics = image.createGraphics();
		Font font = new Font(Font.MONOSPACED, Font.PLAIN, fontSize);
		graphics.setFont(font);
		Rectangle2D rect = graphics.getFontMetrics().getStringBounds(str, graphics);
		image = new BufferedImage((int)Math.ceil(rect.getWidth()), (int)Math.ceil(rect.getHeight()), BufferedImage.TYPE_4BYTE_ABGR); 
		graphics = image.createGraphics();
		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		graphics.setColor(new Color(0F, 0F, 0F, 0F));
		graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
		graphics.setFont(font);		
		graphics.setColor(new Color(1F, 1F, 1F, 1F));
		graphics.drawString(str, 0, graphics.getFontMetrics().getAscent());
//		try {
//			ImageIO.write(image, "png", new File(str + ".png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return new Texture(image);
	}
	
}
