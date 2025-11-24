package xyz.kohara.stellarity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import xyz.kohara.stellarity.block_entity.AltarOfTheAccursedBlockEntity;

public class StellarityBlockEntityTypes {
  public static final BlockEntityType<AltarOfTheAccursedBlockEntity> ALTAR_OF_THE_ACCURSED = register("stellarity:altar_of_the_accursed", AltarOfTheAccursedBlockEntity::new, StellarityBlocks.ALTAR_OF_THE_ACCURSED);

  public static <T extends BlockEntity> BlockEntityType<T> register(String path, BlockEntityType.BlockEntitySupplier<T> factory, Block... blocks) {
    ResourceLocation location = Stellarity.of("altar_of_the_accursed");
    return Registry.<BlockEntityType<T>>register(BuiltInRegistries.BLOCK_ENTITY_TYPE, path, BlockEntityType.Builder.of(factory, blocks).build(null));
  }

  public static void init() {
    Stellarity.LOGGER.info("Registering Stellarity Block Entities");
  }
}
