package archives.tater.dyedvoid.client.render;

import archives.tater.dyedvoid.EndVoidBlock.EndVoidBlockEntity;

import net.minecraft.client.render.block.entity.AbstractEndPortalBlockEntityRenderer;
import net.minecraft.client.render.block.entity.state.EndPortalBlockEntityRenderState;

public class EndVoidBlockEntityRenderer extends AbstractEndPortalBlockEntityRenderer<EndVoidBlockEntity, EndPortalBlockEntityRenderState> {

    @Override
    protected float getTopYOffset() {
        return 1.0F;
    }

    @Override
    protected float getBottomYOffset() {
        return 0.0F;
    }

    @Override
    public int getRenderDistance() {
        return 256;
    }

    @Override
    public EndPortalBlockEntityRenderState createRenderState() {
        return new EndPortalBlockEntityRenderState();
    }
}
