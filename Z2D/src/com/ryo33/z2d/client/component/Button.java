package com.ryo33.z2d.client.component;

import static com.ryo33.z2d.client.helper.RenderHelper.*;

import com.ryo33.z2d.client.helper.LayoutBox;

public class Button extends Component {

	public String text;
	public LayoutBox frameBox;
	public LayoutBox textBox;

	public Button(String text, LayoutBox box) {
		this(box);
		this.text = text;
	}

	public Button(LayoutBox box) {
		super(box);
		frameBox = this.makeOuterBox(new LayoutBox(), 2);
		textBox = this.makeInnerBox(new LayoutBox(), 5);
		this.add(frameBox, textBox);
	}

	@Override
	public void render() {
		super.render();
		setColor(0.6F, 0.6F, 0.6F);
		renderBox(frameBox);
		if (isEnter() && isEnabled()) {
			setColor(1, 1, 1);
			renderBox(this);
		} else {
			setColor(0, 1, 1);
			renderBox(this);
		}
		setColor(0, 0, 0);
		renderString(text, textBox);
	}

}
