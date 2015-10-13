/**
 * This file is part of SmartCCraft
 *
 * Copyright (c) 2015 hitech95 <https://github.com/hitech95>
 * Copyright (c) contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.kytech.smartccraft.util.helper;

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
