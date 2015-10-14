package it.kytech.smartccraft.block;

import cofh.api.block.IDismantleable;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import it.kytech.smartccraft.client.effect.EntityChargePadAuraFX;
import it.kytech.smartccraft.reference.Names;
import it.kytech.smartccraft.tileentity.TileChargePad;
import it.kytech.smartccraft.tileentity.TileChargeStation;
import it.kytech.smartccraft.util.Color;
import it.kytech.smartccraft.util.helper.CenterFaceHelper;
import it.kytech.smartccraft.util.helper.LogHelper;
import it.kytech.smartccraft.util.helper.MoveVector;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Hitech95 on 14/10/2015.
 */
public class BlockChargePad extends BlockTileSCC implements ITileEntityProvider, IDismantleable {
    public static final int MAX_TIER = 3;

    @SideOnly(Side.CLIENT)
    private IIcon[] blockIconFront;
    @SideOnly(Side.CLIENT)
    private IIcon[] blockIconFrontEnabled;
    @SideOnly(Side.CLIENT)
    private IIcon[] blockIconSide;
    @SideOnly(Side.CLIENT)
    private IIcon[] blockIconBottom;

    public BlockChargePad() {
        super(Material.rock);
        this.setHardness(1.0f);
        this.setBlockName(Names.Blocks.CHARGE_PAD);
        this.setTickRandomly(true);
        //this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);

        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metaData) {

        if (metaData > MAX_TIER) {
            return null;
        }

        //TODO: Upgrades
        switch (metaData) {
            case 0:
                return new TileChargePad();
            default:
                return null;
        }
    }

    @Override
    public int damageDropped(int metaData) {
        return metaData;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {

        blockIconFront = new IIcon[MAX_TIER + 1];
        blockIconFrontEnabled = new IIcon[MAX_TIER + 1];
        blockIconSide = new IIcon[MAX_TIER + 1];
        blockIconBottom = new IIcon[MAX_TIER + 1];

        for (int meta = 0; meta < 4; meta++) {
            blockIconFrontEnabled[meta] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()) + "_" + meta + "_top" + "_active"));
            blockIconFront[meta] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()) + "_" + meta + "_top"));
            blockIconBottom[meta] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()) + "_" + meta + "_bottom"));
            blockIconSide[meta] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())) + "_" + meta);

        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (side == 0) {
            return blockIconBottom[meta];
        } else if (side == 1) {
            return blockIconFront[meta];
        } else {
            return blockIconSide[meta];
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        TileEntity tileEntity = world.getTileEntity(x, y, z); //TODO Read Tier and Status & working to get Correct Texture

        /*if ((tileEntity instanceof TileChargePad)) {
            TileChargePad tileChargePad = (TileChargePad) tileEntity;
            return getIcon(SIDE_OFFSETS[direction.ordinal()][side], tileChargeStation.getTier());
        }*/

        return getIcon(side, 0); //TODO, temp
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

        if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileChargeStation) {
            //player.openGui(SmartCCraft.instance, GuiIds.CHARGE_STATION, world, x, y, z);
            LogHelper.info("Open pad Gui"); //TODO: remove debug strings
        }

        return true;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        spawnParticles(world, x, y, z, random);
    }

    @SideOnly(Side.CLIENT)
    public void spawnParticles(World world, int blockX, int blockY, int blockZ, Random rand) {

        ForgeDirection orientation = ForgeDirection.UP;

        EffectRenderer render = FMLClientHandler.instance().getClient().effectRenderer;

        int maxMultParticles = 3;

        for (int multParticles = 0; multParticles < maxMultParticles; multParticles++) {
            for (int particles = getParticleCount(rand); particles > 0; particles--) {
                double dX = blockX + (rand.nextFloat() - 0.5F) + (CenterFaceHelper.getFace(orientation.ordinal()).offsetX);
                double dY = blockY + (CenterFaceHelper.getFace(orientation.ordinal()).offsetY) + rand.nextFloat() - 0.95F;
                double dZ = blockZ + (rand.nextFloat() - 0.5F) + (CenterFaceHelper.getFace(orientation.ordinal()).offsetZ);

                MoveVector movement = getParticleVelocity(rand);

                if (particles % 2 == 0) {
                    movement.multiplyComponents(1D, 0.55D, 1D);
                }
                Color color = getParticleColour(rand, 0); //TODO Read Tier on tilentity
                render.addEffect(new EntityChargePadAuraFX(world, dX, dY, dZ, getParticleMaxAge(rand), movement, color));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    protected int getParticleCount(Random random) {
        return 6 + random.nextInt(5);
    }

    @SideOnly(Side.CLIENT)
    protected int getParticleMaxAge(Random random) {
        return 14 + random.nextInt(5);
    }

    @SideOnly(Side.CLIENT)
    protected MoveVector getParticleVelocity(Random random) {
        return new MoveVector(0.0D, 7.6D, 0.0D);
    }

    @SideOnly(Side.CLIENT)
    protected Color getParticleColour(Random random, int tier) {
        float red = (random.nextBoolean()) ? random.nextFloat() * 0.1F : 0F;
        float gb = 0.6F;
        return new Color(red, gb + random.nextFloat() * 0.4F, gb + random.nextFloat() * 0.4F);
    }

    public boolean canProvidePower() {
        return true;
    }

    public int isProvidingStrongPower(IBlockAccess access, int x, int y, int z, int side) {
        return 0; //TODO read status from Tile
    }

    public int isProvidingWeakPower(IBlockAccess access, int x, int y, int z, int side) {
        return isProvidingStrongPower(access, x, y, z, side);
    }

    @Override
    public ArrayList<ItemStack> dismantleBlock(EntityPlayer entityPlayer, World world, int x, int y, int z, boolean returnBlock) {
        ArrayList<ItemStack> list = getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 0);

        world.setBlockToAir(x, y, z);
        if (!returnBlock) {
            for (ItemStack item : list) {
                dropBlockAsItem(world, x, y, z, item);
            }
        }
        return list;
    }

    @Override
    public boolean canDismantle(EntityPlayer entityPlayer, World world, int i, int i1, int i2) {
        return true;
    }
}
