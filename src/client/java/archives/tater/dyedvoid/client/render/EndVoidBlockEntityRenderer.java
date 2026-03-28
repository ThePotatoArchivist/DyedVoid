package archives.tater.dyedvoid.client.render;

import archives.tater.dyedvoid.block.EndVoidBlock.EndVoidBlockEntity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.AbstractEndPortalRenderer;
import net.minecraft.client.renderer.blockentity.state.EndPortalRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;

public class EndVoidBlockEntityRenderer extends AbstractEndPortalRenderer<EndVoidBlockEntity, EndPortalRenderState> {

    @Override
    public void submit(EndPortalRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        submitCube(state.facesToShow, RenderTypes.endGateway(), poseStack, submitNodeCollector);
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
