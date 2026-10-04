package archives.tater.dyedvoid;

import archives.tater.dyedvoid.client.render.EndVoidBlockEntityRenderer;
import archives.tater.dyedvoid.client.render.VoidBlockItemRenderer;
import archives.tater.dyedvoid.registry.DyedVoidBlocks;
import archives.tater.dyedvoid.registry.DyedVoidItems;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.world.item.BlockItem;

public class DyedVoidClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRenderers.register(DyedVoidBlocks.END_VOID_BLOCK_ENTITY, EndVoidBlockEntityRenderer::new);

        var portal = new VoidBlockItemRenderer(DyedVoidBlocks.BLACK_VOID.defaultBlockState(), true);
        for (var item : DyedVoidItems.VOID_BLOCKS)
            BuiltinItemRendererRegistry.INSTANCE.register(item, item == DyedVoidItems.END_VOID ? portal : new VoidBlockItemRenderer(((BlockItem) item).getBlock().defaultBlockState()));
        BuiltinItemRendererRegistry.INSTANCE.register(DyedVoidItems.DUMMY_END_GATEWAY, portal);
        BuiltinItemRendererRegistry.INSTANCE.register(DyedVoidItems.DUMMY_END_PORTAL, portal);

        BlockRenderLayerMap.INSTANCE.putBlock(DyedVoidBlocks.CLOUD_VOID, RenderType.translucent());
    }
}
