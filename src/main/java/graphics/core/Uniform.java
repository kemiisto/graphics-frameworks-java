package graphics.core;

import static org.lwjgl.opengl.GL30.*;

import graphics.math.Matrix;
import graphics.math.Vector;

public class Uniform<T> {
    // store name of GLSL type:
    // int | bool | float | vec2 | vec3 | vec4
    private String dataType;

    // data to be sent to uniform variable
    public T data;

    // store results of generating buffers
    private int[] resultArray = new int[1];

    // reference for variable location in program
    private int variable;

    public Uniform(String dataType, T data) {
        this.dataType = dataType;
        this.data = data;
    }

    // get and store reference for program variable with name
    public void locateVariable(int program, String variableName) {
        variable = glGetUniformLocation(program, variableName);
    }

    // store data in uniform variable previously located
    public void uploadData() {
        if (variable == -1) {
            return;
        }

        switch (dataType) {
            case "int" -> glUniform1i(variable, (Integer) data);
            case "bool" -> glUniform1i(variable, (Integer) data);
            case "float" -> glUniform1f(variable, (Float) data);
            case "vec2" -> {
                Vector v = (Vector) data;
                glUniform2f(variable,
                    (float)v.values[0],
                    (float)v.values[1]
                );
            }
            case "vec3" -> {
                Vector v = (Vector) data;
                glUniform3f(variable,
                    (float)v.values[0],
                    (float)v.values[1],
                    (float)v.values[2]
                );
            }
            case "vec4" -> {
                Vector v = (Vector) data;
                glUniform4f(variable,
                    (float)v.values[0],
                    (float)v.values[1],
                    (float)v.values[2],
                    (float)v.values[3]
                );
            }
            case "mat4" -> {
                Matrix m = (Matrix)data;
                glUniformMatrix4fv(variable, true, m.flatten());
            }
        }
    }
}
