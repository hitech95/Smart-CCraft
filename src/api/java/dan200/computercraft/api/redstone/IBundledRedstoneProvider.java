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
package dan200.computercraft.api.redstone;

import net.minecraft.world.World;

/**
 * This interface is used to provide bundled redstone output for blocks
 * @see dan200.computercraft.api.ComputerCraftAPI#registerBundledRedstoneProvider(IBundledRedstoneProvider)
 */
public interface IBundledRedstoneProvider
{
    /**
     * Produce an bundled redstone output from a block location.
     * @see dan200.computercraft.api.ComputerCraftAPI#registerBundledRedstoneProvider(IBundledRedstoneProvider)
     * @return a number in the range 0-65535 to indicate this block is providing output, or -1 if you do not wish to handle this block
     */
    public int getBundledRedstoneOutput( World world, int x, int y, int z, int side );
}
