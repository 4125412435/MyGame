package MyGame;

import org.lwjgl.opengl.*;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20C.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;

public class Renderer {
    private int vboId;
    private int vaoId;
    private ShaderProgram shaderProgram;

    public void init() throws Exception{
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(Utils.loadResource("/vertex.vs"));
        shaderProgram.createFragmentShader(Utils.loadResource("/fragment.fs"));
        shaderProgram.link();
        a();
    }

    private void a(){
        float[] vertices = new float[]{
                0.0f,0.5f,0.0f
                -0.5f,-0.5f,0.0f,
                0.5f,-0.5f,0.0f
        };

        FloatBuffer verticesBuffer = MemoryUtil.memAllocFloat(vertices.length);
        verticesBuffer.put(vertices).flip();

        vaoId = GL40.glGenVertexArrays();
        glBindVertexArray(vaoId);

        float[] floats = new float[9];
        GL40.glGetBufferSubData(vaoId,GL_ARRAY_BUFFER,floats);
        for (float aFloat : floats) {
            System.out.println(aFloat);
        }

        vboId = GL40.glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER,vboId);
        GL40.glBufferData(GL_ARRAY_BUFFER,verticesBuffer,GL15.GL_STATIC_DRAW);
        GL40.glEnableVertexAttribArray(0);
        GL40.glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
        if(verticesBuffer!=null){
            MemoryUtil.memFree(verticesBuffer);
        }
    }

    public void render(Window window){
        clear();

        if(window.isReSized()){
            GL11C.glViewport(0,0,window.getWidth(),window.getHeight());
            window.setReSized(false);
        }

        shaderProgram.bind();

        glBindVertexArray(vaoId);
        GL40.glDrawArrays(GL_TRIANGLES, 0, 3);
        glBindVertexArray(0);

        shaderProgram.unbind();
    }

    public void clear(){
        org.lwjgl.opengl.GL11C.glClear(GL11C.GL_COLOR_BUFFER_BIT|GL11C.GL_DEPTH_BUFFER_BIT);
    }

    public void cleanup(){
        shaderProgram.cleanup();
        glDisableVertexAttribArray(0);

        // Delete the VBO
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(vboId);

        // Delete the VAO
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoId);
    }
}
