package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.DyedVoidBlocks;
import archives.tater.dyedvoid.DyedVoidItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static java.util.Map.entry;

public class RecipeGenerator extends FabricRecipeProvider {
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

    public RecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        dyes.forEach((voidItem, dyeItem) -> ShapelessRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, voidItem, 8)
                .input(DyedVoidItems.WHITE_VOID, 4)
                .input(dyeItem)
                .input(DyedVoidItems.WHITE_VOID, 4)
                .criterion(hasItem(DyedVoidItems.WHITE_VOID), conditionsFromItem(DyedVoidItems.WHITE_VOID))
                .group("dye_void_block")
                .offerTo(exporter)
        );

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, DyedVoidBlocks.BLACK_VOID, 4)
                .pattern("##")
                .pattern("##")
                .input('#', DyedVoidItems.VOID_BOTTLE_ITEM)
                .criterion(hasItem(DyedVoidItems.VOID_BOTTLE_ITEM), conditionsFromItem(DyedVoidItems.VOID_BOTTLE_ITEM))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, DyedVoidItems.WHITE_VOID, 8)
                .input(DyedVoidItems.BLACK_VOID, 4)
                .input(Items.GLOW_INK_SAC)
                .input(DyedVoidItems.BLACK_VOID, 4)
                .criterion(hasItem(DyedVoidItems.BLACK_VOID), conditionsFromItem(DyedVoidItems.BLACK_VOID))
                .offerTo(exporter);
    }
}
