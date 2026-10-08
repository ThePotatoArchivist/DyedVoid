package archives.tater.dyedvoid.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;

import org.joml.Vector3fc;

import java.util.function.Consumer;

public class SkyVoidSpecialRenderer implements NoDataSpecialModelRenderer {
    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
        SkyVoidRenderer.submitSpecial(poseStack, submitNodeCollector, outlineColor);
    }

    @Override
    public void getExtents(Consumer<Vector3fc> output) {
        SkyVoidRenderer.getExtents(output);
    }

    public record Unbaked() implements NoDataSpecialModelRenderer.Unbaked {
        public static final MapCodec<Unbaked> CODEC = MapCodec.unit(new Unbaked());

        @Override
        public SpecialModelRenderer<Void> bake(final SpecialModelRenderer.BakingContext context) {
            return new SkyVoidSpecialRenderer();
        }

        public MapCodec<Unbaked> type() {
            return CODEC;
        }
    }
}
