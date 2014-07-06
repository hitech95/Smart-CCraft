package it.kytech.smartccraft;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import it.kytech.smartccraft.handler.ConfigurationHandler;
import it.kytech.smartccraft.handler.GuiHandler;
import it.kytech.smartccraft.init.BlocksLoader;
import it.kytech.smartccraft.init.ItemsLoader;
import it.kytech.smartccraft.network.PacketHandler;
import it.kytech.smartccraft.proxy.IProxy;
import it.kytech.smartccraft.reference.Reference;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, guiFactory = Reference.GUI_FACTORY)
public class SmartCCraft {

    @Mod.Instance(Reference.MOD_ID)
    public static SmartCCraft instance;

    @SidedProxy(serverSide = Reference.SERVER_PROXY, clientSide = Reference.CLIENT_PROXY)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        //Load configuration
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        //Load Packet Manager (Synk TEs)
        PacketHandler.init();

        //Load Intems
        ItemsLoader.init();

        //Load Blocks
        BlocksLoader.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // Register the GUI Handler
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

        // Initialize mod tile entities
        proxy.registerTileEntities();

        // Initialize custom rendering and pre-load textures (Client only)
        proxy.initRenderingAndTextures();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
