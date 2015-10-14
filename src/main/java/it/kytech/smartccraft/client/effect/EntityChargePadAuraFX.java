package it.kytech.smartccraft.client.effect;

import it.kytech.smartccraft.util.Color;
import it.kytech.smartccraft.util.helper.MoveVector;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

/**
 * Created by Hitech95 on 14/10/2015.
 */
public class EntityChargePadAuraFX extends EntityFX {

    public EntityChargePadAuraFX(World world, double x, double y, double z, int maxAge, MoveVector motion, Color color) {
        super(world, x, y, z, motion.getxMove(),motion.getyMove(), motion.getzMove());
        particleRed = color.getRed();
        particleGreen = color.getGreen();
        particleBlue = color.getBlue();
        setParticleTextureIndex(0);
        setSize(0.02F, 0.02F);
        particleScale *= (rand.nextFloat() * 0.5F + 0.5F);
        motionY *= 0.2D;
        this.particleMaxAge = ((int) (maxAge / (Math.random() * 0.8D + 0.2D)));
        this.noClip = true;
    }

    public void onUpdate() {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        moveEntity(motionX, motionY, motionZ);
        motionX *= 0.99D;
        motionY *= 0.99D;
        motionZ *= 0.99D;

        if (this.particleMaxAge-- <= 0)
        {
            this.setDead();
        }
    }
}
