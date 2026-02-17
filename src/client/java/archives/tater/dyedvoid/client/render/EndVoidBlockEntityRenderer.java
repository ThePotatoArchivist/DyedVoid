package archives.tater.dyedvoid.client.render;

import archives.tater.dyedvoid.EndVoidBlock.EndVoidBlockEntity;
import net.minecraft.client.renderer.blockentity.AbstractEndPortalRenderer;
import net.minecraft.client.renderer.blockentity.state.EndPortalRenderState;

public class EndVoidBlockEntityRenderer extends AbstractEndPortalRenderer<EndVoidBlockEntity, EndPortalRenderState> {

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

    @Override
    public EndPortalRenderState createRenderState() {
        return new EndPortalRenderState();
    }
}
