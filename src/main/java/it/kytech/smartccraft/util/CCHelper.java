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
package it.kytech.smartccraft.util;

import dan200.computercraft.api.turtle.ITurtleAccess;
import net.minecraft.tileentity.TileEntity;

import java.lang.reflect.Method;

/**
 * Created by Hitech95 on 03/10/2015.
 */
public class CCHelper {

    public static ITurtleAccess getTurtle(TileEntity te) throws Exception {
        if (te instanceof ITurtleAccess)
            return (ITurtleAccess) te;
        Class teClass = te.getClass();
        if (teClass.getName().equals("dan200.computercraft.shared.turtle.blocks.TileTurtleExpanded") || teClass.getName().equals("dan200.computercraft.shared.turtle.blocks.TileTurtleAdvanced") || teClass.getName().equals("dan200.computercraft.shared.turtle.blocks.TileTurtle")) {
            Method getAccess = teClass.getMethod("getAccess", new Class[0]);
            ITurtleAccess turtle = (ITurtleAccess) getAccess.invoke(te, new Object[0]);
            if (turtle != null)
                return turtle;
        }
        return null;
    }
}
