package it.kytech.smartccraft.tileentity;

import cofh.lib.util.position.IRotateableTile;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Hitech95 on 14/10/2015.
 */
public class TileChargePad extends TileEnergyHandler implements IRotateableTile {
    @Override
    public int getEnergyStored() {
        return 0;
    }

    @Override
    public void setEnergyStored(int energy) {

    }

    @Override
    public int receiveEnergy(ForgeDirection forgeDirection, int i, boolean b) {
        return 0;
    }

    @Override
    public int extractEnergy(ForgeDirection forgeDirection, int i, boolean b) {
        return 0;
    }

    @Override
    public int getEnergyStored(ForgeDirection forgeDirection) {
        return 0;
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection forgeDirection) {
        return 0;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection forgeDirection) {
        return false;
    }

    @Override
    public boolean canRotate() {
        return false;
    }

    @Override
    public boolean canRotate(ForgeDirection forgeDirection) {
        return false;
    }

    @Override
    public void rotate(ForgeDirection forgeDirection) {

    }

    @Override
    public void rotateDirectlyTo(int i) {

    }

    @Override
    public ForgeDirection getDirectionFacing() {
        return null;
    }
}
