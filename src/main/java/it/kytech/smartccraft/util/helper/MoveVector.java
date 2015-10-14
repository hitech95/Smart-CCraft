package it.kytech.smartccraft.util.helper;

/**
 * Created by Hitech95 on 14/10/2015.
 */
public class MoveVector {
    /**
     * X coordinate of Vec3D
     */
    public double xMove;
    /**
     * Y coordinate of Vec3D
     */
    public double yMove;
    /**
     * Z coordinate of Vec3D
     */
    public double zMove;

    public MoveVector(double x, double y, double z) {
        if (x == -0.0D) {
            x = 0.0D;
        }

        if (y == -0.0D) {
            y = 0.0D;
        }

        if (z == -0.0D) {
            z = 0.0D;
        }

        this.xMove = x;
        this.yMove = y;
        this.zMove = z;
    }

    /**
     * Sets the x,y,z components of the vector as specified.
     */
    public MoveVector setComponents(double x, double y, double z) {
        this.xMove = x;
        this.yMove = y;
        this.zMove = z;
        return this;
    }

    public MoveVector multiplyComponents(double x, double y, double z) {
        this.xMove *= x;
        this.yMove *= y;
        this.zMove *= z;
        return this;
    }

    public double getxMove() {
        return xMove;
    }

    public double getyMove() {
        return yMove;
    }

    public double getzMove() {
        return zMove;
    }
}
