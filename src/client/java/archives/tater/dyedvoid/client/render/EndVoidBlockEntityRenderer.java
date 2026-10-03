package archives.tater.dyedvoid.client.render;

import archives.tater.dyedvoid.EndVoidBlock.EndVoidBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;

public class EndVoidBlockEntityRenderer extends TheEndPortalRenderer<EndVoidBlockEntity> {
    public EndVoidBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    protected float getOffsetUp() {
        return 1.0F;
    }

    @Override
    protected float getOffsetDown() {
        return 0.0F;
    }

    @Override
    public int getViewDistance() {
        return 256;
    }
}
