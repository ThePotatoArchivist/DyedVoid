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
    private static List<? extends List<? extends RenderInstance<?>>> renderStates = List.of();

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
                        .map(pair -> {
                            var type = pair.first();
                            var renderer = pair.second();
                            return IntStream.range(0, 64).mapToObj(animationPos -> createInstance(animationPos, renderer, type)).toList();
                        })
                        .toList();
            }
        });

        LevelRenderEvents.COLLECT_SUBMITS.register(context -> {
            for (var list : renderStates)
                for (var state : list)
                    state.submit(context.submitNodeCollector(), context.poseStack(), context.levelState().cameraRenderState);
        });
    }

    private static <S extends LivingEntityRenderState> RenderInstance<S> createInstance(int animationPos, LivingEntityRenderer<?, S, ? extends EntityModel<?>> renderer, EntityType<?> type) {
        var state = renderer.createRenderState();
        state.entityType = type;
        state.walkAnimationPos = animationPos / 16f;
        return new RenderInstance<>(renderer, state);
    }

    private record RenderInstance<S extends EntityRenderState>(EntityRenderer<?, S> renderer, S state) {
        void submit(SubmitNodeCollector submitNodeCollector, PoseStack poseStack, CameraRenderState cameraRenderState) {
            renderer.submit(state, poseStack, submitNodeCollector, cameraRenderState);
        }
    }
}
