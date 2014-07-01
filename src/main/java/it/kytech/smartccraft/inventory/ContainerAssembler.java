package it.kytech.smartccraft.inventory;

import it.kytech.smartccraft.tileentity.TileAssembler;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

import java.util.List;

/**
 * Created by M2K on 30/06/2014.
 */
public class ContainerAssembler extends ContainerSCC {

    public TileAssembler tileAssembler;
    private int lastEnergy;

    public ContainerAssembler(InventoryPlayer inventoryPlayer, TileAssembler tileAssembler) {
        super(inventoryPlayer, tileAssembler);

        this.tileAssembler = tileAssembler;

        addSlotToContainer(new Slot(tileAssembler, 0, 12, 8));
        addSlotToContainer(new Slot(tileAssembler, 1, 12, 55));
        addSlotToContainer(new Slot(tileAssembler, 2, 46, 32));
        addSlotToContainer(new Slot(tileAssembler, 3, 108, 38));
        addSlotToContainer(new Slot(tileAssembler, 4, 147, 8));
    }

    @Override
    public void addCraftingToCrafters(ICrafting var1) {
        super.addCraftingToCrafters(var1);
        var1.sendProgressBarUpdate(this, 0, (int) tileAssembler.energy);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (ICrafting crafter : (List<ICrafting>) crafters) {
            if (lastEnergy != tileAssembler.energy)
                crafter.sendProgressBarUpdate(this, 0, (int) tileAssembler.energy);
        }

        lastEnergy = (int) tileAssembler.energy;
    }

    @Override
    public void updateProgressBar(int index, int value) {
        switch (index) {
            case 0:
                tileAssembler.energy = value;
                break;
        }
    }
}
