package com.SquidCoder.squidcoder;

import com.SquidCoder.squidcoder.data.custom.containers.ModContainers;
import com.SquidCoder.squidcoder.data.custom.entities.ModEntityTypes;
import com.SquidCoder.squidcoder.data.custom.entities.render.ModBoatRenderer;
import com.SquidCoder.squidcoder.data.custom.fluids.ModFluids;
import com.SquidCoder.squidcoder.data.custom.screens.ShipwrightsTableScreen;
import com.SquidCoder.squidcoder.setup.Registration;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.AxeItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SquidCoderMod.MOD_ID)
public class SquidCoderMod
{
    public static final String MOD_ID = "squidcoder";
    public static final String VERSION = "0.1.0";
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting()
            .disableHtmlEscaping()
            .create();
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public SquidCoderMod() {
        Registration.register();

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            AxeItem.STRIPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPABLES)
                    //.put(ModBlocks.WAXED_COPPER_BLOCK.get(), ModBlocks.COPPER_BLOCK.get())
                    //etc.
                    .build();
        });

        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.REDWOOD_BOAT.get(), ModBoatRenderer::new);
        //Minecraft.getInstance().particleEngine.register(Registration.PARTICLE_TYPE, new IParticleFactory factory);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().options);
        event.enqueueWork(() -> {
            ScreenManager.register(ModContainers.SHIPWRIGHT_TABLE_CONTAINER.get(),
                    ShipwrightsTableScreen::new);
            RenderTypeLookup.setRenderLayer(ModFluids.OIL_FLUID.get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(ModFluids.OIL_BLOCK.get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(ModFluids.OIL_FLOWING.get(), RenderType.translucent());
        });

    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("squidcoder", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
