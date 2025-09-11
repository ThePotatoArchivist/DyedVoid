package archives.tater.dyedvoid;

import archives.tater.dyedvoid.consumeeffect.ExhaustConsumeEffect;
import archives.tater.dyedvoid.consumeeffect.SetAirConsumeEffect;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.consume.UseAction;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Function;

public class DyedVoidItems {

    private static Item register(Identifier identifier, Function<Item.Settings, Item> item, Item.Settings settings) {
        var key = RegistryKey.of(RegistryKeys.ITEM, identifier);
        return Registry.register(Registries.ITEM, key, item.apply(settings.registryKey(key)));
    }

    private static Item register(String path, Function<Item.Settings, Item> item, Item.Settings settings) {
        return register(DyedVoid.id(path), item, settings);
    }

    private static Item register(String path, Item.Settings settings) {
        return register(DyedVoid.id(path), Item::new, settings);
    }

    private static Item registerBlockItem(Block block) {
        return Items.register(block);
    }

    private static Item registerBlockItem(Identifier identifier, Block block) {
        return register(identifier, settings -> new BlockItem(block, settings), new Item.Settings());
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
            END_VOID
    };

    public static final Item VOID_BOTTLE_ITEM = register("void_bottle", new Item.Settings()
            .maxCount(16)
            .recipeRemainder(Items.GLASS_BOTTLE)
            .useRemainder(Items.GLASS_BOTTLE)
            .component(DataComponentTypes.CONSUMABLE, new ConsumableComponent(1.6f, UseAction.DRINK, Registries.SOUND_EVENT.getEntry(SoundEvents.INTENTIONALLY_EMPTY), false, List.of(
                    new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 200)),
                    new ExhaustConsumeEffect(12),
                    new SetAirConsumeEffect(0)
            )))
    );

    public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(RED_VOID))
            .displayName(Text.translatable("itemGroup.dyedvoid.group"))
            .entries((context, entries) -> {
                entries.add(VOID_BOTTLE_ITEM);
                entries.add(WHITE_VOID);
                entries.add(LIGHT_GRAY_VOID);
                entries.add(GRAY_VOID);
                entries.add(BLACK_VOID);
                entries.add(BROWN_VOID);
                entries.add(RED_VOID);
                entries.add(ORANGE_VOID);
                entries.add(YELLOW_VOID);
                entries.add(LIME_VOID);
                entries.add(GREEN_VOID);
                entries.add(CYAN_VOID);
                entries.add(LIGHT_BLUE_VOID);
                entries.add(BLUE_VOID);
                entries.add(PURPLE_VOID);
                entries.add(MAGENTA_VOID);
                entries.add(PINK_VOID);
                entries.add(END_VOID);
            })
            .build();

//    public static final Item DUMMY_END_PORTAL = registerBlockItem(Identifier.ofVanilla("dyedvoid/dummy/end_portal"), Blocks.END_PORTAL);
//    public static final Item DUMMY_END_GATEWAY = registerBlockItem(Identifier.ofVanilla("dyedvoid/dummy/end_gateway"), Blocks.END_GATEWAY);

    public static final TagKey<Item> NO_GRAVITY_TAG = TagKey.of(RegistryKeys.ITEM, DyedVoid.id("no_gravity"));

    public static void initalize() {
        Registry.register(Registries.ITEM_GROUP, DyedVoid.id("item_group"), DyedVoidItems.ITEM_GROUP);

        Registry.register(Registries.CONSUME_EFFECT_TYPE, DyedVoid.id("set_air"), SetAirConsumeEffect.TYPE);
        Registry.register(Registries.CONSUME_EFFECT_TYPE, DyedVoid.id("exhaust"), ExhaustConsumeEffect.TYPE);
    }
}
