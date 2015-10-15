package it.kytech.smartccraft.network.message;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import it.kytech.smartccraft.tileentity.TileChargePad;
import net.minecraft.tileentity.TileEntity;

import java.util.UUID;

/**
 * Created by nicolo on 15/10/15.
 */
public class MessageTileChargePad implements IMessage, IMessageHandler<MessageTileChargePad, IMessage> {

    public int x, y, z;
    public byte state;
    public String customName;
    public UUID ownerUUID;

    public byte status;

    public MessageTileChargePad() {
    }

    public MessageTileChargePad(TileChargePad tileEntitySCC) {
        this.x = tileEntitySCC.xCoord;
        this.y = tileEntitySCC.yCoord;
        this.z = tileEntitySCC.zCoord;
        this.state = (byte) tileEntitySCC.getState();
        this.customName = tileEntitySCC.getCustomName();
        this.ownerUUID = tileEntitySCC.getOwnerUUID();

        status = (byte) tileEntitySCC.getStatus().ordinal();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.state = buf.readByte();
        int customNameLength = buf.readInt();
        this.customName = new String(buf.readBytes(customNameLength).array());
        if (buf.readBoolean()) {
            this.ownerUUID = new UUID(buf.readLong(), buf.readLong());
        } else {
            this.ownerUUID = null;
        }
        this.status = buf.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeByte(state);
        buf.writeInt(customName.length());
        buf.writeBytes(customName.getBytes());
        if (ownerUUID != null) {
            buf.writeBoolean(true);
            buf.writeLong(ownerUUID.getMostSignificantBits());
            buf.writeLong(ownerUUID.getLeastSignificantBits());
        } else {
            buf.writeBoolean(false);
        }
        buf.writeByte(status);
    }

    @Override
    public IMessage onMessage(MessageTileChargePad message, MessageContext ctx) {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

        if (tileEntity instanceof TileChargePad) {
            ((TileChargePad) tileEntity).setState(message.state);
            ((TileChargePad) tileEntity).setCustomName(message.customName);
            ((TileChargePad) tileEntity).setOwnerUUID(message.ownerUUID);
            ((TileChargePad) tileEntity).setStatus(TileChargePad.STATES.values()[message.status]);
        }

        return null;
    }

    @Override
    public String toString() {
        return String.format("MessageTileChargePad - x:%s, y:%s, z:%s, state:%s, customName:%s, ownerUUID:%s", x, y, z, state, customName, ownerUUID);
    }
}
