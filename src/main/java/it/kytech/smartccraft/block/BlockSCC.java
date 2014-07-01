package it.kytech.smartccraft.block;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import it.kytech.smartccraft.creativetab.CreativeTab;
import it.kytech.smartccraft.reference.Reference;
import it.kytech.smartccraft.reference.Textures;
import it.kytech.smartccraft.tileentity.TileEntitySCC;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class BlockSCC extends Block {
    /**
     * 0 = y- down
     * 1 = y+ up
     * 2 = z- east
     * 3 = z+ west (DEFAULT)
     * 4 = x- north
     * 5 = x+ south
     */
    protected static final int[][] SIDE_OFFSETS = {{3, 2, 0, 1, 5, 4}, {2, 3, 0, 1, 5, 4}, {0, 1, 3, 2, 5, 4}, {0, 1, 2, 3, 4, 5}, {0, 1, 4, 5, 3, 2}, {0, 1, 5, 4, 2, 3}};

    public BlockSCC() {
        this(Material.rock);
    }

    public BlockSCC(Material material) {
        super(material);
        this.setCreativeTab(CreativeTab.SCC_TAB);
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        dropInventory(world, x, y, z);
        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack) {
        if (world.getTileEntity(x, y, z) instanceof TileEntitySCC) {
            int direction = 0;
            int facing = 4;

            int yaw = MathHelper.floor_double(((entityLiving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
            int pitch = Math.round(entityLiving.rotationPitch);

            if (pitch >= 65) direction = ForgeDirection.UP.ordinal();
            else if (pitch <= -65) direction = ForgeDirection.DOWN.ordinal();
            else if (yaw == 0) direction = ForgeDirection.NORTH.ordinal();
            else if (yaw == 1) direction = ForgeDirection.EAST.ordinal();
            else if (yaw == 2) direction = ForgeDirection.SOUTH.ordinal();
            else direction = ForgeDirection.WEST.ordinal();

            if (itemStack.hasDisplayName()) {
                ((TileEntitySCC) world.getTileEntity(x, y, z)).setCustomName(itemStack.getDisplayName());
            }

            ((TileEntitySCC) world.getTileEntity(x, y, z)).setOrientation(direction);
        }
    }

    protected void dropInventory(World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (!(tileEntity instanceof IInventory)) {
            return;
        }

        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack itemStack = inventory.getStackInSlot(i);

            if (itemStack != null && itemStack.stackSize > 0) {
                Random rand = new Random();

                float dX = rand.nextFloat() * 0.8F + 0.1F;
                float dY = rand.nextFloat() * 0.8F + 0.1F;
                float dZ = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, itemStack.copy());

                if (itemStack.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                itemStack.stackSize = 0;
            }
        }
    }
}
