package MyGame;

import org.lwjgl.glfw.GLFW;

public class MainGame {
    public void gameLoop(){
        while (true){
            GLFW.glfwSwapInterval(2);
        }
    }
}
