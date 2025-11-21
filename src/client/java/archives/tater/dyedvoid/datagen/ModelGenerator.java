package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.DyedVoid;
import archives.tater.dyedvoid.DyedVoidBlocks;
import archives.tater.dyedvoid.DyedVoidItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.client.data.*;

import java.util.Optional;

public class ModelGenerator extends FabricModelProvider {

    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    private static final Model VOID_BLOCK_MODEL = new Model(Optional.of(DyedVoid.id("block/void_block")), Optional.empty(), TextureKey.ALL);
    private static final TexturedModel.Factory VOID_BLOCK_FACTORY = TexturedModel.makeFactory(TextureMap::all, VOID_BLOCK_MODEL);

    private static final TextureKey OUTLINE = TextureKey.of("outline");
    private static final Model OUTLINE_BLOCK_MODEL = new Model(Optional.of(DyedVoid.id("block/outline_block")), Optional.empty(), TextureKey.TEXTURE, OUTLINE);

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

    private static void registerOutlineBlock(BlockStateModelGenerator modelGenerator, Block block, Block texture, Block outline) {
        var textures = new TextureMap();
        textures.put(TextureKey.TEXTURE, TextureMap.getId(texture));
        textures.put(OUTLINE, TextureMap.getId(outline));
        modelGenerator.blockStateCollector.accept(
                BlockStateModelGenerator.createSingletonBlockState(
                        block,
                        BlockStateModelGenerator.createWeightedVariant(
                                OUTLINE_BLOCK_MODEL.upload(block, textures, modelGenerator.modelCollector)
                        )
                )
        );
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        for (Block block : NORMAL_VOID_BLOCKS) {
            blockStateModelGenerator.registerSingleton(block, VOID_BLOCK_FACTORY);
        }
        blockStateModelGenerator.registerBuiltinWithParticle(DyedVoidBlocks.END_VOID, DyedVoid.id("block/empty"));
        registerOutlineBlock(blockStateModelGenerator, DyedVoidBlocks.SHADOW_VOID, DyedVoidBlocks.BLACK_VOID, DyedVoidBlocks.WHITE_VOID);
        registerOutlineBlock(blockStateModelGenerator, DyedVoidBlocks.INVERTED_SHADOW_VOID, DyedVoidBlocks.WHITE_VOID, DyedVoidBlocks.BLACK_VOID);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.output.accept(DyedVoidItems.END_VOID, ItemModels.basic(ModelIds.getBlockModelId(DyedVoidBlocks.BLACK_VOID)));

//        Models.GENERATED.upload(ModelIds.getItemModelId(DyedVoidItems.DUMMY_END_PORTAL), TextureMap.layer0(DyedVoidBlocks.BLACK_VOID), itemModelGenerator.writer);
//        Models.CUBE_ALL.upload(ModelIds.getItemModelId(DyedVoidItems.DUMMY_END_GATEWAY), TextureMap.all(DyedVoidBlocks.BLACK_VOID), itemModelGenerator.writer);

        itemModelGenerator.register(DyedVoidItems.VOID_BOTTLE_ITEM, Models.GENERATED);
    }
}
