package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.registry.DyedVoidBlockTags;
import archives.tater.dyedvoid.registry.DyedVoidBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends FabricTagProvider.BlockTagProvider {

    public BlockTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        getOrCreateTagBuilder(DyedVoidBlockTags.VOID_BLOCKS).add(DyedVoidBlocks.VOID_BLOCKS);

        getOrCreateTagBuilder(DyedVoidBlockTags.HIDDEN_OUTLINE).addTag(DyedVoidBlockTags.VOID_BLOCKS);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL).addTag(DyedVoidBlockTags.VOID_BLOCKS);
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE).addTag(DyedVoidBlockTags.VOID_BLOCKS);
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).addTag(DyedVoidBlockTags.VOID_BLOCKS);
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_SHOVEL).addTag(DyedVoidBlockTags.VOID_BLOCKS);
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_HOE).addTag(DyedVoidBlockTags.VOID_BLOCKS);
    }
}
