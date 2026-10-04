package archives.tater.dyedvoid.registry;

import archives.tater.dyedvoid.DyedVoid;
import archives.tater.dyedvoid.item.VoidBottleItem;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class DyedVoidItems {

    private static Item register(Identifier identifier, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, identifier, item);
    }

    private static Item register(String path, Item item) {
        return register(DyedVoid.id(path), item);
    }

    private static Item registerBlockItem(Block block, Item.Properties settings) {
        return Registry.register(BuiltInRegistries.ITEM, BuiltInRegistries.BLOCK.getKey(block), new BlockItem(block, settings));
    }

    private static Item registerBlockItem(Block block) {
        return registerBlockItem(block, new Item.Properties());
    }

    public static final Item WHITE_VOID = registerBlockItem(DyedVoidBlocks.WHITE_VOID);
    public static final Item LIGHT_GRAY_VOID = registerBlockItem(DyedVoidBlocks.LIGHT_GRAY_VOID);
    public static final Item GRAY_VOID = registerBlockItem(DyedVoidBlocks.GRAY_VOID);
    public static final Item BLACK_VOID = registerBlockItem(DyedVoidBlocks.BLACK_VOID);
    public static final Item BROWN_VOID = registerBlockItem(DyedVoidBlocks.BROWN_VOID);
    public static final Item RED_VOID = registerBlockItem(DyedVoidBlocks.RED_VOID);
    public static final Item ORANGE_VOID = registerBlockItem(DyedVoidBlocks.ORANGE_VOID);
    public static final Item YELLOW_VOID = registerBlockItem(DyedVoidBlocks.YELLOW_VOID);
    public static final Item LIME_VOID = registerBlockItem(DyedVoidBlocks.LIME_VOID);
    public static final Item GREEN_VOID = registerBlockItem(DyedVoidBlocks.GREEN_VOID);
    public static final Item CYAN_VOID = registerBlockItem(DyedVoidBlocks.CYAN_VOID);
    public static final Item LIGHT_BLUE_VOID = registerBlockItem(DyedVoidBlocks.LIGHT_BLUE_VOID);
    public static final Item BLUE_VOID = registerBlockItem(DyedVoidBlocks.BLUE_VOID);
    public static final Item PURPLE_VOID = registerBlockItem(DyedVoidBlocks.PURPLE_VOID);
    public static final Item MAGENTA_VOID = registerBlockItem(DyedVoidBlocks.MAGENTA_VOID);
    public static final Item PINK_VOID = registerBlockItem(DyedVoidBlocks.PINK_VOID);

    public static final Item END_VOID = registerBlockItem(DyedVoidBlocks.END_VOID);
    public static final Item SKY_VOID = registerBlockItem(DyedVoidBlocks.SKY_VOID);
    public static final Item CLOUD_VOID = registerBlockItem(DyedVoidBlocks.CLOUD_VOID);

    public static final Item[] VOID_BLOCKS = {
            BLACK_VOID,
            WHITE_VOID,
            LIGHT_GRAY_VOID,
            GRAY_VOID,
            BROWN_VOID,
            RED_VOID,
            ORANGE_VOID,
            YELLOW_VOID,
            LIME_VOID,
            GREEN_VOID,
            CYAN_VOID,
            LIGHT_BLUE_VOID,
            BLUE_VOID,
            PURPLE_VOID,
            MAGENTA_VOID,
            PINK_VOID,
            END_VOID,
            SKY_VOID,
            CLOUD_VOID
    };

    public static final Item VOID_BOTTLE_ITEM = register("void_bottle", new VoidBottleItem(new Item.Properties()
            .stacksTo(16)
            .craftRemainder(Items.GLASS_BOTTLE)
    ));

    public static final CreativeModeTab ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(RED_VOID))
            .title(Component.translatable("itemGroup.dyedvoid.group"))
            .displayItems((context, entries) -> {
                entries.accept(VOID_BOTTLE_ITEM);
                entries.accept(WHITE_VOID);
                entries.accept(LIGHT_GRAY_VOID);
                entries.accept(GRAY_VOID);
                entries.accept(BLACK_VOID);
                entries.accept(BROWN_VOID);
                entries.accept(RED_VOID);
                entries.accept(ORANGE_VOID);
                entries.accept(YELLOW_VOID);
                entries.accept(LIME_VOID);
                entries.accept(GREEN_VOID);
                entries.accept(CYAN_VOID);
                entries.accept(LIGHT_BLUE_VOID);
                entries.accept(BLUE_VOID);
                entries.accept(PURPLE_VOID);
                entries.accept(MAGENTA_VOID);
                entries.accept(PINK_VOID);
                entries.accept(END_VOID);
                entries.accept(SKY_VOID);
            })
            .build();

    public static final Item DUMMY_END_PORTAL = register(Identifier.withDefaultNamespace("dyedvoid/dummy/end_portal"), new BlockItem(Blocks.END_PORTAL, new Item.Properties()));
    public static final Item DUMMY_END_GATEWAY = register(Identifier.withDefaultNamespace("dyedvoid/dummy/end_gateway"), new BlockItem(Blocks.END_GATEWAY, new Item.Properties()));

    public static void initalize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, DyedVoid.id("item_group"), DyedVoidItems.ITEM_GROUP);
    }
}
