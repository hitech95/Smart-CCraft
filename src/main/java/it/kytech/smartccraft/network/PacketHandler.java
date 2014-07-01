package it.kytech.smartccraft.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import it.kytech.smartccraft.network.message.MessageTileEntitySCC;
import it.kytech.smartccraft.reference.Reference;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID.toLowerCase());

    public static void init() {
        INSTANCE.registerMessage(MessageTileEntitySCC.class, MessageTileEntitySCC.class, 0, Side.CLIENT);
    }
}
