package archives.tater.dyedvoid.client.render;

import archives.tater.dyedvoid.DyedVoid;
import archives.tater.dyedvoid.client.mixin.EntityRenderDispatcherAccessor;

import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;

import it.unimi.dsi.fastutil.Pair;

import java.util.List;
import java.util.stream.IntStream;

public class MeatFactoryRenderer {
    public static final Identifier MEAT_FACTORY = DyedVoid.id("meat_factory");
    private static List<? extends RenderInstance<?>> renderStates = List.of();

    public static void register() {
        ResourceLoader.get(PackType.CLIENT_RESOURCES).registerReloadListener(MEAT_FACTORY, new SimplePreparableReloadListener<Void>() {
            @Override
            protected Void prepare(ResourceManager manager, ProfilerFiller profiler) {
                return null;
            }

            @Override
            protected void apply(Void preparations, ResourceManager manager, ProfilerFiller profiler) {
                renderStates = ((EntityRenderDispatcherAccessor) Minecraft.getInstance().getEntityRenderDispatcher()).getRenderers().entrySet().stream()
                        .<Pair<EntityType<?>, LivingEntityRenderer<?, ?, ?>>>mapMulti((entry, yield) -> {
                            if (entry.getValue() instanceof LivingEntityRenderer<?, ?, ?> livingEntityRenderer)
                                yield.accept(Pair.of(entry.getKey(), livingEntityRenderer));
                        })
                        .map(pair -> createInstance(pair.second(), pair.first()))
                        .toList();
            }
        });

        LevelRenderEvents.COLLECT_SUBMITS.register(context -> {
            var submitNodeCollector = context.submitNodeCollector();
            var poseStack = context.poseStack();
            var cameraRenderState = context.levelState().cameraRenderState;

            poseStack.pushPose();
            poseStack.translate(cameraRenderState.pos.reverse());
            poseStack.translate(0, 64, 0);
            for (var instance : renderStates) {
                poseStack.translate(0, 0, instance.width + 2);

                instance.submit(submitNodeCollector, poseStack, cameraRenderState);
            }
            poseStack.popPose();
        });
    }

    private static <S extends LivingEntityRenderState> RenderInstance<S> createInstance(LivingEntityRenderer<?, S, ? extends EntityModel<?>> renderer, EntityType<?> type) {
        return new RenderInstance<>(type.getWidth(), renderer, IntStream.range(0, 64).mapToObj(animationPos -> {
            var state = renderer.createRenderState();
            state.entityType = type;
            state.walkAnimationPos = animationPos / 16f;
            state.walkAnimationSpeed = 0.3f;
            return state;
        }).toList());
    }

    private record RenderInstance<S extends EntityRenderState>(float width, EntityRenderer<?, S> renderer, List<S> states) {
        void submit(SubmitNodeCollector submitNodeCollector, PoseStack poseStack, CameraRenderState cameraRenderState) {
            poseStack.pushPose();
            for (var state : states) {
                poseStack.translate(width + 2, 0, 0);

                renderer.submit(state, poseStack, submitNodeCollector, cameraRenderState);
            }
            poseStack.popPose();
        }
    }
}
