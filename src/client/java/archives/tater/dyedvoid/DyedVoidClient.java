package archives.tater.dyedvoid;

import archives.tater.dyedvoid.client.render.EndVoidBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.item.ItemStack;

public class DyedVoidClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(DyedVoidBlocks.END_VOID_BLOCK_ENTITY, EndVoidBlockEntityRenderer::new);
    }

    public static boolean isFlatRendered(ItemStack itemStack) {
        for (var item : DyedVoidItems.VOID_BLOCKS) {
            if (item != DyedVoidItems.END_VOID && itemStack.isOf(item)) return true;
        }
        return false;
    }

    public static boolean isPortalRendered(ItemStack itemStack) {
        return itemStack.isOf(DyedVoidItems.END_VOID) ; //|| itemStack.isOf(DyedVoidItems.DUMMY_END_PORTAL) || itemStack.isOf(DyedVoidItems.DUMMY_END_GATEWAY);
    }
}
