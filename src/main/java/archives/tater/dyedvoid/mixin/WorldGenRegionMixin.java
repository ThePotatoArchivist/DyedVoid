package archives.tater.dyedvoid.mixin;

import archives.tater.dyedvoid.DyedVoidEnvironmentAttributes;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;

@Mixin(WorldGenRegion.class)
public abstract class WorldGenRegionMixin {

    @Shadow
    @Final
    private ServerLevel level;

    @Inject(
            method = "addFreshEntity",
            at = @At("HEAD"),
            cancellable = true
    )
    private void preventSpawnAttribute(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof Mob && !level.environmentAttributes().getValue(DyedVoidEnvironmentAttributes.WORLDGEN_MOB_SPAWN, entity.position()))
            cir.cancel();
    }
}
