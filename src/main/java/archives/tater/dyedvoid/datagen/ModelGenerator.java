package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.DyedVoid;
import archives.tater.dyedvoid.DyedVoidBlocks;
import archives.tater.dyedvoid.DyedVoidItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.world.level.block.Block;
import java.util.Optional;

public class ModelGenerator extends FabricModelProvider {

    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    private static final ModelTemplate VOID_BLOCK_MODEL = new ModelTemplate(Optional.of(DyedVoid.id("block/void_block")), Optional.empty(), TextureSlot.ALL);
    private static final TexturedModel.Provider VOID_BLOCK_FACTORY = TexturedModel.createDefault(TextureMapping::cube, VOID_BLOCK_MODEL);

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        for (Block block : DyedVoidBlocks.VOID_BLOCKS) {
            if (block == DyedVoidBlocks.END_VOID) continue; // Skip
            blockStateModelGenerator.createTrivialBlock(block, VOID_BLOCK_FACTORY);
        }
        blockStateModelGenerator.createAirLikeBlock(DyedVoidBlocks.END_VOID, DyedVoid.id("block/empty"));
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        ModelTemplates.CUBE_ALL.create(ModelLocationUtils.getModelLocation(DyedVoidItems.END_VOID), TextureMapping.cube(DyedVoidBlocks.BLACK_VOID), itemModelGenerator.output);
        ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(DyedVoidItems.DUMMY_END_PORTAL), TextureMapping.layer0(DyedVoidBlocks.BLACK_VOID), itemModelGenerator.output);
        ModelTemplates.CUBE_ALL.create(ModelLocationUtils.getModelLocation(DyedVoidItems.DUMMY_END_GATEWAY), TextureMapping.cube(DyedVoidBlocks.BLACK_VOID), itemModelGenerator.output);

        itemModelGenerator.generateFlatItem(DyedVoidItems.VOID_BOTTLE_ITEM, ModelTemplates.FLAT_ITEM);
    }
}
