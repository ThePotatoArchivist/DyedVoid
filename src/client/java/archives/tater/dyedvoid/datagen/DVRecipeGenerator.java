package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.DyedVoid;
import archives.tater.dyedvoid.registry.DyedVoidItems;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.ColorCollection;

import java.util.concurrent.CompletableFuture;

public class DVRecipeGenerator extends RecipeProvider {
    protected DVRecipeGenerator(HolderLookup.Provider registries, RecipeOutput exporter) {
        super(registries, exporter);
    }

    @Override
    public void buildRecipes() {
        var whiteVoid = DyedVoidItems.VOID.white();
        var blackVoid = DyedVoidItems.VOID.black();

        ColorCollection.zipApply(DyedVoidItems.VOID, Items.DYE, (voidItem, dyeItem) -> {
            if (voidItem == blackVoid || voidItem == whiteVoid) return;

            shapeless(RecipeCategory.DECORATIONS, voidItem, 8)
                    .requires(whiteVoid, 4)
                    .requires(dyeItem)
                    .requires(whiteVoid, 4)
                    .unlockedBy(getHasName(whiteVoid), has(whiteVoid))
                    .group(DyedVoid.MOD_ID + ":dye_void_block")
                    .save(output);
        });

        shaped(RecipeCategory.DECORATIONS, blackVoid, 4)
                .pattern("##")
                .pattern("##")
                .define('#', DyedVoidItems.VOID_BOTTLE_ITEM)
                .unlockedBy(getHasName(DyedVoidItems.VOID_BOTTLE_ITEM), has(DyedVoidItems.VOID_BOTTLE_ITEM))
                .save(output);

        shapeless(RecipeCategory.DECORATIONS, whiteVoid, 8)
                .requires(blackVoid, 4)
                .requires(Items.GLOW_INK_SAC)
                .requires(blackVoid, 4)
                .unlockedBy(getHasName(blackVoid), has(blackVoid))
                .save(output);
    }

    public static class Provider extends FabricRecipeProvider {

        public Provider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider wrapperLookup, RecipeOutput recipeExporter) {
            return new DVRecipeGenerator(wrapperLookup, recipeExporter);
        }

        @Override
        public String getName() {
            return "Recipes";
        }
    }
}
