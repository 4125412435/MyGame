package MyGame;

import org.lwjgl.glfw.GLFW;

public class DummyGame implements IGameLogic {

    private int direction = 1;

    private float color = 0.0F;

    private final Renderer renderer;

    public DummyGame(){
        this.renderer = new Renderer();
    }

    @Override
    public void init() throws Exception {
        renderer.init();
    }

    @Override
    public void input(Window window) {
        if(window.isKeyPress(GLFW.GLFW_KEY_UP)) {
            direction = 1;
        }
        else if(window.isKeyPress(GLFW.GLFW_KEY_DOWN)){
            direction = -1;
        }
        else{
            direction = 0;
        }
    }

    @Override
    public void update(float interval) {
        color += 0.01*direction;
        if(color>1){
            color = 1F;
        }
        else if(color<0){
            color = 0F;
        }
    }

    @Override
    public void render(Window window) {
        if(window.isReSized()){
            org.lwjgl.opengl.GL11C.glViewport(0,0,window.getWidth(),window.getHeight());
            window.setReSized(false);
        }

        renderer.clear();
    }

    @Override
    public void cleanup() {

    }
}
