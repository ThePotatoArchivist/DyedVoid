package archives.tater.dyedvoid;

import archives.tater.dyedvoid.client.render.EndVoidBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.item.ItemStack;

public class DyedVoidClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRenderers.register(DyedVoidBlocks.END_VOID_BLOCK_ENTITY, ctx -> new EndVoidBlockEntityRenderer());
    }

    public static boolean isFlatRendered(ItemStack itemStack) {
        for (var item : DyedVoidItems.VOID_BLOCKS) {
            if (item != DyedVoidItems.END_VOID && itemStack.is(item)) return true;
        }
        return false;
    }

    public static boolean isPortalRendered(ItemStack itemStack) {
        return itemStack.is(DyedVoidItems.END_VOID) ; //|| itemStack.isOf(DyedVoidItems.DUMMY_END_PORTAL) || itemStack.isOf(DyedVoidItems.DUMMY_END_GATEWAY);
    }
}
