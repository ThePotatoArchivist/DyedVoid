package archives.tater.dyedvoid;

import archives.tater.dyedvoid.client.render.BlockStateModelRenderer;
import archives.tater.dyedvoid.client.render.EndVoidBlockEntityRenderer;

import net.fabricmc.api.ClientModInitializer;

import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.item.model.special.SpecialModelTypes;
import net.minecraft.util.Identifier;

public class DyedVoidClient implements ClientModInitializer {
    public static final Identifier BLOCK_STATE_ITEM_MODEL = DyedVoid.id("block_state");

    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(DyedVoidBlocks.END_VOID_BLOCK_ENTITY, ctx -> new EndVoidBlockEntityRenderer());

        SpecialModelTypes.ID_MAPPER.put(BLOCK_STATE_ITEM_MODEL, BlockStateModelRenderer.Unbaked.CODEC);
    }

}
