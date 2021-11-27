package com.SquidCoder.squidcoder.data.custom.containers;

import com.SquidCoder.squidcoder.setup.Registration;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;

public class ModContainers {

    public static final RegistryObject<ContainerType<ShipwrightsTableContainer>> SHIPWRIGHT_TABLE_CONTAINER
            = Registration.CONTAINERS.register("lightning_channeler_container",
            () -> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getCommandSenderWorld();
                return new ShipwrightsTableContainer(windowId, world, pos, inv, inv.player);
            })));


    public static void register() {
    }
}