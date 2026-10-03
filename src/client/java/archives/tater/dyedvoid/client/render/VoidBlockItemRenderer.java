package archives.tater.dyedvoid.client.render;

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public record VoidBlockItemRenderer(BlockState state, boolean portal) implements BuiltinItemRendererRegistry.DynamicItemRenderer {
    public VoidBlockItemRenderer(BlockState state) {
        this(state, false);
    }

    @Override
    public void render(ItemStack stack, ItemDisplayContext mode, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
        var dispatcher = Minecraft.getInstance().getBlockRenderer();
        var level = Minecraft.getInstance().level;
        dispatcher.getModelRenderer().tesselateBlock(level, dispatcher.getBlockModel(state), state, BlockPos.ZERO, matrices, vertexConsumers.getBuffer(portal ? RenderType.endPortal() : RenderType.solid()), false, level.random, 0, overlay);
    }
}
