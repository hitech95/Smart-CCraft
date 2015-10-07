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
package dan200.computercraft.api.turtle;

import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * The primary interface for defining an turtle for Turtles. A turtle turtle
 * can either be a new tool, or a new peripheral.
 * @see dan200.computercraft.api.ComputerCraftAPI#registerTurtleUpgrade( dan200.computercraft.api.turtle.ITurtleUpgrade )
 */
public interface ITurtleUpgrade
{
	/**
	 * Gets a unique numerical identifier representing this type of turtle turtle.
	 * Like Minecraft common and item IDs, you should strive to make this number unique
	 * among all turtle turtle that have been released for ComputerCraft.
	 * The ID must be in the range 64 to 255, as the ID is stored as an 8-bit value,
	 * and 0-64 is reserved for future use by ComputerCraft. The turtle will
	 * fail registration if an already used ID is specified.
	 * @see dan200.computercraft.api.ComputerCraftAPI#registerTurtleUpgrade( dan200.computercraft.api.turtle.ITurtleUpgrade )
	 */
	public int getUpgradeID();
	
	/**
	 * Return a String to describe this type of turtle in turtle item names.
	 * Examples of built-in adjectives are "Wireless", "Mining" and "Crafty".
	 */	
	public String getUnlocalisedAdjective();

	/**
	 * Return whether this turtle adds a tool or a peripheral to the turtle.
	 * Currently, turtle crafting is restricted to one tool & one peripheral per turtle.
	 * @see TurtleUpgradeType for the differences between the two.
	 */	
	public TurtleUpgradeType getType();
	
	/**
	 * Return an item stack representing the type of item that a turtle must be crafted
	 * with to create a turtle which holds this turtle.
	 * Currently, turtle crafting is restricted to one tool & one peripheral per turtle.
	 */		
	public ItemStack getCraftingItem();

    /**
	 * Will only be called for Peripheral turtle. Creates a peripheral for a turtle
	 * being placed using this turtle. The peripheral created will be stored
	 * for the lifetime of the turtle, will have update() called once-per-tick, and will be
	 * attach'd detach'd and have methods called in the same manner as a Computer peripheral.
	 *
     * @param turtle Access to the turtle that the peripheral is being created for.
     * @param side Which side of the turtle (left or right) that the turtle resides on.
     * @return The newly created peripheral. You may return null if this turtle is a Tool
	 * and this method is not expected to be called.
	 */		
	public IPeripheral createPeripheral( ITurtleAccess turtle, TurtleSide side );

	/**
	 * Will only be called for Tool turtle. Called when turtle.dig() or turtle.attack() is called
	 * by the turtle, and the tool is required to do some work.
	 * @param turtle Access to the turtle that the tool resides on.
	 * @param side Which side of the turtle (left or right) the tool resides on.
	 * @param verb Which action (dig or attack) the turtle is being called on to perform.
	 * @param direction Which world direction the action should be performed in, relative to the turtles
	 * position. This will either be up, down, or the direction the turtle is facing, depending on
	 * whether dig, digUp or digDown was called.
	 * @return Whether the turtle was able to perform the action, and hence whether the turtle.dig()
	 * or turtle.attack() lua method should return true. If true is returned, the tool will perform
	 * a swinging animation. You may return null if this turtle is a Peripheral
	 * and this method is not expected to be called.
	 */
	public TurtleCommandResult useTool( ITurtleAccess turtle, TurtleSide side, TurtleVerb verb, int direction );

	/**
	 * Called to obtain the IIcon to be used when rendering a turtle peripheral. Needs to be a "common"
	 * type IIcon for now, as there is no way to determine which texture sheet an IIcon is from by the
	 * IIcon itself.
	 * @param turtle Access to the turtle that the peripheral resides on.
	 * @param side Which side of the turtle (left or right) the peripheral resides on.
	 * @return The IIcon that you wish to be used to render your turtle peripheral.
	 */
	public IIcon getIcon( ITurtleAccess turtle, TurtleSide side );

    /**
     * TODO: Document me
     */
    public void update( ITurtleAccess turtle, TurtleSide side );
}
