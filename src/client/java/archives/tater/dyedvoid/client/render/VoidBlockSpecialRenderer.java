package archives.tater.dyedvoid.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.MovingBlockRenderState;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

import org.joml.Vector3fc;

import java.util.function.Consumer;

public record VoidBlockSpecialRenderer(MovingBlockRenderState state) implements NoDataSpecialModelRenderer {

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
        submitNodeCollector.submitMovingBlock(poseStack, state);
    }

    @Override
    public void getExtents(Consumer<Vector3fc> output) {

    }

    public record Unbaked(Block block) implements NoDataSpecialModelRenderer.Unbaked {
        public static final MapCodec<Unbaked> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                BuiltInRegistries.BLOCK.byNameCodec().fieldOf("block").forGetter(Unbaked::block)
        ).apply(instance, Unbaked::new));

        @Override
        public SpecialModelRenderer<Void> bake(BakingContext context) {
            var state = new MovingBlockRenderState();
            state.blockState = block.defaultBlockState();
            return new VoidBlockSpecialRenderer(state);
        }

        @Override
        public MapCodec<? extends NoDataSpecialModelRenderer.Unbaked> type() {
            return CODEC;
        }
    }
}
