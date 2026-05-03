package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.registry.DyedVoidBlockItemIds;
import archives.tater.dyedvoid.registry.DyedVoidItemIds;
import archives.tater.dyedvoid.registry.DyedVoidItemTags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends FabricTagsProvider.ItemTagsProvider {

    public ItemTagGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        builder(DyedVoidItemTags.NO_GRAVITY)
                .add(DyedVoidItemIds.VOID_BOTTLE)
                .add(DyedVoidBlockItemIds.ALL_VOID_BLOCKS);

        builder(DyedVoidItemTags.END_VOID_INGREDIENT)
                .add(DyedVoidBlockItemIds.VOID.black());

        builder(TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("create", "upright_on_belt")))
                .add(DyedVoidItemIds.VOID_BOTTLE);
    }
}
