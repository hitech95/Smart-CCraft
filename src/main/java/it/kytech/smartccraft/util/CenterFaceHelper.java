package it.kytech.smartccraft.util;

/**
 * Created by Hitech95 on 07/10/2015.
 */
public enum CenterFaceHelper {
    /**
     * -Y
     */
    DOWN(0.5, -0.1, +.5),

    /**
     * +Y
     */
    UP(0.5, 1.1, 0.5),

    /**
     * -Z
     */
    NORTH(0.5, 0.5, -0.1),

    /**
     * +Z
     */
    SOUTH(0.5, 0.5, 1.1),

    /**
     * -X
     */
    WEST(-0.1, 0.5, 0.5),

    /**
     * +X
     */
    EAST(1.1, 0.5, 0.5),

    /**
     * Used only by getOrientation, for invalid inputs
     */
    UNKNOWN(0, 0, 0);

    public final double offsetX;
    public final double offsetY;
    public final double offsetZ;
    public static final CenterFaceHelper[] VALID_DIRECTIONS = {DOWN, UP, NORTH, SOUTH, WEST, EAST};

    private CenterFaceHelper(double x, double y, double z) {
        offsetX = x;
        offsetY = y;
        offsetZ = z;
    }

    public static CenterFaceHelper getFace(int id) {
        if (id >= 0 && id < VALID_DIRECTIONS.length) {
            return VALID_DIRECTIONS[id];
        }
        return UNKNOWN;
    }
}
