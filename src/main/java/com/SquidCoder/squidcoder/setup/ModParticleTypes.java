package com.SquidCoder.squidcoder.setup;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.fml.RegistryObject;

public class ModParticleTypes extends ParticleTypes {
    public static final RegistryObject<BasicParticleType> GLOW = Registration.PARTICLE_TYPE.register("glow", () -> new BasicParticleType(false));

    static void register() {}
}
