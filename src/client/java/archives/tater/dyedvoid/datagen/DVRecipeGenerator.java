package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.DyedVoidBlocks;
import archives.tater.dyedvoid.DyedVoidItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
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
                .input(dyeItem)
                .input(DyedVoidItems.WHITE_VOID, 4)
                .criterion(getHasName(DyedVoidItems.WHITE_VOID), has(DyedVoidItems.WHITE_VOID))
                .group("dye_void_block")
                .offerTo(output)
        );

        shaped(RecipeCategory.DECORATIONS, DyedVoidBlocks.BLACK_VOID, 4)
                .pattern("##")
                .pattern("##")
                .input('#', DyedVoidItems.VOID_BOTTLE_ITEM)
                .criterion(getHasName(DyedVoidItems.VOID_BOTTLE_ITEM), has(DyedVoidItems.VOID_BOTTLE_ITEM))
                .offerTo(output);

        shapeless(RecipeCategory.DECORATIONS, DyedVoidItems.WHITE_VOID, 8)
                .input(DyedVoidItems.BLACK_VOID, 4)
                .input(Items.GLOW_INK_SAC)
                .input(DyedVoidItems.BLACK_VOID, 4)
                .criterion(getHasName(DyedVoidItems.BLACK_VOID), has(DyedVoidItems.BLACK_VOID))
                .offerTo(output);

        shaped(RecipeCategory.DECORATIONS, DyedVoidItems.SHADOW_VOID, 4)
                .pattern("$#$")
                .pattern("###")
                .pattern("$#$")
                .input('#', DyedVoidItems.BLACK_VOID)
                .input('$', DyedVoidItems.WHITE_VOID)
                .criterion(getHasName(DyedVoidItems.WHITE_VOID), has(DyedVoidItems.WHITE_VOID))
                .offerTo(output);

        shaped(RecipeCategory.DECORATIONS, DyedVoidItems.INVERTED_SHADOW_VOID, 4)
                .pattern("$#$")
                .pattern("###")
                .pattern("$#$")
                .input('#', DyedVoidItems.WHITE_VOID)
                .input('$', DyedVoidItems.BLACK_VOID)
                .criterion(getHasName(DyedVoidItems.WHITE_VOID), has(DyedVoidItems.WHITE_VOID))
                .offerTo(output);
    }

    public static class Provider extends FabricRecipeProvider {

        public Provider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
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
