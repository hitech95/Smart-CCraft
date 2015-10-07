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
package dan200.computercraft.api.media;

import net.minecraft.item.ItemStack;

/**
 * This interface is used to provide IMedia implementations for ItemStack
 * @see dan200.computercraft.api.ComputerCraftAPI#registerMediaProvider(IMediaProvider)
 */
public interface IMediaProvider
{
    /**
     * Produce an IMedia implementation from an ItemStack.
     * @see dan200.computercraft.api.ComputerCraftAPI#registerMediaProvider(IMediaProvider)
     * @return an IMedia implementation, or null if the item is not something you wish to handle
     */
    public IMedia getMedia( ItemStack stack );
}
