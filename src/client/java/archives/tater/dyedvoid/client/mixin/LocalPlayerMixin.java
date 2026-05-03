package archives.tater.dyedvoid.client.mixin;

import archives.tater.dyedvoid.DyedVoid;

import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin extends AbstractClientPlayer {
    public LocalPlayerMixin(ClientLevel level, GameProfile gameProfile) {
        super(level, gameProfile);
    }

    @ModifyArg(
            method = "pick",
            at = @At(value = "INVOKE:LAST", target = "Lnet/minecraft/client/player/LocalPlayer;filterHitResult(Lnet/minecraft/world/phys/HitResult;Lnet/minecraft/world/phys/Vec3;D)Lnet/minecraft/world/phys/HitResult;"),
            index = 2
    )
    private static double allowMinisculeDistance(double maxRange, @Local(argsOnly = true, name = "cameraEntity") Entity cameraEntity) {
        return cameraEntity instanceof LivingEntity livingEntity && DyedVoid.canPlaceInAir(livingEntity)
                ? maxRange + 0.001
                : maxRange;
    }
}
