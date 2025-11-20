package xyz.kohara.stellarity.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.projectile.FishingHook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingHook.class)
public class VoidFishingMixin {
	@Inject(method = "Lnet/minecraft/world/entity/projectile/FishingHook;tick()V", at = @At("HEAD"))
	private void init(CallbackInfo info) {

	}
}