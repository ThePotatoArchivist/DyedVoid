package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.DyedVoid;
import archives.tater.dyedvoid.DyedVoidBlocks;
import archives.tater.dyedvoid.DyedVoidItems;
import archives.tater.dyedvoid.client.render.VoidBlockSpecialRenderer;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.special.EndCubeSpecialRenderer;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

import static net.minecraft.client.data.models.model.ItemModelUtils.specialModel;

public class ModelGenerator extends FabricModelProvider {

    public ModelGenerator(FabricPackOutput output) {
        super(output);
    }

    private static final ModelTemplate VOID_BLOCK_MODEL = new ModelTemplate(Optional.of(DyedVoid.id("block/void_block")), Optional.empty(), TextureSlot.ALL);
    private static final TexturedModel.Provider VOID_BLOCK_FACTORY = TexturedModel.createDefault(TextureMapping::cube, VOID_BLOCK_MODEL);

    private static final TextureSlot OUTLINE = TextureSlot.create("outline");
    private static final ModelTemplate OUTLINE_BLOCK_MODEL = new ModelTemplate(Optional.of(DyedVoid.id("block/outline_block")), Optional.empty(), TextureSlot.TEXTURE, OUTLINE);

    private static final Identifier BLOCK_BASE = Identifier.withDefaultNamespace("block/block");

    private static final Block[] NORMAL_VOID_BLOCKS = {
            DyedVoidBlocks.BLACK_VOID,
            DyedVoidBlocks.WHITE_VOID,
            DyedVoidBlocks.LIGHT_GRAY_VOID,
            DyedVoidBlocks.GRAY_VOID,
            DyedVoidBlocks.BROWN_VOID,
            DyedVoidBlocks.RED_VOID,
            DyedVoidBlocks.ORANGE_VOID,
            DyedVoidBlocks.YELLOW_VOID,
            DyedVoidBlocks.LIME_VOID,
            DyedVoidBlocks.GREEN_VOID,
            DyedVoidBlocks.CYAN_VOID,
            DyedVoidBlocks.LIGHT_BLUE_VOID,
            DyedVoidBlocks.BLUE_VOID,
            DyedVoidBlocks.PURPLE_VOID,
            DyedVoidBlocks.MAGENTA_VOID,
            DyedVoidBlocks.PINK_VOID,
    };

    private static void registerOutlineBlock(BlockModelGenerators modelGenerator, Block block, Block texture, Block outline) {
        var textures = new TextureMapping();
        textures.put(TextureSlot.TEXTURE, TextureMapping.getBlockTexture(texture));
        textures.put(OUTLINE, TextureMapping.getBlockTexture(outline));
        modelGenerator.blockStateOutput.accept(
                BlockModelGenerators.createSimpleBlock(
                        block,
                        BlockModelGenerators.plainVariant(
                                OUTLINE_BLOCK_MODEL.create(block, textures, modelGenerator.modelOutput)
                        )
                )
        );
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        for (var block : NORMAL_VOID_BLOCKS) {
            blockStateModelGenerator.createTrivialBlock(block, VOID_BLOCK_FACTORY);
        }
        blockStateModelGenerator.createAirLikeBlock(DyedVoidBlocks.END_VOID, new Material(DyedVoid.id("block/empty")));
        registerOutlineBlock(blockStateModelGenerator, DyedVoidBlocks.SHADOW_VOID, DyedVoidBlocks.BLACK_VOID, DyedVoidBlocks.WHITE_VOID);
        registerOutlineBlock(blockStateModelGenerator, DyedVoidBlocks.INVERTED_SHADOW_VOID, DyedVoidBlocks.WHITE_VOID, DyedVoidBlocks.BLACK_VOID);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        for (var block : DyedVoidBlocks.VOID_BLOCKS) {
            itemModelGenerator.itemModelOutput.accept(block.asItem(), specialModel(
                    BLOCK_BASE,
                    block == DyedVoidBlocks.END_VOID ? new EndCubeSpecialRenderer.Unbaked(EndCubeSpecialRenderer.Type.PORTAL) : new VoidBlockSpecialRenderer.Unbaked(block)
            ));
        }

        itemModelGenerator.itemModelOutput.accept(DyedVoidItems.DUMMY_END_GATEWAY, specialModel(BLOCK_BASE, new EndCubeSpecialRenderer.Unbaked(EndCubeSpecialRenderer.Type.GATEWAY)));
        itemModelGenerator.itemModelOutput.accept(DyedVoidItems.DUMMY_END_PORTAL, specialModel(Identifier.withDefaultNamespace("item/generated"), new EndCubeSpecialRenderer.Unbaked(EndCubeSpecialRenderer.Type.GATEWAY)));

        itemModelGenerator.generateFlatItem(DyedVoidItems.VOID_BOTTLE_ITEM, ModelTemplates.FLAT_ITEM);
    }
}
