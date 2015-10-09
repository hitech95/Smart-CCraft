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
package it.kytech.smartccraft.network.message;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import it.kytech.smartccraft.tileentity.TileEnergyHandler;
import it.kytech.smartccraft.util.LogHelper;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by nicolo on 09/10/15.
 */
public class MessageTileEnergy implements IMessage, IMessageHandler<MessageTileEnergy, IMessage> {

    public int x;
    public int y;
    public int z;

    public int energy;

    public MessageTileEnergy() {

    }

    public MessageTileEnergy(TileEnergyHandler tileEnergyHandler) {
        this.x = tileEnergyHandler.xCoord;
        this.x = tileEnergyHandler.yCoord;
        this.x = tileEnergyHandler.zCoord;

        this.energy = tileEnergyHandler.getEnergyStored();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.x = buf.readInt();
        this.x = buf.readInt();

        this.energy = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);

        buf.writeInt(energy);
    }

    @Override
    public IMessage onMessage(MessageTileEnergy message, MessageContext ctx) {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

        if (tileEntity instanceof TileEnergyHandler) {
            ((TileEnergyHandler) tileEntity).setEnergyStored(message.energy);

            LogHelper.info("Message Energy Received: " + ((TileEnergyHandler) tileEntity).getEnergyStored());
        }

        return null;
    }
}
