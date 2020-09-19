package MyGame;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;

public class GameEngine implements Runnable{
    private Window window;
    private IGameLogic gameLogic;

    public GameEngine(String title,int width,int height,boolean vSync,IGameLogic gameLogic){
        this.window = new Window(title,width,height,vSync);
        this.gameLogic = gameLogic;
    }

    public void run() {
        try{
            init();
            loop();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void render(){
        gameLogic.render(window);
    }

    public void update(float interval){
        gameLogic.update(interval);
    }

    public void input(){
        gameLogic.input(window);
    }

    private void init() throws Exception {
        window.init();
        gameLogic.init();
    }

    private void loop(){
        GL.createCapabilities();
        GLFW.glfwSwapInterval(2);
        GLFW.glfwSwapBuffers(window.getWindowHandle());
        while (!glfwWindowShouldClose(window.getWindowHandle())) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer


            glfwSwapBuffers(window.getWindowHandle()); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();

            render();
        }
    }

}
