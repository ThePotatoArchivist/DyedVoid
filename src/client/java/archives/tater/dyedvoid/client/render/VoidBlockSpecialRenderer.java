package archives.tater.dyedvoid.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.renderer.FaceInfo;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.util.ToFloatFunction;

import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public record VoidBlockSpecialRenderer(List<BakedVertexInfo> vertices, SpriteGetter sprites, SpriteId sprite) implements NoDataSpecialModelRenderer {

    private static final Vector3fc FROM = new Vector3f(0, 0, 0);
    private static final Vector3fc TO = new Vector3f(1, 1, 1);

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
        var sprite = sprites.get(this.sprite);
        submitNodeCollector.submitCustomGeometry(poseStack, RenderTypes.solidMovingBlock(), (pose, buffer) -> {
            for (var vertex : vertices) buffer
                    .addVertex(pose, vertex.position)
                    .setNormal(pose, vertex.normal)
                    .setColor(0xFFFFFF)
                    .setLight(LightCoordsUtil.FULL_BRIGHT)
                    .setOverlay(overlayCoords)
                    .setUv(vertex.u.applyAsFloat(sprite), vertex.v.applyAsFloat(sprite));
        });
    }

    @Override
    public void getExtents(Consumer<Vector3fc> output) {
        for (var vertex : vertices)
            output.accept(vertex.position);
    }

    private record BakedVertexInfo(Vector3fc position, Vector3fc normal, ToFloatFunction<TextureAtlasSprite> u, ToFloatFunction<TextureAtlasSprite> v) {}

    public record Unbaked(Identifier texture) implements NoDataSpecialModelRenderer.Unbaked {
        public static final MapCodec<Unbaked> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Identifier.CODEC.fieldOf("texture").forGetter(Unbaked::texture)
        ).apply(instance, Unbaked::new));

        @SuppressWarnings("deprecation")
        @Override
        public SpecialModelRenderer<Void> bake(BakingContext context) {
            return new VoidBlockSpecialRenderer(Arrays.stream(Direction.values())
                    .flatMap(direction -> {
                        var faceInfo = FaceInfo.fromFacing(direction);
                        var normal = direction.getUnitVec3f();
                        return Stream.of(
                                new BakedVertexInfo(faceInfo.getVertexInfo(0).select(FROM, TO), normal, TextureAtlasSprite::getU0, TextureAtlasSprite::getV0),
                                new BakedVertexInfo(faceInfo.getVertexInfo(1).select(FROM, TO), normal, TextureAtlasSprite::getU0, TextureAtlasSprite::getV1),
                                new BakedVertexInfo(faceInfo.getVertexInfo(2).select(FROM, TO), normal, TextureAtlasSprite::getU1, TextureAtlasSprite::getV0),
                                new BakedVertexInfo(faceInfo.getVertexInfo(3).select(FROM, TO), normal, TextureAtlasSprite::getU1, TextureAtlasSprite::getV1)
                        );
                    })
                    .toList(),
                    context.sprites(),
                    new SpriteId(TextureAtlas.LOCATION_BLOCKS, texture)
            );
        }

        @Override
        public MapCodec<? extends NoDataSpecialModelRenderer.Unbaked> type() {
            return CODEC;
        }
    }
}
