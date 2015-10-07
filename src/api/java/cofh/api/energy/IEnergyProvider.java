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
package cofh.api.energy;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * Implement this interface on Tile Entities which should provide energy, generally storing it in one or more internal {@link IEnergyStorage} objects.
 * <p>
 * A reference implementation is provided {@link TileEnergyHandler}.
 *
 * @author King Lemming
 *
 */
public interface IEnergyProvider extends IEnergyConnection {

	/**
	 * Remove energy from an IEnergyProvider, internal distribution is left entirely to the IEnergyProvider.
	 *
	 * @param from
	 *            Orientation the energy is extracted from.
	 * @param maxExtract
	 *            Maximum amount of energy to extract.
	 * @param simulate
	 *            If TRUE, the extraction will only be simulated.
	 * @return Amount of energy that was (or would have been, if simulated) extracted.
	 */
	int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate);

	/**
	 * Returns the amount of energy currently stored.
	 */
	int getEnergyStored(ForgeDirection from);

	/**
	 * Returns the maximum amount of energy that can be stored.
	 */
	int getMaxEnergyStored(ForgeDirection from);

}
