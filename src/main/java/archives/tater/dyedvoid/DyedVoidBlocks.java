package archives.tater.dyedvoid;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import org.jetbrains.annotations.Nullable;

public class DyedVoidBlocks {

    private static Block register(String name, Block block) {
        return Registry.register(Registries.BLOCK, DyedVoid.id(name), block);
    }

    private static Block registerVoidBlock(@Nullable String colorName, boolean luminant) {
        var settings = AbstractBlock.Settings.create()
                .strength(0)
                .hardness(3)
                .emissiveLighting((state, world, pos) -> true)
                .sounds(DyedVoidSounds.VOID_BLOCK_SOUND_GROUP)
                .luminance(luminant ? state -> 15 : state -> 0)
                .noBlockBreakParticles();

        return register(colorName == null ? "void" : colorName + "_void", new VoidBlock(settings));
    }

    private static Block registerVoidBlock(String colorName) {
        return registerVoidBlock(colorName, true);
    }

    public static final Block WHITE_VOID = registerVoidBlock("white");
    public static final Block LIGHT_GRAY_VOID = registerVoidBlock("light_gray");
    public static final Block GRAY_VOID = registerVoidBlock("gray");
    public static final Block BLACK_VOID = registerVoidBlock(null, false);
    public static final Block BROWN_VOID = registerVoidBlock("brown");
    public static final Block RED_VOID = registerVoidBlock("red");
    public static final Block ORANGE_VOID = registerVoidBlock("orange");
    public static final Block YELLOW_VOID = registerVoidBlock("yellow");
    public static final Block LIME_VOID = registerVoidBlock("lime");
    public static final Block GREEN_VOID = registerVoidBlock("green");
    public static final Block CYAN_VOID = registerVoidBlock("cyan");
    public static final Block LIGHT_BLUE_VOID = registerVoidBlock("light_blue");
    public static final Block BLUE_VOID = registerVoidBlock("blue");
    public static final Block PURPLE_VOID = registerVoidBlock("purple");
    public static final Block MAGENTA_VOID = registerVoidBlock("magenta");
    public static final Block PINK_VOID = registerVoidBlock("pink");

    public static final Block END_VOID = register("end_void", new EndVoidBlock(AbstractBlock.Settings.create()
            .strength(0)
            .hardness(3)
            .sounds(DyedVoidSounds.VOID_BLOCK_SOUND_GROUP)
            .noBlockBreakParticles()
    ));
    public static final BlockEntityType<EndVoidBlock.EndVoidBlockEntity> END_VOID_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            DyedVoid.id("end_void"),
            BlockEntityType.Builder.create(EndVoidBlock.EndVoidBlockEntity::new, END_VOID).build()
    );

    public static final Block[] VOID_BLOCKS = {
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

    public static final TagKey<Block> VOID_BLOCKS_TAG = TagKey.of(RegistryKeys.BLOCK, DyedVoid.id("void_blocks"));

    public static void initialize() {}
}
