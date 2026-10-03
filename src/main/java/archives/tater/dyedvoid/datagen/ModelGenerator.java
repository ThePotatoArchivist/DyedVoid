package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.DyedVoid;
import archives.tater.dyedvoid.block.SkyVoidBlock;
import archives.tater.dyedvoid.registry.DyedVoidBlocks;
import archives.tater.dyedvoid.registry.DyedVoidItems;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;

import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.*;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

import static net.minecraft.data.models.BlockModelGenerators.createBooleanModelDispatch;
import static net.minecraft.data.models.blockstates.MultiVariantGenerator.multiVariant;

public class ModelGenerator extends FabricModelProvider {

    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    private static final ModelTemplate VOID_BLOCK_MODEL = new ModelTemplate(Optional.of(DyedVoid.id("block/template_void")), Optional.empty(), TextureSlot.ALL);
    private static final TexturedModel.Provider VOID_BLOCK_FACTORY = TexturedModel.createDefault(TextureMapping::cube, VOID_BLOCK_MODEL);
    private static final ModelTemplate VOID_BLOCK_ITEM_MODEL = new ModelTemplate(Optional.of(DyedVoid.id("item/template_void")), Optional.empty());

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        for (Block block : DyedVoidBlocks.VOID_BLOCKS) {
            if (block == DyedVoidBlocks.END_VOID || block == DyedVoidBlocks.SKY_VOID) continue; // Skip
            blockStateModelGenerator.createTrivialBlock(block, VOID_BLOCK_FACTORY);
        }
        blockStateModelGenerator.createAirLikeBlock(DyedVoidBlocks.END_VOID, DyedVoid.id("block/empty"));

        blockStateModelGenerator.blockStateOutput.accept(multiVariant(DyedVoidBlocks.SKY_VOID).with(createBooleanModelDispatch(
                SkyVoidBlock.NIGHT,
                blockStateModelGenerator.createSuffixedVariant(DyedVoidBlocks.SKY_VOID, "_night", VOID_BLOCK_MODEL, TextureMapping::cube),
                VOID_BLOCK_FACTORY.create(DyedVoidBlocks.SKY_VOID, blockStateModelGenerator.modelOutput)
        )));
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        for (var item : DyedVoidItems.VOID_BLOCKS)
            itemModelGenerator.generateFlatItem(item, VOID_BLOCK_ITEM_MODEL);

        itemModelGenerator.generateFlatItem(DyedVoidItems.DUMMY_END_GATEWAY, VOID_BLOCK_ITEM_MODEL);

        itemModelGenerator.generateFlatItem(DyedVoidItems.VOID_BOTTLE_ITEM, ModelTemplates.FLAT_ITEM);
    }
}
