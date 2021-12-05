package com.SquidCoder.squidcoder.setup;

import com.SquidCoder.squidcoder.SquidCoderMod;
import com.SquidCoder.squidcoder.data.custom.blocks.ModBlocks;
import com.SquidCoder.squidcoder.data.custom.containers.ModContainers;
import com.SquidCoder.squidcoder.data.custom.entities.ModEntityTypes;
import com.SquidCoder.squidcoder.data.custom.fluids.ModFluids;
import com.SquidCoder.squidcoder.data.custom.items.ModItems;
import com.SquidCoder.squidcoder.data.custom.sounds.ModSoundEvents;
import com.SquidCoder.squidcoder.data.custom.tile_entities.ModTileEntities;
import com.SquidCoder.squidcoder.setup.world.gen.ModBiomesDatapack;
import com.SquidCoder.squidcoder.setup.world.gen.ModFeatures;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
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
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, SquidCoderMod.MOD_ID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, SquidCoderMod.MOD_ID);
    public static DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, SquidCoderMod.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, SquidCoderMod.MOD_ID);
    public static void register(){
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        SOUND_EVENTS.register(modEventBus);
        //PARTICLE_TYPE.register(modEventBus);
        FEATURES.register(modEventBus);
        BIOMES.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        TILE_ENTITIES.register(modEventBus);
        CONTAINERS.register(modEventBus);
        FLUIDS.register(modEventBus);

        ModBlocks.register();
        ModItems.register();
        ModSoundEvents.register();
        //ModParticleTypes.register();
        ModFeatures.register();
        //ModBiomes.registerBiomes();
        ModBiomesDatapack.register();
        ModEntityTypes.register();
        ModTileEntities.register();
        ModContainers.register();
        ModFluids.register();
    }
}
