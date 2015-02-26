package com.ryo33.z2d.client.component;

import com.ryo33.z2d.client.helper.LayoutBox;

public class Selector extends Component {

	@Override
	public void redoLayout(LayoutBox parent) {
		super.redoLayout(parent);
	}

	@Override
	public void eventMouseButton(int button, int action, int mods) {
		// TODO Auto-generated method stub
		super.eventMouseButton(button, action, mods);
	}

	public Selector(LayoutBox box, String[] names) {
		this(box, names, 0);
	}

	public Selector(LayoutBox box, String[] names, int active) {
		super(box);
	}

	@Override
	public void render() {
		super.render();

	}

	protected class SelectorButton extends Button {

		public SelectorButton(String text, LayoutBox box) {
			super(text, box);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void redoLayout(LayoutBox parent) {
			super.redoLayout(parent);
		}

		@Override
		public void render() {
			super.render();
		}

	}

}