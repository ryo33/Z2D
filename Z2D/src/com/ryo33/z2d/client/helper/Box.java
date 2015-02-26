package com.ryo33.z2d.client.helper;

import com.ryo33.z2d.Main;

public class Box {
	protected enum Origin {
		nothing, gridLayout, createInnerBox, createOuterBox, adjoiningBox,
	}
	public static final int top = 0, right = 1, bottom = 2, left = 3;

	public int x, y, width, height;
	protected Origin origin;//used to redoLayout
	protected Object[] args;//used with origin

	public Box(int baseX, int baseY, int x, int y, int width, int height, Origin origin, Object... args) {
		this.x = baseX * x;
		this.y = baseY * y;
		this.width = baseX * width;
		this.height = baseY * height;
		this.origin = origin;
		this.args = args;
	}

	public Box(int x, int y, int width, int height, Origin origin, Object... args) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.origin = origin;
		this.args = args;
	}

	public Box(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.origin = Origin.nothing;
	}

	public void place(Box box){
		this.x = box.x;
		this.y = box.y;
		this.width = box.width;
		this.height = box.height;
		this.origin = box.origin;
		this.args = box.args;
	}
	
	public void removeOrigin(){
		this.origin = Origin.nothing;
	}

	public void redoLayout() {
		switch (origin) {
		case gridLayout:
			place(doGridLayout((int) args[0], (int) args[1], (int) args[2], (int) args[3], (int) args[4], (int) args[5]));
			break;
		case createInnerBox:
			place(((Box) args[0]).createInnerBox((int[]) args[1]));
			break;
		case createOuterBox:
			place(((Box) args[0]).createOuterBox((int[]) args[1]));
			break;
		case adjoiningBox:
			place(((Box) args[0]).createAdjoiningBox((int)args[1], (int)args[2], (int)args[3]));
			break;
		default:
			break;
		}
	}

	public static Box doGridLayout(int rows, int lines, int x, int y, int width, int height) {
		return new Box(Main.windowManager.width / rows, Main.windowManager.height / lines, x, y, width, height, Origin.gridLayout, rows, lines, x, y, width, height);
	}

	public Box createInnerBox(int... paddings) {
		int paddingTop = 0, paddingRight = 0, paddingBottom = 0, paddingLeft = 0;
		switch (paddings.length) {
		case 0:
			break;
		case 1:
			paddingTop = paddingRight = paddingBottom = paddingLeft = paddings[0];
			break;
		case 2:
			paddingTop = paddingBottom = paddings[0];
			paddingRight = paddingLeft = paddings[1];
			break;
		case 3:
			paddingTop = paddings[0];
			paddingRight = paddingLeft = paddings[1];
			paddingBottom = paddings[2];
			break;
		default:
			paddingTop = paddings[0];
			paddingRight = paddings[1];
			paddingBottom = paddings[2];
			paddingLeft = paddings[3];
			break;
		}
		return new Box(x + paddingLeft, y + paddingBottom, width - (paddingRight + paddingLeft), height - (paddingTop + paddingBottom), Origin.createInnerBox, this, paddings);
	}

	public Box createOuterBox(int... margins) {
		int marginTop = 0, marginRight = 0, marginBottom = 0, marginLeft = 0;
		switch (margins.length) {
		case 0:
			break;
		case 1:
			marginTop = marginRight = marginBottom = marginLeft = margins[0];
			break;
		case 2:
			marginTop = marginBottom = margins[0];
			marginRight = marginLeft = margins[1];
			break;
		case 3:
			marginTop = margins[0];
			marginRight = marginLeft = margins[1];
			marginBottom = margins[2];
			break;
		default:
			marginTop = margins[0];
			marginRight = margins[1];
			marginBottom = margins[2];
			marginLeft = margins[3];
			break;
		}
		return new Box(x - marginLeft, y - marginBottom, width + (marginRight + marginLeft), height + (marginTop + marginBottom), Origin.createOuterBox, this, margins);
	}
	
	public Box createAdjoiningBox(int direction, int size, int spacing){
		Box box = new Box(x, y, width, height, Origin.adjoiningBox, this, direction, size, spacing);
		switch(direction){
		case top:
			box.height = size;
			box.y += this.height + spacing;
			break;
		case right:
			box.width = size;
			box.x += this.width + spacing;
			break;
		case bottom:
			box.height = size;
			box.y -= box.height + spacing;
			break;
		case left:
			box.width = size;
			box.x -= box.width + spacing;
			break;
		}
		return box;
	}
}