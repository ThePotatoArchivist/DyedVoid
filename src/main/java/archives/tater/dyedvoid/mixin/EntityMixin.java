package archives.tater.dyedvoid.mixin;

import archives.tater.dyedvoid.DyedVoid;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow
    public abstract Level level();

    @SuppressWarnings("ConstantValue")
    @ModifyReturnValue(
            method = "pick",
            at = @At("RETURN")
    )
    private HitResult placeInAir(HitResult original) {
        return (Object) this instanceof LivingEntity livingEntity
                && original.getType() == HitResult.Type.MISS
                && original instanceof BlockHitResult blockHitResult
                && level().getBlockState(blockHitResult.getBlockPos()).canBeReplaced()
                && DyedVoid.canPlaceInAir(livingEntity)
                ? new BlockHitResult(
                        blockHitResult.getLocation(),
                        blockHitResult.getDirection(),
                        blockHitResult.getBlockPos(),
                        blockHitResult.isInside(),
                        blockHitResult.isWorldBorderHit()
                )
                : original;
    }

}
