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

/**
 * An enum representing the two different actions that an ITurtleUpgrade of type
 * Tool may be called on to perform by a turtle.
 * @see ITurtleUpgrade
 * @see ITurtleUpgrade#useTool
 */
public enum TurtleVerb
{
	/**
	 * The turtle called turtle.dig(), turtle.digUp() or turtle.digDown()
	 */
	Dig,
	
	/**
	 * The turtle called turtle.attack(), turtle.attackUp() or turtle.attackDown()
	 */
	Attack,
}
