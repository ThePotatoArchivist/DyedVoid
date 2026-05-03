package archives.tater.dyedvoid.registry;

import archives.tater.dyedvoid.DyedVoid;
import archives.tater.dyedvoid.consumeeffect.ExhaustConsumeEffect;
import archives.tater.dyedvoid.consumeeffect.SetAirConsumeEffect;
import archives.tater.dyedvoid.item.DummyVanillaItem;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ColorCollection;

import java.util.List;
import java.util.function.Function;

public class DyedVoidItems {

    private static Item register(ResourceKey<Item> key, Function<Item.Properties, Item> item, Item.Properties properties) {
        return Registry.register(BuiltInRegistries.ITEM, key, item.apply(properties.setId(key)));
    }

    private static Item register(ResourceKey<Item> key, Item.Properties properties) {
        return register(key, Item::new, properties);
    }

    private static Item registerBlockItem(BlockItemId id, Block block) {
        return register(id.item(), settings -> new BlockItem(block, settings), new Item.Properties().useBlockDescriptionPrefix());
    }

    public static final ColorCollection<Item> VOID = ColorCollection.registerBlockItems(
            DyedVoidBlockItemIds.VOID,
            DyedVoidBlocks.VOID,
            (id, block, _) -> registerBlockItem(id, block)
    );

    public static final Item END_VOID = registerBlockItem(DyedVoidBlockItemIds.END_VOID, DyedVoidBlocks.END_VOID);

    public static final Item VOID_BOTTLE_ITEM = register(DyedVoidItemIds.VOID_BOTTLE, new Item.Properties()
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
            .icon(() -> new ItemStack(VOID.red()))
            .title(Component.translatable("itemGroup.dyedvoid.group"))
            .displayItems((_, output) -> {
                output.accept(VOID_BOTTLE_ITEM);
                VOID.forEach(output::accept);
                output.accept(END_VOID);
            })
            .build();

    public static final Item DUMMY_END_PORTAL = register(DyedVoidItemIds.DUMMY_END_PORTAL, DummyVanillaItem::new, new Item.Properties().overrideDescription(Blocks.END_PORTAL.getDescriptionId()));
    public static final Item DUMMY_END_GATEWAY = register(DyedVoidItemIds.DUMMY_END_GATEWAY, DummyVanillaItem::new, new Item.Properties().overrideDescription(Blocks.END_GATEWAY.getDescriptionId()));

    public static void init() {
        BuiltInRegistries.ITEM.addAlias(DyedVoidBlockItemIds.LEGACY_BLACK_VOID, DyedVoidBlockItemIds.VOID.black().item().identifier());

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, DyedVoid.id("item_group"), DyedVoidItems.ITEM_GROUP);

        Registry.register(BuiltInRegistries.CONSUME_EFFECT_TYPE, DyedVoid.id("set_air"), SetAirConsumeEffect.TYPE);
        Registry.register(BuiltInRegistries.CONSUME_EFFECT_TYPE, DyedVoid.id("exhaust"), ExhaustConsumeEffect.TYPE);
    }
}
