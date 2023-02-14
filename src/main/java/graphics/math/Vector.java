package graphics.math;

public class Vector {
    public double[] values;

    public Vector(int size) {
        values = new double[size];
    }

    public Vector(double... v) {
        values = new double[v.length];
        System.arraycopy(v, 0, values, 0, v.length);
    }

    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (double value : values) {
            s.append(String.format("%6.2f", value));
        }
        s.append("]");
        return s.toString();
    }
}
