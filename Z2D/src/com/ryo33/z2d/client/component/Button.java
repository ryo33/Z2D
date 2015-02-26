package com.ryo33.z2d.client.component;

import static com.ryo33.z2d.client.helper.RenderHelper.*;

import com.ryo33.z2d.client.helper.Box;

public class Button extends Component{
	
	public String text;
	public Box frameBox;
	public Box textBox;

	public Button(String text, Box box) {
		super(box);
		frameBox = box.createOuterBox(2);
		textBox = box.createInnerBox(5);
		this.text = text;
	}
	
	@Override
	public void redoLayout() {
		super.redoLayout();
		frameBox.redoLayout();
		textBox.redoLayout();
	}

	@Override
	public void render() {
		setColor(0.6F, 0.6F, 0.6F);
			renderBox(frameBox, 0);
		if(isEnter){
			setColor(1, 1, 1);
			renderBox(box, 0);
		}else{
			setColor(0, 1, 1);
			renderBox(box, 0F);
		}
		setColor(0, 0, 0);
		renderString(text.toCharArray(), textBox, -0.1F);
	}

}
