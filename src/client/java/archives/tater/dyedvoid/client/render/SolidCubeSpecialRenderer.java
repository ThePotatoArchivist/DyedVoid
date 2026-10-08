package archives.tater.dyedvoid.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.FaceInfo;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;

import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static net.minecraft.util.Util.makeEnumMap;

public class SolidCubeSpecialRenderer implements NoDataSpecialModelRenderer {
    private static final Vector3fc FROM = new Vector3f(0.0F, 0.0F, 0.0F);
    private static final Vector3fc TO = new Vector3f(1.0F, 1.0F, 1.0F);
    private static final Map<Direction, List<Vector3fc>> FACES = makeEnumMap(Direction.class, direction -> {
        var faceInfo = FaceInfo.fromFacing(direction);
        return List.of(
                faceInfo.getVertexInfo(0).select(FROM, TO),
                faceInfo.getVertexInfo(1).select(FROM, TO),
                faceInfo.getVertexInfo(2).select(FROM, TO),
                faceInfo.getVertexInfo(3).select(FROM, TO)
        );
    });
    private static final List<Direction> ALL_FACES = List.of(Direction.values());

    private final TextureAtlasSprite sprite;
    private final Map<Direction, List<Vector3fc>> uvs;

    public SolidCubeSpecialRenderer(TextureAtlasSprite sprite) {
        this.sprite = sprite;
        uvs = makeEnumMap(Direction.class, direction -> {
            var faceInfo = FaceInfo.fromFacing(direction);
            return List.of(
                    faceInfo.getVertexInfo(0).select(FROM, TO),
                    faceInfo.getVertexInfo(1).select(FROM, TO),
                    faceInfo.getVertexInfo(2).select(FROM, TO),
                    faceInfo.getVertexInfo(3).select(FROM, TO)
            );
        });
    }

    private void submitCube(final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector) {
        submitCube(poseStack, submitNodeCollector, _ -> {});
    }

    private void submitCube(final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final Consumer<VertexConsumer> vertexDecorator) {
        submitNodeCollector.submitCustomGeometry(poseStack, RenderTypes.solidMovingBlock(), (pose, buffer) -> {
            for (var direction : ALL_FACES)
                for (Vector3fc faceVertex : FACES.get(direction))
                    vertexDecorator.accept(buffer.addVertex(pose, faceVertex));
        });
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
        submitCube(poseStack, submitNodeCollector);
    }

    @Override
    public void getExtents(final Consumer<Vector3fc> output) {
        FACES.values().forEach((vertices) -> vertices.forEach(output));
    }

    record Unbaked(Identifier texture) implements NoDataSpecialModelRenderer.Unbaked {
        @Override
        public SpecialModelRenderer<Void> bake(BakingContext context) {
            return new SolidCubeSpecialRenderer(context.sprites().get(new SpriteId(TextureAtlas.LOCATION_BLOCKS, texture)));
        }

        @Override
        public MapCodec<? extends NoDataSpecialModelRenderer.Unbaked> type() {
            return null;
        }
    }
}
