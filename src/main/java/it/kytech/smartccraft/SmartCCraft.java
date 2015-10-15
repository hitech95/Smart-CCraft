/**
 * This file is part of SmartCCraft
 * <p/>
 * Copyright (c) 2015 hitech95 <https://github.com/hitech95>
 * Copyright (c) contributors
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.kytech.smartccraft;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import it.kytech.smartccraft.handler.ConfigurationHandler;
import it.kytech.smartccraft.handler.GuiHandler;
import it.kytech.smartccraft.init.BlocksLoader;
import it.kytech.smartccraft.init.ItemsLoader;
import it.kytech.smartccraft.init.RecipesLoader;
import it.kytech.smartccraft.network.PacketHandler;
import it.kytech.smartccraft.proxy.IProxy;
import it.kytech.smartccraft.reference.Reference;

@Mod(
        modid = Reference.MOD_ID,
        name = Reference.MOD_NAME,
        version = Reference.MOD_VERSION,
        guiFactory = Reference.GUI_FACTORY,
        dependencies = "after:Waila;"
)
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

        RecipesLoader.init();

        if (Loader.isModLoaded("Waila")) {
            FMLInterModComms.sendMessage("Waila", "register", "it.kytech.smartccraft.handler.WailaHandler.callbackRegister");
        }
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
