package it.kytech.smartccraft.handler;

import cpw.mods.fml.common.network.IGuiHandler;
import it.kytech.smartccraft.client.gui.inventory.GuiAssembler;
import it.kytech.smartccraft.client.gui.inventory.GuiChargeStation;
import it.kytech.smartccraft.inventory.ContainerAssembler;
import it.kytech.smartccraft.inventory.ContainerChargeStation;
import it.kytech.smartccraft.reference.GuiIds;
import it.kytech.smartccraft.tileentity.TileAssembler;
import it.kytech.smartccraft.tileentity.TileChargeStation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by M2K on 29/06/2014.
 */
public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == GuiIds.CHARGE_STATION) {
            TileChargeStation tileChargeStation = (TileChargeStation) world.getTileEntity(x, y, z);
            return new ContainerChargeStation(player.inventory, tileChargeStation);
        } else if (id == GuiIds.ASSEMBLER) {
            TileAssembler tileAssembler = (TileAssembler) world.getTileEntity(x, y, z);
            return new ContainerAssembler(player.inventory, tileAssembler);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == GuiIds.CHARGE_STATION) {
            TileChargeStation tileChargeStation = (TileChargeStation) world.getTileEntity(x, y, z);
            return new GuiChargeStation(player.inventory, tileChargeStation);
        } else if (id == GuiIds.ASSEMBLER) {
            TileAssembler tileAssembler = (TileAssembler) world.getTileEntity(x, y, z);
            return new GuiAssembler(player.inventory, tileAssembler);
        }

        return null;
    }
}
