package it.kytech.smartccraft.network.message;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import it.kytech.smartccraft.tileentity.TileChargeStation;
import net.minecraft.tileentity.TileEntity;

import java.util.UUID;

/**
 * Created by nicolo on 07/10/15.
 */
public class MessageTileEntityChargeStation implements IMessage, IMessageHandler<MessageTileEntityChargeStation, IMessage> {

    public int x, y, z;
    public byte orientation, state;
    public String customName;
    public UUID ownerUUID;

    public byte status;

    public MessageTileEntityChargeStation() {
    }

    public MessageTileEntityChargeStation(TileChargeStation tileEntitySCC) {
        this.x = tileEntitySCC.xCoord;
        this.y = tileEntitySCC.yCoord;
        this.z = tileEntitySCC.zCoord;
        this.orientation = (byte) tileEntitySCC.getOrientation().ordinal();
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
        this.orientation = buf.readByte();
        this.state = buf.readByte();
        int customNameLength = buf.readInt();
        this.customName = new String(buf.readBytes(customNameLength).array());
        if (buf.readBoolean())
        {
            this.ownerUUID = new UUID(buf.readLong(), buf.readLong());
        }
        else
        {
            this.ownerUUID = null;
        }
        this.status = buf.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeByte(orientation);
        buf.writeByte(state);
        buf.writeInt(customName.length());
        buf.writeBytes(customName.getBytes());
        if (ownerUUID != null)
        {
            buf.writeBoolean(true);
            buf.writeLong(ownerUUID.getMostSignificantBits());
            buf.writeLong(ownerUUID.getLeastSignificantBits());
        }
        else
        {
            buf.writeBoolean(false);
        }
        buf.writeByte(status);
    }

    @Override
    public IMessage onMessage(MessageTileEntityChargeStation message, MessageContext ctx) {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

        if (tileEntity instanceof TileChargeStation) {
            ((TileChargeStation) tileEntity).setOrientation(message.orientation);
            ((TileChargeStation) tileEntity).setState(message.state);
            ((TileChargeStation) tileEntity).setCustomName(message.customName);
            ((TileChargeStation) tileEntity).setOwnerUUID(message.ownerUUID);
            ((TileChargeStation) tileEntity).setStatus(TileChargeStation.STATES.values()[message.status]);
        }

        return null;
    }

    @Override
    public String toString() {
        return String.format("MessageTileEntitySCC - x:%s, y:%s, z:%s, orientation:%s, state:%s, customName:%s, ownerUUID:%s", x, y, z, orientation, state, customName, ownerUUID);
    }
}
