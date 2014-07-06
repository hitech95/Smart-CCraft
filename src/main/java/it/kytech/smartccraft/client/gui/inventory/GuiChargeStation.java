package it.kytech.smartccraft.client.gui.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import it.kytech.smartccraft.inventory.ContainerChargeStation;
import it.kytech.smartccraft.reference.Names;
import it.kytech.smartccraft.reference.Textures;
import it.kytech.smartccraft.tileentity.TileChargeStation;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiChargeStation extends GuiContainer {
    private TileChargeStation tileChargeStation;

    public GuiChargeStation(InventoryPlayer inventoryPlayer, TileChargeStation tileChargeStation) {
        super(new ContainerChargeStation(inventoryPlayer, tileChargeStation));
        this.tileChargeStation = tileChargeStation;
        xSize = 176;
        //ySize = 190;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        String containerName = StatCollector.translateToLocal(tileChargeStation.getInventoryName());
        fontRendererObj.drawString(containerName, xSize / 2 - fontRendererObj.getStringWidth(containerName) / 2, 6, 4210752);
        fontRendererObj.drawString(StatCollector.translateToLocal(Names.Containers.VANILLA_INVENTORY), 8, ySize - 93, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Textures.GUI_CHARGE_STATION);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

        int energy = (int) (14 * ((float) tileChargeStation.getEnergy() / (float) tileChargeStation.getEnergyCapacity()));
        if (energy > 0) drawTexturedModalRect(xStart + 80, (yStart + 36) - energy, 176, 12 - energy, 14, energy + 2);

        /*if (isPointInRegion(80, 36 - 14, 14, 14, x, y)) {
            drawCreativeTabHoveringText("Stored: "+(int)tileChargeStation.energyStorage+" / "+tileChargeStation.getMaxCharge(), x, y);
        }*/
    }
}
