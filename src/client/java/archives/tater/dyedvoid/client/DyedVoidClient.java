package archives.tater.dyedvoid.client;

import archives.tater.dyedvoid.DyedVoid;
import archives.tater.dyedvoid.client.render.EndVoidRenderer;
import archives.tater.dyedvoid.client.render.SkyVoidRenderer;
import archives.tater.dyedvoid.client.render.SkyVoidSpecialRenderer;
import archives.tater.dyedvoid.client.render.VoidBlockSpecialRenderer;
import archives.tater.dyedvoid.registry.DyedVoidBlockEntities;
import archives.tater.dyedvoid.registry.DyedVoidBlockItemTags;
import archives.tater.dyedvoid.registry.DyedVoidBlocks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltInBlockModelsCallback;

import net.minecraft.client.renderer.block.BuiltInBlockModels;
import net.minecraft.client.renderer.block.model.SpecialBlockModelWrapper;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.special.EndCubeSpecialRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class DyedVoidClient implements ClientModInitializer {
    public static SpecialModelRenderer.Unbaked<Void> getModel(BlockState state) {
        return
                state.is(DyedVoidBlocks.END_VOID) ? new EndCubeSpecialRenderer.Unbaked(EndCubeSpecialRenderer.Type.PORTAL) :
                state.is(DyedVoidBlocks.SKY_VOID) ? new SkyVoidSpecialRenderer.Unbaked() :
                new VoidBlockSpecialRenderer.Unbaked(state);
    }

    public static boolean isHiddenOutline(BlockState state) {
        return state.is(DyedVoidBlockItemTags.HIDDEN_OUTLINE.block());
    }

    public static boolean seesHiddenOutlines(LivingEntity entity) {
        return entity.getMainHandItem().is(DyedVoidBlockItemTags.HIDDEN_OUTLINE.item()) || entity.getOffhandItem().is(DyedVoidBlockItemTags.HIDDEN_OUTLINE.item());
    }

    @Override
    public void onInitializeClient() {
        BlockEntityRenderers.register(DyedVoidBlockEntities.END_VOID, _ -> new EndVoidRenderer());
        BlockEntityRenderers.register(DyedVoidBlockEntities.SKY_VOID, _ -> new SkyVoidRenderer());

        SpecialModelRenderers.ID_MAPPER.put(DyedVoid.id("void_block"), VoidBlockSpecialRenderer.Unbaked.CODEC);
        SpecialModelRenderers.ID_MAPPER.put(DyedVoid.id("sky_void"), SkyVoidSpecialRenderer.Unbaked.CODEC);

        BuiltInBlockModelsCallback.EVENT.register(builder -> {
            for (var block : DyedVoidBlocks.ALL_VOID_BLOCKS)
                builder.put((BuiltInBlockModels.ModelFactory) (_, state) ->
                        new SpecialBlockModelWrapper.Unbaked<>(getModel(state), Optional.empty()), block);
        });
    }
}
