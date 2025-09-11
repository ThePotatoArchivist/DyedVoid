package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.DyedVoid;
import archives.tater.dyedvoid.DyedVoidBlocks;
import archives.tater.dyedvoid.DyedVoidItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;

import java.util.Optional;

public class ModelGenerator extends FabricModelProvider {

    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    private static final Model VOID_BLOCK_MODEL = new Model(Optional.of(DyedVoid.id("block/void_block")), Optional.empty(), TextureKey.ALL);
    private static final TexturedModel.Factory VOID_BLOCK_FACTORY = TexturedModel.makeFactory(TextureMap::all, VOID_BLOCK_MODEL);

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        for (Block block : DyedVoidBlocks.VOID_BLOCKS) {
            if (block == DyedVoidBlocks.END_VOID) continue; // Skip
            blockStateModelGenerator.registerSingleton(block, VOID_BLOCK_FACTORY);
        }
        blockStateModelGenerator.registerBuiltinWithParticle(DyedVoidBlocks.END_VOID, DyedVoid.id("block/empty"));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        Models.CUBE_ALL.upload(ModelIds.getItemModelId(DyedVoidItems.END_VOID), TextureMap.all(DyedVoidBlocks.BLACK_VOID), itemModelGenerator.writer);
        Models.GENERATED.upload(ModelIds.getItemModelId(DyedVoidItems.DUMMY_END_PORTAL), TextureMap.layer0(DyedVoidBlocks.BLACK_VOID), itemModelGenerator.writer);
        Models.CUBE_ALL.upload(ModelIds.getItemModelId(DyedVoidItems.DUMMY_END_GATEWAY), TextureMap.all(DyedVoidBlocks.BLACK_VOID), itemModelGenerator.writer);

        itemModelGenerator.register(DyedVoidItems.VOID_BOTTLE_ITEM, Models.GENERATED);
    }
}
