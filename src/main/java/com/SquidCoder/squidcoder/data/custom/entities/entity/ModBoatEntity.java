package com.SquidCoder.squidcoder.data.custom.entities.entity;

import com.SquidCoder.squidcoder.SquidCoderMod;
import com.SquidCoder.squidcoder.data.custom.entities.ModEntityTypes;
import com.SquidCoder.squidcoder.data.custom.items.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

public class ModBoatEntity extends BoatEntity {
        private static final DataParameter<String> WOOD_TYPE
                = EntityDataManager.defineId(ModBoatEntity.class, DataSerializers.STRING);

        public ModBoatEntity(EntityType<? extends BoatEntity> type, World world) {
            super(type, world);
            this.blocksBuilding = true;
        }

        public ModBoatEntity(World worldIn, double x, double y, double z) {
            this(ModEntityTypes.REDWOOD_BOAT.get(), worldIn);
            this.setPosAndOldPos(x, y, z);
            this.setDeltaMovement(Vector3d.ZERO);
        }

        @Override
        protected void defineSynchedData() {
            super.defineSynchedData();
            this.entityData.define(WOOD_TYPE, "redwood");
        }

        @Override
        protected void readAdditionalSaveData(CompoundNBT compound) {
            super.readAdditionalSaveData(compound);
            compound.putString("Type", this.getWoodType());
        }

        @Override
        protected void addAdditionalSaveData(CompoundNBT compound) {
            super.addAdditionalSaveData(compound);
            compound.putString("Type", this.getWoodType());
        }

        public String getWoodType() {
            return this.entityData.get(WOOD_TYPE);
        }

        public void setWoodType(String wood) {
            this.entityData.set(WOOD_TYPE, wood);
        }

        @Override
        public Item getDropItem() {
            switch(this.getWoodType()) {
                case "redwood":
                    return Items.OAK_BOAT;
                default:
                    return Items.OAK_BOAT;
            }
        }

        @Override
        public ItemStack getPickedResult(RayTraceResult target) {
            return new ItemStack(ForgeRegistries.ITEMS.getValue(
                    new ResourceLocation(SquidCoderMod.MOD_ID, this.getWoodType() + "_boat")));
        }

        @Nonnull
        @Override
        public IPacket<?> getAddEntityPacket() {
            return NetworkHooks.getEntitySpawningPacket(this);
        }
}

