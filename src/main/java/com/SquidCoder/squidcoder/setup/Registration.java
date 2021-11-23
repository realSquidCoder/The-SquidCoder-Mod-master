package com.SquidCoder.squidcoder.setup;

import com.SquidCoder.squidcoder.SquidCoderMod;
import com.SquidCoder.squidcoder.setup.world.gen.ModBiomes;
import com.SquidCoder.squidcoder.setup.world.gen.ModConfiguredFeatures;
import com.SquidCoder.squidcoder.setup.world.gen.ModFeatures;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SquidCoderMod.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SquidCoderMod.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, SquidCoderMod.MOD_ID);
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPE = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, SquidCoderMod.MOD_ID);
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, SquidCoderMod.MOD_ID);
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, SquidCoderMod.MOD_ID);

    public static void register(){
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        SOUND_EVENTS.register(modEventBus);
        //PARTICLE_TYPE.register(modEventBus);
        FEATURES.register(modEventBus);
        BIOMES.register(modEventBus);

        ModBlocks.register();
        ModItems.register();
        ModSoundEvents.register();
        //ModParticleTypes.register();
        ModFeatures.register();
        ModBiomes.registerBiomes();
    }
}
