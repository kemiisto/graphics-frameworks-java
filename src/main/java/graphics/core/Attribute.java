package graphics.core;

import static org.lwjgl.opengl.GL30.*;

public class Attribute {
    // type of elements in data array:
    // int | float | vec2 | vec3 | vec4
    private String dataType;

    // array of data to be stored in buffer
    public float[] dataArray;

    // store results of generating buffers
    private int[] resultArray = new int[1];

    // reference of available buffer from GPU
    private int buffer;

    public Attribute(String dataType, float[] dataArray) {
        this.dataType = dataType;
        this.dataArray = dataArray;
        buffer = glGenBuffers();
        uploadData();
    }

    public void uploadData() {
        glBindBuffer(GL_ARRAY_BUFFER, buffer);
        glBufferData(GL_ARRAY_BUFFER, dataArray, GL_STATIC_DRAW);
    }

    public void associateVariable(int program, String variableName) {
        int variable = glGetAttribLocation(program, variableName);
        if (variable == -1) {
            return;
        }

        glBindBuffer(GL_ARRAY_BUFFER, buffer);

        switch (dataType) {
            case "int" -> glVertexAttribPointer(variable, 1, GL_INT, false, 0, 0);
            case "float" -> glVertexAttribPointer(variable, 1, GL_FLOAT, false, 0, 0);
            case "vec2" -> glVertexAttribPointer(variable, 2, GL_FLOAT, false, 0, 0);
            case "vec3" -> glVertexAttribPointer(variable, 3, GL_FLOAT, false, 0, 0);
            case "vec4" -> glVertexAttribPointer(variable, 4, GL_FLOAT, false, 0, 0);
            default -> throw new RuntimeException("Attribute " + variableName + " has unknown type " + dataType);
        }

        glEnableVertexAttribArray(variable);
    }
}
