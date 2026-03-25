package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.DyedVoidBlocks;
import archives.tater.dyedvoid.DyedVoidItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static java.util.Map.entry;

public class DVRecipeGenerator extends RecipeProvider {
    Map<Item, Item> dyes = Map.ofEntries(
            entry(DyedVoidItems.ORANGE_VOID, Items.ORANGE_DYE),
            entry(DyedVoidItems.MAGENTA_VOID, Items.MAGENTA_DYE),
            entry(DyedVoidItems.LIGHT_BLUE_VOID, Items.LIGHT_BLUE_DYE),
            entry(DyedVoidItems.YELLOW_VOID, Items.YELLOW_DYE),
            entry(DyedVoidItems.LIME_VOID, Items.LIME_DYE),
            entry(DyedVoidItems.PINK_VOID, Items.PINK_DYE),
            entry(DyedVoidItems.GRAY_VOID, Items.GRAY_DYE),
            entry(DyedVoidItems.LIGHT_GRAY_VOID, Items.LIGHT_GRAY_DYE),
            entry(DyedVoidItems.CYAN_VOID, Items.CYAN_DYE),
            entry(DyedVoidItems.PURPLE_VOID, Items.PURPLE_DYE),
            entry(DyedVoidItems.BLUE_VOID, Items.BLUE_DYE),
            entry(DyedVoidItems.BROWN_VOID, Items.BROWN_DYE),
            entry(DyedVoidItems.GREEN_VOID, Items.GREEN_DYE),
            entry(DyedVoidItems.RED_VOID, Items.RED_DYE)
    );

    protected DVRecipeGenerator(HolderLookup.Provider registries, RecipeOutput exporter) {
        super(registries, exporter);
    }


    @Override
    public void buildRecipes() {
        dyes.forEach((voidItem, dyeItem) -> shapeless(RecipeCategory.DECORATIONS, voidItem, 8)
                .requires(DyedVoidItems.WHITE_VOID, 4)
                .requires(dyeItem)
                .requires(DyedVoidItems.WHITE_VOID, 4)
                .unlockedBy(getHasName(DyedVoidItems.WHITE_VOID), has(DyedVoidItems.WHITE_VOID))
                .group("dye_void_block")
                .save(output)
        );

        shaped(RecipeCategory.DECORATIONS, DyedVoidBlocks.BLACK_VOID, 4)
                .pattern("##")
                .pattern("##")
                .define('#', DyedVoidItems.VOID_BOTTLE_ITEM)
                .unlockedBy(getHasName(DyedVoidItems.VOID_BOTTLE_ITEM), has(DyedVoidItems.VOID_BOTTLE_ITEM))
                .save(output);

        shapeless(RecipeCategory.DECORATIONS, DyedVoidItems.WHITE_VOID, 8)
                .requires(DyedVoidItems.BLACK_VOID, 4)
                .requires(Items.GLOW_INK_SAC)
                .requires(DyedVoidItems.BLACK_VOID, 4)
                .unlockedBy(getHasName(DyedVoidItems.BLACK_VOID), has(DyedVoidItems.BLACK_VOID))
                .save(output);

        shaped(RecipeCategory.DECORATIONS, DyedVoidItems.SHADOW_VOID, 4)
                .pattern("$#$")
                .pattern("###")
                .pattern("$#$")
                .define('#', DyedVoidItems.BLACK_VOID)
                .define('$', DyedVoidItems.WHITE_VOID)
                .unlockedBy(getHasName(DyedVoidItems.WHITE_VOID), has(DyedVoidItems.WHITE_VOID))
                .save(output);

        shaped(RecipeCategory.DECORATIONS, DyedVoidItems.INVERTED_SHADOW_VOID, 4)
                .pattern("$#$")
                .pattern("###")
                .pattern("$#$")
                .define('#', DyedVoidItems.WHITE_VOID)
                .define('$', DyedVoidItems.BLACK_VOID)
                .unlockedBy(getHasName(DyedVoidItems.WHITE_VOID), has(DyedVoidItems.WHITE_VOID))
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
