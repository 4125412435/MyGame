import org.lwjgl.glfw.GLFW;

public class GameEngine implements Runnable{
    private Window window;
    private IGameLogic gameLogic;

    public GameEngine(String title,int width,int height,boolean vSync,IGameLogic gameLogic){
        this.window = new Window(title,width,height,vSync);
        this.window.init();
        this.gameLogic = gameLogic;
    }

    public void run() {
        try{
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

    private void loop(){
        GLFW.glfwSwapInterval(2);
        GLFW.glfwSwapBuffers(window.getWindowHandle());
    }

}
