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
 * An interface for objects executing custom turtle commands, used with ITurtleAccess.issueCommand
 * @see ITurtleAccess#executeCommand(dan200.computercraft.api.lua.ILuaContext,ITurtleCommand)
 */
public interface ITurtleCommand
{
	/**
	 * Will be called by the turtle on the main thread when it is time to execute the custom command.
	 * The handler should either perform the work of the command, and return success, or return
	 * failure with an error message to indicate the command cannot be executed at this time.
	 * @param turtle access to the turtle for whom the command was issued
	 * @return TurtleCommandResult.success() or TurtleCommandResult.failure( errorMessage )
     * @see ITurtleAccess#executeCommand(dan200.computercraft.api.lua.ILuaContext,ITurtleCommand)
     * @see dan200.computercraft.api.turtle.TurtleCommandResult
	 */
	public TurtleCommandResult execute( ITurtleAccess turtle );
}
