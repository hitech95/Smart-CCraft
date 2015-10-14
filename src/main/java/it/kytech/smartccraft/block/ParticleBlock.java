package it.kytech.smartccraft.block;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import it.kytech.smartccraft.client.effect.EntityChargePadAuraFX;
import it.kytech.smartccraft.reference.Names;
import it.kytech.smartccraft.util.Color;
import it.kytech.smartccraft.util.helper.CenterFaceHelper;
import it.kytech.smartccraft.util.helper.MoveVector;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

/**
 * Created by Hitech95 on 14/10/2015.
 */
public class ParticleBlock extends BlockDummySCC {

    public ParticleBlock() {
        super(net.minecraft.block.material.Material.iron);
        this.setBlockName(Names.Blocks.PARTICLE);
        this.setHardness(0.6f);
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
                double dY = blockY + (CenterFaceHelper.getFace(orientation.ordinal()).offsetY) + rand.nextFloat() - 0.7F;
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
        float gb = 0.6F;
        return new Color(0F, gb + random.nextFloat() * 0.4F, gb + random.nextFloat() * 0.4F);
    }
}
