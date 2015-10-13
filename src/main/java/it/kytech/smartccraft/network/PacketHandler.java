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
package it.kytech.smartccraft.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import it.kytech.smartccraft.network.message.MessageTileChargeStation;
import it.kytech.smartccraft.network.message.MessageTileEnergy;
import it.kytech.smartccraft.network.message.MessageTileEntitySCC;
import it.kytech.smartccraft.reference.Reference;
import it.kytech.smartccraft.util.helper.LogHelper;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.LOWERCASE_MOD_ID);

    public static void init() {
        LogHelper.info("Registering Network");

        INSTANCE.registerMessage(MessageTileEntitySCC.class, MessageTileEntitySCC.class, 0, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileChargeStation.class, MessageTileChargeStation.class, 1, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileEnergy.class, MessageTileEnergy.class, 2, Side.CLIENT);
    }
}
