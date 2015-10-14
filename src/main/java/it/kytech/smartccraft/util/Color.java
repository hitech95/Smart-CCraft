package it.kytech.smartccraft.util;

/**
 * Created by Hitech95 on 14/10/2015.
 */
public class Color {
    private float red;
    private float green;
    private float blue;
    private float alpha;

    public Color(float red, float green, float blue){
        this(red, green, blue, 1F);
    }

    public Color(float red, float green, float blue, float alpha){
        this.red = red % 1F;
        this.green = green % 1F;
        this.blue = blue % 1F;
        this.alpha = alpha % 1F;
    }

    public float getRed() {
        return red;
    }

    public float getGreen() {
        return green;
    }

    public float getBlue() {
        return blue;
    }

    public float getAlpha() {
        return alpha;
    }

    @Override
    public String toString() {
        return "Color{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                ", alpha=" + alpha +
                '}';
    }
}
