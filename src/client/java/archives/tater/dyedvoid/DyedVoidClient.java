package archives.tater.dyedvoid;

import archives.tater.dyedvoid.client.render.EndVoidBlockEntityRenderer;
import archives.tater.dyedvoid.client.render.VoidBlockSpecialRenderer;
import archives.tater.dyedvoid.registry.DyedVoidBlockTags;
import archives.tater.dyedvoid.registry.DyedVoidBlocks;
import archives.tater.dyedvoid.registry.DyedVoidItemTags;

import net.fabricmc.api.ClientModInitializer;

import net.minecraft.client.renderer.block.BuiltInBlockModels;
import net.minecraft.client.renderer.block.model.SpecialBlockModelWrapper;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.special.EndCubeSpecialRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class DyedVoidClient implements ClientModInitializer {
    public static void initBuiltinBlockModels(BuiltInBlockModels.Builder builder) {
        for (var block : DyedVoidBlocks.VOID_BLOCKS)
            builder.put((BuiltInBlockModels.ModelFactory) (_, _) ->
                    new SpecialBlockModelWrapper.Unbaked<>(getModel(block), Optional.empty()), block);
    }

    public static SpecialModelRenderer.@NonNull Unbaked<Void> getModel(Block block) {
        return block == DyedVoidBlocks.END_VOID
                ? new EndCubeSpecialRenderer.Unbaked(EndCubeSpecialRenderer.Type.PORTAL)
                : new VoidBlockSpecialRenderer.Unbaked(block);
    }

    public static boolean isHiddenOutline(BlockState state) {
        return state.is(DyedVoidBlockTags.HIDDEN_OUTLINE);
    }

    public static boolean seesHiddenOutlines(LivingEntity entity) {
        return entity.getMainHandItem().is(DyedVoidItemTags.HIDDEN_OUTLINE) || entity.getOffhandItem().is(DyedVoidItemTags.HIDDEN_OUTLINE);
    }

    @Override
    public void onInitializeClient() {
        BlockEntityRenderers.register(DyedVoidBlocks.END_VOID_BLOCK_ENTITY, _ -> new EndVoidBlockEntityRenderer());

        SpecialModelRenderers.ID_MAPPER.put(DyedVoid.id("void_block"), VoidBlockSpecialRenderer.Unbaked.CODEC);
    }
}
