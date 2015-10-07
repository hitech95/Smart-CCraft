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
package dan200.computercraft.api.lua;

/**
 * An interface for representing custom objects returned by IPeripheral.callMethod() calls.
 * Return objects implementing this interface to expose objects with methods to lua.
 */
public interface ILuaObject
{
	/**
	 * Get the names of the methods that this object implements. This works the same as IPeripheral.getMethodNames(). See that method for detailed documentation.
	 * @see dan200.computercraft.api.peripheral.IPeripheral#getMethodNames()
	 */
    public String[] getMethodNames();

	/**
	 * Called when a user calls one of the methods that this object implements. This works the same as IPeripheral.callMethod(). See that method for detailed documentation.
	 * @see dan200.computercraft.api.peripheral.IPeripheral#callMethod(dan200.computercraft.api.peripheral.IComputerAccess, ILuaContext, int, Object[])
	 */
    public Object[] callMethod( ILuaContext context, int method, Object[] arguments ) throws LuaException, InterruptedException;
}
