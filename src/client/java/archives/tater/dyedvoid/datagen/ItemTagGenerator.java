package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.registry.DyedVoidBlocks;
import archives.tater.dyedvoid.registry.DyedVoidItemTags;
import archives.tater.dyedvoid.registry.DyedVoidItems;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;

import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends FabricTagsProvider.ItemTagsProvider {

    public ItemTagGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture, @Nullable BlockTagsProvider blockTagsProvider) {
        super(output, registryLookupFuture, blockTagsProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        copy(DyedVoidBlocks.VOID_BLOCKS_TAG, DyedVoidItemTags.VOID_BLOCKS);

        valueLookupBuilder(DyedVoidItemTags.NO_GRAVITY)
                .add(DyedVoidItems.VOID_BOTTLE_ITEM)
                .addTag(DyedVoidItemTags.VOID_BLOCKS);

        valueLookupBuilder(DyedVoidItemTags.PLACEABLE_IN_AIR)
            .addTag(DyedVoidItemTags.VOID_BLOCKS);

        valueLookupBuilder(TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("create", "upright_on_belt")))
                .add(DyedVoidItems.VOID_BOTTLE_ITEM);
    }
}
