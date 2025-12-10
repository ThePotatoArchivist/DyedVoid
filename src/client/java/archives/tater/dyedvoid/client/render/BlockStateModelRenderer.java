package archives.tater.dyedvoid.client.render;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.MovingBlockRenderState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlockStateComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;

import org.joml.Vector3fc;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public class BlockStateModelRenderer implements SpecialModelRenderer<MovingBlockRenderState> {
    @Override
    public void render(@Nullable MovingBlockRenderState data, ItemDisplayContext displayContext, MatrixStack matrices, OrderedRenderCommandQueue queue, int light, int overlay, boolean glint, int i) {
        queue.submitMovingBlock(matrices, data);
    }

    @Override
    public void collectVertices(Consumer<Vector3fc> consumer) {

    }

    @Override
    public @Nullable MovingBlockRenderState getData(ItemStack stack) {
        if (!(stack.getItem() instanceof BlockItem blockItem)) return null;
        var state = new MovingBlockRenderState();
        state.world = MinecraftClient.getInstance().world;
        state.blockState = stack.getOrDefault(DataComponentTypes.BLOCK_STATE, BlockStateComponent.DEFAULT).applyToState(blockItem.getBlock().getDefaultState());
        return state;
    }

    public record Unbaked() implements SpecialModelRenderer.Unbaked {
        public static final MapCodec<Unbaked> CODEC = MapCodec.unit(new Unbaked());

        @Override
        public SpecialModelRenderer<?> bake(BakeContext context) {
            return new BlockStateModelRenderer();
        }

        @Override
        public MapCodec<? extends SpecialModelRenderer.Unbaked> getCodec() {
            return CODEC;
        }
    }
}
