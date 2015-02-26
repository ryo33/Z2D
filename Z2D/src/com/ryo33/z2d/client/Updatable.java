package com.ryo33.z2d.client;

public abstract interface Updatable {
	int Nothing = 0, Next = 1;
	
    int update();
    
    void render();

    void eventKey(int key, int scancode, int action, int mods);

    void eventMouseButton(int button, int action, int mods);

	void eventCursorPos(double xpos, double ypos);
	
	void eventWindowSize();
}