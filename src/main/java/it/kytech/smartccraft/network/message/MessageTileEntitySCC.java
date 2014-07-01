package it.kytech.smartccraft.network.message;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import it.kytech.smartccraft.tileentity.TileEntitySCC;
import net.minecraft.tileentity.TileEntity;

public class MessageTileEntitySCC implements IMessage, IMessageHandler<MessageTileEntitySCC, IMessage> {
    public int x, y, z;
    public byte orientation, state;
    public String customName, owner;

    public MessageTileEntitySCC() {
    }

    public MessageTileEntitySCC(TileEntitySCC tileEntitySCC) {
        this.x = tileEntitySCC.xCoord;
        this.y = tileEntitySCC.yCoord;
        this.z = tileEntitySCC.zCoord;
        this.orientation = (byte) tileEntitySCC.getOrientation().ordinal();
        this.state = (byte) tileEntitySCC.getState();
        this.customName = tileEntitySCC.getCustomName();
        this.owner = tileEntitySCC.getOwner();
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
        int ownerLength = buf.readInt();
        this.owner = new String(buf.readBytes(ownerLength).array());
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
        buf.writeInt(owner.length());
        buf.writeBytes(owner.getBytes());
    }

    @Override
    public IMessage onMessage(MessageTileEntitySCC message, MessageContext ctx) {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

        if (tileEntity instanceof TileEntitySCC) {
            ((TileEntitySCC) tileEntity).setOrientation(message.orientation);
            ((TileEntitySCC) tileEntity).setState(message.state);
            ((TileEntitySCC) tileEntity).setCustomName(message.customName);
            ((TileEntitySCC) tileEntity).setOwner(message.owner);
        }

        return null;
    }

    @Override
    public String toString() {
        return String.format("MessageTileEntitySCC - x:%s, y:%s, z:%s, orientation:%s, state:%s, customName:%s, owner:%s", x, y, z, orientation, state, customName, owner);
    }
}
