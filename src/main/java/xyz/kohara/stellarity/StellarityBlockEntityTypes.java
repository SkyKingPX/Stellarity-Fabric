package xyz.kohara.stellarity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import xyz.kohara.stellarity.block_entity.AltarOfTheAccursedBlockEntity;

public class StellarityBlockEntityTypes {
  public static final BlockEntityType<AltarOfTheAccursedBlockEntity> ALTAR_OF_THE_ACCURSED = register("altar_of_the_accursed", AltarOfTheAccursedBlockEntity::new, StellarityBlocks.ALTAR_OF_THE_ACCURSED);
  //? <= 1.21.1 {
  public static <T extends BlockEntity> BlockEntityType<T> register(String path, BlockEntityType.BlockEntitySupplier<T> factory, Block... blocks) {
    return Registry.<BlockEntityType<T>>register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Stellarity.of(path).toString(), BlockEntityType.Builder.of(factory, blocks).build(null));
  }
  //? } else {
  /*private static <T extends BlockEntity> BlockEntityType<T> register(
    String name,
    FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
    Block... blocks
  ) {
    return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Stellarity.of(name), FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
  }
  *///? }

  public static void init() {
    Stellarity.LOGGER.info("Registering Stellarity Block Entities");
  }
}
