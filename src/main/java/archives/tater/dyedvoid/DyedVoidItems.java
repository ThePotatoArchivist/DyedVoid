package archives.tater.dyedvoid;

import archives.tater.dyedvoid.consumeeffect.ExhaustConsumeEffect;
import archives.tater.dyedvoid.consumeeffect.SetAirConsumeEffect;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.function.Function;

public class DyedVoidItems {

    private static Item register(Identifier identifier, Function<Item.Properties, Item> item, Item.Properties settings) {
        var key = ResourceKey.create(Registries.ITEM, identifier);
        return Registry.register(BuiltInRegistries.ITEM, key, item.apply(settings.setId(key)));
    }

    private static Item register(String path, Function<Item.Properties, Item> item, Item.Properties settings) {
        return register(DyedVoid.id(path), item, settings);
    }

    private static Item register(String path, Item.Properties settings) {
        return register(DyedVoid.id(path), Item::new, settings);
    }

    private static Item registerBlockItem(Block block) {
        return registerBlockItem(BuiltInRegistries.BLOCK.getKey(block), block);
    }

    private static Item registerBlockItem(Identifier identifier, Block block) {
        return register(identifier, settings -> new BlockItem(block, settings), new Item.Properties().useBlockDescriptionPrefix());
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

    public static final Item SHADOW_VOID = registerBlockItem(DyedVoidBlocks.SHADOW_VOID);
    public static final Item INVERTED_SHADOW_VOID = registerBlockItem(DyedVoidBlocks.INVERTED_SHADOW_VOID);

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
            SHADOW_VOID,
            INVERTED_SHADOW_VOID,
            END_VOID
    };

    public static final Item VOID_BOTTLE_ITEM = register("void_bottle", new Item.Properties()
            .stacksTo(16)
            .craftRemainder(Items.GLASS_BOTTLE)
            .usingConvertsTo(Items.GLASS_BOTTLE)
            .component(DataComponents.CONSUMABLE, new Consumable(1.6f, ItemUseAnimation.DRINK, BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.EMPTY), false, List.of(
                    new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.LEVITATION, 200)),
                    new ExhaustConsumeEffect(12),
                    new SetAirConsumeEffect(0)
            )))
    );

    public static final CreativeModeTab ITEM_GROUP = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(RED_VOID))
            .title(Component.translatable("itemGroup.dyedvoid.group"))
            .displayItems((_, output) -> {
                output.accept(VOID_BOTTLE_ITEM);
                output.accept(WHITE_VOID);
                output.accept(LIGHT_GRAY_VOID);
                output.accept(GRAY_VOID);
                output.accept(BLACK_VOID);
                output.accept(BROWN_VOID);
                output.accept(RED_VOID);
                output.accept(ORANGE_VOID);
                output.accept(YELLOW_VOID);
                output.accept(LIME_VOID);
                output.accept(GREEN_VOID);
                output.accept(CYAN_VOID);
                output.accept(LIGHT_BLUE_VOID);
                output.accept(BLUE_VOID);
                output.accept(PURPLE_VOID);
                output.accept(MAGENTA_VOID);
                output.accept(PINK_VOID);
                output.accept(END_VOID);
            })
            .build();

    public static final Item DUMMY_END_PORTAL = registerBlockItem(Identifier.withDefaultNamespace("dyedvoid/dummy/end_portal"), Blocks.END_PORTAL);
    public static final Item DUMMY_END_GATEWAY = registerBlockItem(Identifier.withDefaultNamespace("dyedvoid/dummy/end_gateway"), Blocks.END_GATEWAY);

    public static final TagKey<Item> NO_GRAVITY_TAG = TagKey.create(Registries.ITEM, DyedVoid.id("no_gravity"));

    public static void init() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, DyedVoid.id("item_group"), DyedVoidItems.ITEM_GROUP);

        Registry.register(BuiltInRegistries.CONSUME_EFFECT_TYPE, DyedVoid.id("set_air"), SetAirConsumeEffect.TYPE);
        Registry.register(BuiltInRegistries.CONSUME_EFFECT_TYPE, DyedVoid.id("exhaust"), ExhaustConsumeEffect.TYPE);
    }
}
