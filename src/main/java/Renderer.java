import org.lwjgl.opengl.GL11C;

public class Renderer {
    private ShaderProgram shaderProgram;

    public void init() throws Exception{
        shaderProgram = new ShaderProgram();
        //shaderProgram.createVertexShader(Utils);
    }

    public void clear(){
        org.lwjgl.opengl.GL11C.glClear(GL11C.GL_COLOR_BUFFER_BIT|GL11C.GL_DEPTH_BUFFER_BIT);
    }
}
