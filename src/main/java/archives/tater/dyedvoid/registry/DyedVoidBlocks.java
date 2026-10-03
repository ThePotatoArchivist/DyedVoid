package archives.tater.dyedvoid.registry;

import archives.tater.dyedvoid.DyedVoid;
import archives.tater.dyedvoid.block.EndVoidBlock;
import archives.tater.dyedvoid.block.VoidBlock;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.Nullable;

public class DyedVoidBlocks {

    private static Block register(String name, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, DyedVoid.id(name), block);
    }

    private static Block registerVoidBlock(@Nullable String colorName, boolean luminant) {
        var settings = BlockBehaviour.Properties.of()
                .strength(0)
                .destroyTime(3)
                .sound(DyedVoidSounds.VOID_BLOCK_SOUND_GROUP)
                .noTerrainParticles();

        if (luminant)
            settings
                    .emissiveRendering((state, world, pos) -> true)
                    .lightLevel(state -> 15);

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

    public static final Block END_VOID = register("end_void", new EndVoidBlock(BlockBehaviour.Properties.of()
            .strength(0)
            .destroyTime(3)
            .sound(DyedVoidSounds.VOID_BLOCK_SOUND_GROUP)
            .noTerrainParticles()
    ));
    public static final BlockEntityType<EndVoidBlock.EndVoidBlockEntity> END_VOID_BLOCK_ENTITY = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE,
            DyedVoid.id("end_void"),
            BlockEntityType.Builder.of(EndVoidBlock.EndVoidBlockEntity::new, END_VOID).build()
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

    public static final TagKey<Block> VOID_BLOCKS_TAG = TagKey.create(Registries.BLOCK, DyedVoid.id("void_blocks"));

    public static void initialize() {}
}
