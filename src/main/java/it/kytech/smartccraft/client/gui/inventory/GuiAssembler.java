package it.kytech.smartccraft.client.gui.inventory;

import it.kytech.smartccraft.inventory.ContainerAssembler;
import it.kytech.smartccraft.reference.Names;
import it.kytech.smartccraft.reference.Textures;
import it.kytech.smartccraft.tileentity.TileAssembler;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

/**
 * Created by M2K on 30/06/2014.
 */
public class GuiAssembler extends GuiContainer {

    private TileAssembler tileAssembler;

    public GuiAssembler(InventoryPlayer inventoryPlayer, TileAssembler tileAssembler) {
        super(new ContainerAssembler(inventoryPlayer, tileAssembler));
        this.tileAssembler = tileAssembler;
        xSize = 176;
        //ySize = 190;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        String containerName = StatCollector.translateToLocal(tileAssembler.getInventoryName());
        fontRendererObj.drawString(containerName, xSize / 2 - fontRendererObj.getStringWidth(containerName) / 2, 6, 4210752);
        fontRendererObj.drawString(StatCollector.translateToLocal(Names.Containers.VANILLA_INVENTORY), 8, ySize - 93, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Textures.GUI_ASSEMBLER);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

        int energy = (int) (14 * ((float) tileAssembler.energy / (float) tileAssembler.getMaxCharge()));
        if (energy > 0) drawTexturedModalRect(xStart + 80, (yStart + 36) - energy, 176, 12 - energy, 14, energy + 2);

        /*if (isPointInRegion(80, 36 - 14, 14, 14, x, y)) {
            drawCreativeTabHoveringText("Stored: "+(int)tileChargeStation.energy+" / "+tileChargeStation.getMaxCharge(), x, y);
        }*/
    }
}
