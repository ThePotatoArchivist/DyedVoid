package archives.tater.dyedvoid.client.mixin;

import archives.tater.dyedvoid.registry.DyedVoidBlockTags;
import archives.tater.dyedvoid.registry.DyedVoidItemTags;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
    @WrapWithCondition(
            method = "renderHitOutline",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/LevelRenderer;renderShape(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;Lnet/minecraft/world/phys/shapes/VoxelShape;DDDFFFF)V")
    )
    private boolean hideOutline(PoseStack matrices, VertexConsumer consumer, VoxelShape shape, double x, double y, double z, float red, float green, float blue, float alpha, PoseStack matrices2, VertexConsumer consumer2, Entity entity, double camX, double camY, double camZ, BlockPos pos, BlockState state) {
        return !state.is(DyedVoidBlockTags.HIDDEN_OUTLINE) || !(entity instanceof LivingEntity livingEntity) || livingEntity.getMainHandItem().is(DyedVoidItemTags.HIDDEN_OUTLINE) || livingEntity.getOffhandItem().is(DyedVoidItemTags.HIDDEN_OUTLINE);
    }
}
