package MyGame;

import org.lwjgl.glfw.*;

import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_FORWARD_COMPAT;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.opengl.GL11.GL_TRUE;

public class Window {
    public static void main(String[] args) throws InterruptedException {
        GameEngine gameEngine = new GameEngine("123",200,200,true,new DummyGame());
        gameEngine.run();
}
    private Long windowHandle;
    private int width;
    private int height;
    private boolean reSized;
    private boolean vSync;
    private String title;

    public Window(String title,int width,int height,boolean vSync){
       this.title = title;
       this.width = width;
       this.height = height;
       this.vSync = vSync;
    }

    public void init(){
        GLFWErrorCallback.createPrint(System.err).set();

        if(!GLFW.glfwInit()){
            System.out.println("GLFW has not been Initialized");
            return;
        }

        if(windowHandle==null){

        }else {
            GLFW.glfwDestroyWindow(windowHandle);
        }
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE,GLFW.GLFW_TRUE);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR,3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR,2);

        windowHandle = GLFW.glfwCreateWindow(width,height,title,0,0);

        if(windowHandle==0){
            System.out.println("MyGame.Window is Null");
        }

        GLFW.glfwMakeContextCurrent(windowHandle);
        GLFW.glfwShowWindow(windowHandle);
        //没有这段代码 界面会闪退 与下面的内容无关
        GLFW.glfwSetKeyCallback(windowHandle,(window, key, scancode, action, mods)-> {
            System.out.println("Key "+key+" Press");
                if(key==GLFW.GLFW_KEY_ESCAPE&&action==GLFW_RELEASE){
                    GLFW.glfwSetWindowShouldClose(window,true);
                }
        });

        setFramebufferSizeCallback();
    }

    public boolean isKeyPress(int keycode){
        return GLFW.glfwGetKey(windowHandle,keycode)==GLFW.GLFW_PRESS;
    }

    public void setFramebufferSizeCallback(){
        GLFW.glfwSetFramebufferSizeCallback(windowHandle, new GLFWFramebufferSizeCallback() {

            @Override
            public GLFWFramebufferSizeCallback set(long window) {
                return super.set(window);
            }

            @Override
            public String getSignature() {
                return null;
            }

            @Override
            public void callback(long args) {

            }

            @Override
            public void close() {

            }

            @Override
            public void invoke(long l, int i, int i1) {
                width = i;
                height = i1;
            }
        });
    }

    public void setReSized(boolean reSized){
        this.reSized = reSized;
    }

    public boolean isReSized() {
        return reSized;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public long getWindowHandle() {
        return windowHandle;
    }
}
