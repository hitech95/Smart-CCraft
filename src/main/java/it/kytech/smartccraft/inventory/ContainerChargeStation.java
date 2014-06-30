package it.kytech.smartccraft.inventory;

import it.kytech.smartccraft.tileentity.TileChargeStation;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

import java.util.List;

/**
 * Created by M2K on 29/06/2014.
 */
public class ContainerChargeStation extends ContainerSCC {
    public TileChargeStation tileChargeStation;
    private int lastEnergy;

    public ContainerChargeStation(InventoryPlayer inventoryPlayer, TileChargeStation tileChargeStation) {
        super(inventoryPlayer, tileChargeStation);

        this.tileChargeStation = tileChargeStation;

        addSlotToContainer(new Slot(tileChargeStation, 0, 80, 41));
    }

    @Override
    public void addCraftingToCrafters(ICrafting var1) {
        super.addCraftingToCrafters(var1);
        var1.sendProgressBarUpdate(this, 0, (int) tileChargeStation.energy);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (ICrafting crafter : (List<ICrafting>) crafters) {
            if (lastEnergy != tileChargeStation.energy)
                crafter.sendProgressBarUpdate(this, 0, (int) tileChargeStation.energy);
        }

        lastEnergy = (int) tileChargeStation.energy;
    }

    @Override
    public void updateProgressBar(int index, int value) {
        switch (index) {
            case 0:
                tileChargeStation.energy = value;
                break;
        }
    }
}
