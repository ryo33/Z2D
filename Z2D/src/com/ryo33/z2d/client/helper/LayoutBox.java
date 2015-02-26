package com.ryo33.z2d.client.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import com.ryo33.z2d.util.Box;

public class LayoutBox extends Box {
	public enum Origin {
		nothing, gridLayout, createInnerBox, createOuterBox, adjoiningBox
	}

	public static final int top = 0, right = 1, bottom = 2, left = 3;
	public ArrayList<LayoutBox> children;

	private Origin origin;// used to redoLayout
	private Object[] args;// used with origin

	public LayoutBox(int x, int y, int width, int height, Origin origin, Object... args) {
		super(x, y, width, height);
		this.origin = origin;
		this.args = args;
		this.children = new ArrayList<LayoutBox>();
	}

	public LayoutBox(int x, int y, int width, int height) {
		this(x, y, width, height, Origin.nothing);
	}
	
	public LayoutBox(){
		this(0, 0, 0, 0);
	}

	public LayoutBox(LayoutBox box) {
		place(box);
	}

	public void place(LayoutBox box) {
		this.x = box.x;
		this.y = box.y;
		this.width = box.width;
		this.height = box.height;
		this.origin = box.origin;
		this.args = box.args;
		this.children = box.children;
	}

	public void removeOrigin() {
		this.origin = Origin.nothing;
	}

	public void redoLayout(LayoutBox parent) {
		switch (origin) {
		case gridLayout:
			parent.doGridLayout(this, (int) args[0], (int) args[1], (int) args[2], (int) args[3], (int) args[4], (int) args[5]);
			break;
		case createInnerBox:
			parent.makeInnerBox(this, (int[]) args[0]);
			break;
		case createOuterBox:
			parent.makeOuterBox(this, (int[]) args[0]);
			break;
		case adjoiningBox:
			parent.makeAdjoiningBox(this, (int) args[0], (int) args[1], (int) args[2]);
			break;
		case nothing:
			break;
		}
		for (Iterator<LayoutBox> iterator = children.iterator(); iterator.hasNext();) {
			iterator.next().redoLayout(this);
		}
	}

	public LayoutBox doGridLayout(LayoutBox target, int rows, int lines, int x, int y, int width, int height) {
		int baseX = this.width / rows, baseY = this.height / lines;
		target.x = baseX * x + this.x;
		target.y = baseY * y + this.y;
		target.width = baseX * width;
		target.height = baseY * height;
		target.origin = Origin.gridLayout;
		target.args = new Object[] { rows, lines, x, y, width, height };
		return target;
	}

	public LayoutBox makeInnerBox(LayoutBox target, int... paddings) {
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
		target.x = x + paddingLeft;
		target.y = y + paddingBottom;
		target.width = width - (paddingRight + paddingLeft);
		target.height = height - (paddingTop + paddingBottom);
		target.origin = Origin.createInnerBox;
		target.args = Arrays.asList(paddings).stream().toArray();
		return target;
	}

	public LayoutBox makeOuterBox(LayoutBox target, int... margins) {
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
		target.x = x - marginLeft;
		target.y = y - marginBottom;
		target.width = width + (marginRight + marginLeft);
		target.height = height + (marginTop + marginBottom);
		target.origin = Origin.createOuterBox;
		target.args = Arrays.asList(margins).stream().toArray();
		return target;
	}

	public LayoutBox makeAdjoiningBox(LayoutBox target, int direction, int size, int spacing) {
		target.x = this.x;
		target.y = this.y;
		target.width = this.width;
		target.height = this.height;
		target.origin = Origin.adjoiningBox;
		target.args = new Object[] { direction, size, spacing };
		switch (direction) {
		case top:
			target.height = size;
			target.y += this.height + spacing;
			break;
		case right:
			target.width = size;
			target.x += this.width + spacing;
			break;
		case bottom:
			target.height = size;
			target.y -= target.height + spacing;
			break;
		case left:
			target.width = size;
			target.x -= target.width + spacing;
			break;
		}
		return target;
	}

	public LayoutBox clone(LayoutBox target) {
		target.place(this);
		return target;
	}

	public void add(LayoutBox... boxes) {
		for (LayoutBox box : boxes) {
			children.add(box);
		}
	}

}