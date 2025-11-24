package xyz.kohara.stellarity.block_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import xyz.kohara.stellarity.StellarityBlockEntityTypes;
//? 1.21.9 {
/*import net.minecraft.util.ColorRGBA;
 *///?}

public class AltarOfTheAccursedBlockEntity extends BlockEntity {
  public AltarOfTheAccursedBlockEntity(BlockPos pos, BlockState state) {
    this(StellarityBlockEntityTypes.ALTAR_OF_THE_ACCURSED, pos, state);
  }

  private boolean unlocked = false;
  private static long ticksPassed = 0;

  public AltarOfTheAccursedBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
    super(blockEntityType, blockPos, blockState);
  }

  //? 1.20.1 {
  @Override
  protected void saveAdditional(CompoundTag compoundTag) {
    super.saveAdditional(compoundTag);
    compoundTag.putBoolean("unlocked", unlocked);
  }

  @Override
  public void load(CompoundTag compoundTag) {
    super.load(compoundTag);
    if (compoundTag.contains("unlocked")) {
      this.unlocked = compoundTag.getBoolean("unlocked");
    }
  }

  @Override
  public @NotNull CompoundTag getUpdateTag() {
    var tag = super.getUpdateTag();
    tag.putBoolean("unlocked", this.unlocked);
    return tag;
  }
  //? } else 1.21.1 {
/*
  @Override
  protected void saveAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
    super.saveAdditional(compoundTag, provider);
    compoundTag.putBoolean("unlocked", unlocked);
  }

  @Override
  protected void loadAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
    super.loadAdditional(compoundTag, provider);
    if (compoundTag.contains("unlocked")) {
      this.unlocked = compoundTag.getBoolean("unlocked");
    }
  }

  *///? } else {

  
  //? }

  //? > 1.21 {
/*
  @Override
  public @NotNull CompoundTag getUpdateTag(HolderLookup.Provider provider) {
    var tag = super.getUpdateTag(provider);
    tag.putBoolean("unlocked", this.unlocked);
    return tag;
  }
  *///?}

  @Override
  public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
    return ClientboundBlockEntityDataPacket.create(this);
  }

  public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T blockEntity) {
    if (blockEntity instanceof AltarOfTheAccursedBlockEntity entity) {
      if (level.isClientSide()) {
        if (!entity.unlocked) return;

        ticksPassed++;

        double x = blockPos.getX() + 0.5;
        double y = blockPos.getY() + 0.5;
        double z = blockPos.getZ() + 0.5;


        float angle = ticksPassed / 20f;
        double dx = Mth.cos(angle);
        double dz = Mth.sin(angle);

        level.addParticle(
          new DustColorTransitionOptions(new Vector3f(0.733f, 0.0f, 1.0f), new Vector3f(0.106f, 0.0f, 0.145f), 1.4f),
          x + dx, y, z + dz,
          0, 0, 0
        );


        level.addParticle(
          new DustColorTransitionOptions(new Vector3f(0.733f, 0.0f, 1.0f), new Vector3f(0.106f, 0.0f, 0.145f), 1.4f),
          x - dx, y, z - dz,
          0, 0, 0
        );


        dx = Mth.cos(-angle) * 1.5;
        dz = Mth.sin(-angle) * 1.5;


        level.addParticle(
          new DustColorTransitionOptions(new Vector3f(0.733f, 0.0f, 1.0f), new Vector3f(0.106f, 0.0f, 0.145f), 1.4f),
          x + dx, y, z + dz,
          0, 0, 0
        );

        level.addParticle(
          new DustColorTransitionOptions(new Vector3f(0.733f, 0.0f, 1.0f), new Vector3f(0.106f, 0.0f, 0.145f), 1.4f),
          x - dx, y, z - dz,
          0, 0, 0
        );
        if (ticksPassed % 3 == 0) {
          dx = level.random.nextGaussian() * 0.5;
          dz = level.random.nextGaussian() * 0.5;
          level.addParticle(
            ParticleTypes.ENCHANT,
            x + dx, y + 1.5, z + dz,
            dx * 2, -1.5, dz * 2
          );
        }

        level.addParticle(
          ParticleTypes.WITCH,
          x, y + 0.5, z,
          0, 0,
          0
        );

      }
      ;
      if (level instanceof ServerLevel server) {
        EndDragonFight dragonFight = server.getDragonFight();
        boolean unlocked = dragonFight != null && dragonFight.hasPreviouslyKilledDragon();

        if (entity.unlocked != unlocked) {
          entity.unlocked = unlocked;
          level.sendBlockUpdated(blockPos, blockState, blockState, 2);
          entity.setChanged();
        }

      }
    }
  }


}
