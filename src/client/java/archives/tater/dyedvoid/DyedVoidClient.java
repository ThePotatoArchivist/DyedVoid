package archives.tater.dyedvoid;

import archives.tater.dyedvoid.client.render.EndVoidBlockEntityRenderer;
import archives.tater.dyedvoid.client.render.VoidBlockSpecialRenderer;
import archives.tater.dyedvoid.registry.DyedVoidBlocks;
import archives.tater.dyedvoid.registry.DyedVoidItems;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.special.SpecialModelRenderers;
import net.minecraft.world.item.ItemStack;

public class DyedVoidClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRenderers.register(DyedVoidBlocks.END_VOID_BLOCK_ENTITY, ctx -> new EndVoidBlockEntityRenderer());

        SpecialModelRenderers.ID_MAPPER.put(DyedVoid.id("void_block"), VoidBlockSpecialRenderer.Unbaked.CODEC);
    }
}
