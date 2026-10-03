package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.registry.DyedVoidBlockTags;
import archives.tater.dyedvoid.registry.DyedVoidItemTags;
import archives.tater.dyedvoid.registry.DyedVoidItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends FabricTagProvider.ItemTagProvider {

    public ItemTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
        super(output, completableFuture, blockTagProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        copy(DyedVoidBlockTags.VOID_BLOCKS, DyedVoidItemTags.VOID_BLOCKS);

        getOrCreateTagBuilder(DyedVoidItemTags.NO_GRAVITY)
                .add(DyedVoidItems.VOID_BOTTLE_ITEM)
                .addTag(DyedVoidItemTags.VOID_BLOCKS);

        getOrCreateTagBuilder(DyedVoidItemTags.HIDDEN_OUTLINE)
                .addTag(DyedVoidItemTags.VOID_BLOCKS);

        getOrCreateTagBuilder(TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("create", "upright_on_belt")))
                .add(DyedVoidItems.VOID_BOTTLE_ITEM);
    }
}
