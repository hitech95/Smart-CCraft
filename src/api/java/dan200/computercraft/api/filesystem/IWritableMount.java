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
package dan200.computercraft.api.filesystem;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Represents a part of a virtual filesystem that can be mounted onto a computercraft using IComputerAccess.mount() or IComputerAccess.mountWritable(), that can also be written to.
 * Ready made implementations of this interface can be created using ComputerCraftAPI.createSaveDirMount(), or you're free to implement it yourselves!
 * @see dan200.computercraft.api.ComputerCraftAPI#createSaveDirMount(World, String)
 * @see dan200.computercraft.api.peripheral.IComputerAccess#mountWritable(String, dan200.computercraft.api.filesystem.IMount)
 * @see dan200.computercraft.api.filesystem.IMount
 */
public interface IWritableMount extends IMount
{
	/**
	 * Creates a directory at a given path inside the virtual file system.
	 * @param path A file path in normalised format, relative to the mount location. ie: "programs/mynewprograms"
	 */
	public void makeDirectory( String path ) throws IOException;

	/**
	 * Deletes a directory at a given path inside the virtual file system.
	 * @param path A file path in normalised format, relative to the mount location. ie: "programs/myoldprograms"
	 */
	public void delete( String path ) throws IOException;

	/**
	 * Opens a file with a given path, and returns an outputstream for writing to it.
	 * @param path A file path in normalised format, relative to the mount location. ie: "programs/myprogram"
	 * @return a stream for writing to
	 */
	public OutputStream openForWrite( String path ) throws IOException;

	/**
	 * Opens a file with a given path, and returns an outputstream for appending to it.
	 * @param path A file path in normalised format, relative to the mount location. ie: "programs/myprogram"
	 * @return a stream for writing to
	 */
	public OutputStream openForAppend( String path ) throws IOException;

	/**
	 * Get the ammount of free space on the mount, in bytes. You should decrease this value as the user writes to the mount, and write operations should fail once it reaches zero.
	 * @return The ammount of free space, in bytes.
	 */
	public long getRemainingSpace() throws IOException;
}
