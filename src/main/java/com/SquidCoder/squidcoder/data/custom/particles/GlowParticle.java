package com.SquidCoder.squidcoder.data.custom.particles;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.awt.*;

@OnlyIn(Dist.CLIENT)
public class GlowParticle extends SpriteTexturedParticle {

    /*
     * Just like all other SpriteTexturedParticles this class is more or less copied, take a look at existing
     * particles and add change some numbers around until you get what you need!
     */

    protected GlowParticle(ClientWorld worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);

        float f = this.random.nextFloat() * 1.0f;
        this.rCol = f;
        this.gCol = f;
        this.bCol = f;

        this.setSize(0.02f, 0.02f);
        this.scale(this.random.nextFloat() * 1.1F);
        this.xd *= (double)0.02f;
        this.yd *= (double)0.02f;
        this.zd *= (double)0.02f;
        this.lifetime = (int)(20.0D / (Math.random() * 1.0D));

    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.yd -= 0.04D * (double)this.gravity;
            this.move(this.xd, this.yd, this.zd);
            this.xd *= 0.9800000190734863D;
            this.yd *= 0.9800000190734863D;
            this.zd *= 0.9800000190734863D;
            if (this.onGround) {
                this.xd *= 0.699999988079071D;
                this.zd *= 0.699999988079071D;
            }
        }

    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }


    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final Color color;
        public Factory(Color color) {
            this.color = color;
        }


        @Nullable
        @Override
        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new Particle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed) {
                @Override
                public void render(IVertexBuilder iVertexBuilder, ActiveRenderInfo activeRenderInfo, float v) {

                }

                @Override
                public IParticleRenderType getRenderType() {
                    return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
                }

            };
        }

    }


}























