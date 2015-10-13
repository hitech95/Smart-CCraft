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
package it.kytech.smartccraft.init;

import cpw.mods.fml.common.registry.GameRegistry;
import it.kytech.smartccraft.block.*;
import it.kytech.smartccraft.item.ItemBlockChargeStation;
import it.kytech.smartccraft.item.ItemBlockFrame;
import it.kytech.smartccraft.reference.Names;
import it.kytech.smartccraft.reference.Reference;
import it.kytech.smartccraft.util.helper.LogHelper;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class BlocksLoader {

    public static final BlockDummySCC frameBlock = new BlockFrame();
    public static final BlockTileSCC assemblerBlock = new BlockAssembler();
    public static final BlockTileSCC chargeStation = new BlockChargeStation();

    public static void init() {
        LogHelper.info("Registering Blocks");

        GameRegistry.registerBlock(frameBlock, ItemBlockFrame.class, Names.Blocks.FRAME);
        GameRegistry.registerBlock(assemblerBlock, Names.Blocks.ASSEMBLER);
        GameRegistry.registerBlock(chargeStation, ItemBlockChargeStation.class, Names.Blocks.CHARGE_STATION);
    }
}
