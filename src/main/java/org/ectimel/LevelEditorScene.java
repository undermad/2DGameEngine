package org.ectimel;

import org.ectimel.render.Shader;
import org.ectimel.utils.TimeConverter;
import org.joml.Vector2f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.file.Path;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class LevelEditorScene extends Scene {


    private int vaoID, eboID, vboID;
    private Shader defaultShader;

    private float[] vertexArray = {
            //position xyz      //color rgba
            100.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, // bottom right
            0.0f, 100.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, // top left
            100.0f, 100.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, // top right
            0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, // bottom left
    };

    // IMPORTANT: Must be in anti-clockwise order
    private int[] elementArray = {
            2, 1, 0, // top right triangle
            0, 1, 3 // bottom left triangle
    };


    public LevelEditorScene() {
    }

    @Override
    public void update(float deltaTime) {
        this.camera.position.x -= deltaTime * 50.0f;
        this.camera.position.y -= deltaTime * 50.0f;

        defaultShader.use();
        defaultShader.uploadMat4f("uProjection", camera.getProjectionMatrix());
        defaultShader.uploadMat4f("uView", camera.getViewMatrix());
        defaultShader.uploadFloat("uTime", 5);

        // Bind the VAO that we're using
        glBindVertexArray(vaoID);

        // Enable the vertex attribute pointers
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);




        //Unbind everything
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        glBindVertexArray(0);

        defaultShader.detach();
    }

    @Override
    public void init() {
        this.camera = new Camera(new Vector2f());

        defaultShader = new Shader(Path.of(".\\assets\\shaders\\default.glsl"));
        defaultShader.compileAndLink();

        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        // Create float buffer or vertices
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
        vertexBuffer.put(vertexArray).flip();


        // Create VBO upload the vertex buffer
        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        // Create the indices and upload
        IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
        elementBuffer.put(elementArray).flip();

        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);

        // Add the vertex attribute pointers
        int positionSize = 3;
        int colorSize = 4;
        int floatSizeBytes = 4;
        int vertexSizeBytes = (positionSize + colorSize) * floatSizeBytes;
        glVertexAttribPointer(0, positionSize, GL_FLOAT, false, vertexSizeBytes, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, positionSize * floatSizeBytes);
        glEnableVertexAttribArray(1);


    }
}
