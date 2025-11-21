package xyz.kohara.stellarity.mixin;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//? >= 1.21.10 {
/*import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.PowerParticleOption;
*///?} else {
import net.minecraft.core.particles.SimpleParticleType;
//?}

@Mixin(FishingHook.class)
public abstract class VoidFishingMixin extends Projectile {
    @Shadow
    private FishingHook.FishHookState currentState;

    @Unique
    private boolean isVoidFishing = false;

    @Unique
    private boolean splashed = false;

    @Shadow
    @Nullable
    public abstract Player getPlayerOwner();

    public VoidFishingMixin(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }

    @Unique
    private boolean isEnd() {
        //? <= 1.20.1 {
        return this.level().dimensionTypeId() == BuiltinDimensionTypes.END;
         //?} else {
        /*return this.level().dimensionTypeRegistration().is(BuiltinDimensionTypes.END);
        *///?}
    }

    @Unique
    private boolean isNotInEndWater() {
        BlockPos pos = this.blockPosition();

        return isEnd() && !this.level().getBlockState(pos).is(Blocks.WATER) && !this.level().getBlockState(pos.below()).is(Blocks.WATER);
    }

    @Unique
    private boolean evalVoidFishing() {
        return isNotInEndWater() && currentState == FishingHook.FishHookState.BOBBING;
    }

    @Unique
    private boolean canBob() {
        return isNotInEndWater() && (this.currentState == FishingHook.FishHookState.BOBBING || this.distanceTo(this.getPlayerOwner()) > 20.0);
    }

    @ModifyVariable(method = "tick()V", at = @At(value = "STORE"), ordinal = 0)
    private boolean checkCanBob(boolean original) {

        if (canBob()) {
            return true;
        }

        return original;
    }

    @Redirect(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/FishingHook;move(Lnet/minecraft/world/entity/MoverType;Lnet/minecraft/world/phys/Vec3;)V"))
    private void voidFishingHover(FishingHook instance, MoverType moverType, Vec3 vec3) {
        this.isVoidFishing = evalVoidFishing();
        if (isVoidFishing) {
            this.setDeltaMovement(0.0, 0.0, 0.0);
        } else {
            instance.move(moverType, vec3);
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void visuals(CallbackInfo ci) {
        if (level() instanceof ClientLevel level) {
            if (!evalVoidFishing()) return;
            double x = getX();
            double y = getY();
            double z = getZ();

            if (!splashed) {
                splashed = true;

                for (float i = 0; i < 2 * Mth.PI; i += Mth.PI / 30) {
                    float vx = Mth.cos(i) * 0.04f;
                    float vz = Mth.sin(i) * 0.04f;
                    //? < 1.21.9 {
                    level.addParticle(ParticleTypes.DRAGON_BREATH, x, y, z, vx, 0, vz);
                    //?} else {
                    /*level.addParticle(PowerParticleOption.create(ParticleTypes.DRAGON_BREATH, 1F),  x, y, z, vx, 0, vz);
                    *///?}
                }
            }

            //? < 1.21.9 {
            SimpleParticleType particleType = random.nextBoolean() ? ParticleTypes.DRAGON_BREATH : ParticleTypes.END_ROD;
            //?} else {
            /*ParticleOptions particleType = random.nextBoolean() ? PowerParticleOption.create(ParticleTypes.DRAGON_BREATH, 1F) : ParticleTypes.END_ROD;
            *///?}

            double dx = random.nextDouble() * 4 - 2;
            double dy = random.nextDouble() * 2 - 1;
            double dz = random.nextDouble() * 4 - 2;

            level.addParticle(
                    particleType,
                    x + dx,
                    y + dy,
                    z + dz,
                    dx * 0.01,
                    dy * 0.01,
                    dz * 0.01
            );


        }
    }

    @Redirect(method = "catchingFish", at= @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
    private boolean addVoidVishingToWaterCheck(BlockState instance, Block block) {
        return isVoidFishing || instance.is(block);
    }

    @Redirect(method = "catchingFish", at = @At(value = "FIELD", target = "Lnet/minecraft/core/particles/ParticleTypes;BUBBLE:Lnet/minecraft/core/particles/SimpleParticleType;", opcode = Opcodes.GETSTATIC))
    private SimpleParticleType bubbleParticles() {
        return isVoidFishing ? ParticleTypes.DRAGON_BREATH : ParticleTypes.BUBBLE;
    }

    @Redirect(method = "catchingFish", at = @At(value = "FIELD", target = "Lnet/minecraft/core/particles/ParticleTypes;FISHING:Lnet/minecraft/core/particles/SimpleParticleType;", opcode = Opcodes.GETSTATIC))
    private SimpleParticleType fishingParticles() {
        return isVoidFishing ? ParticleTypes.WITCH : ParticleTypes.FISHING;
    }


    @Redirect(method = "catchingFish", at = @At(value = "FIELD", target = "Lnet/minecraft/core/particles/ParticleTypes;SPLASH:Lnet/minecraft/core/particles/SimpleParticleType;", opcode = Opcodes.GETSTATIC))
    private SimpleParticleType splashParticles() {
        return isVoidFishing ? ParticleTypes.WITCH : ParticleTypes.SPLASH;
    }


    @Redirect( method = "catchingFish", at= @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/FishingHook;playSound(Lnet/minecraft/sounds/SoundEvent;FF)V"))
    public void louderSplash(FishingHook instance, SoundEvent soundEvent, float v, float p) {
        instance.playSound(soundEvent, evalVoidFishing() ? 1.5f : v, p);
    }

}