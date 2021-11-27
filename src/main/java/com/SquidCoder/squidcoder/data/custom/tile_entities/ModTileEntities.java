package com.SquidCoder.squidcoder.data.custom.tile_entities;

import com.SquidCoder.squidcoder.data.custom.blocks.ModBlocks;
import com.SquidCoder.squidcoder.setup.Registration;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

public class ModTileEntities {

    public static RegistryObject<TileEntityType<ShipwrightsTableTile>> SHIPWRIGHT_TABLE_TILE =
            Registration.TILE_ENTITIES.register("shipwright_table_tile", () -> TileEntityType.Builder.of(
                    ShipwrightsTableTile::new, ModBlocks.SHIPWRIGHTS_TABLE.get()).build(null));

    public static void register() {
    }
}
