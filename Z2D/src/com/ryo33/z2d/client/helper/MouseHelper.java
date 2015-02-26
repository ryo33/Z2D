package com.ryo33.z2d.client.helper;

import org.lwjgl.glfw.GLFW;

public class MouseHelper{

    public static void setDisable(long window){
        GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
    }
    
    public static void setNormal(long window){
        GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
    }
    
}
