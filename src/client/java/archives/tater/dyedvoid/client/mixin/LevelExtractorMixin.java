package archives.tater.dyedvoid.client.mixin;

import archives.tater.dyedvoid.DyedVoid;
import archives.tater.dyedvoid.DyedVoidClient;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.Camera;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@Mixin(LevelRenderer.class)
public class LevelExtractorMixin {
    @WrapOperation(
            method = "extractBlockOutline",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;isAir()Z")
    )
    private boolean outlinePlaceInAir(BlockState instance, Operation<Boolean> original, Camera camera) {
        return original.call(instance) && !(camera.entity() instanceof LivingEntity livingEntity && DyedVoid.canPlaceInAir(livingEntity))
                || DyedVoidClient.isHiddenOutline(instance) && !(camera.entity() instanceof LivingEntity livingEntity && DyedVoidClient.seesHiddenOutlines(livingEntity));
    }

    @WrapOperation(
            method = "extractBlockOutline",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getShape(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;")
    )
    private VoxelShape airShape(BlockState instance, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext, Operation<VoxelShape> original) {
        var result = original.call(instance, blockGetter, blockPos, collisionContext);
        return instance.canBeReplaced() && result.isEmpty() ? Shapes.block() : result;
    }
}
