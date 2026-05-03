package archives.tater.dyedvoid.registry;

import archives.tater.dyedvoid.DyedVoid;

import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.ColorCollection;

import java.util.stream.Stream;

public class DyedVoidBlockItemIds {

    private static BlockItemId create(String path) {
        return BlockItemId.create(DyedVoid.id(path), DyedVoid.id(path));
    }

    private static ColorCollection<BlockItemId> createSimpleColored(String baseName) {
        return ColorCollection.prefixWithColor(ColorCollection.create(baseName)).map(DyedVoidBlockItemIds::create);
    }

    public static final ColorCollection<BlockItemId> VOID = createSimpleColored("void");

    public static final BlockItemId END_VOID = create("end_void");

    public static final Identifier LEGACY_BLACK_VOID = DyedVoid.id("void");

    public static final BlockItemId[] ALL_VOID_BLOCKS = Stream.concat(
            VOID.asList().stream(),
            Stream.of(END_VOID)
    ).toArray(BlockItemId[]::new);
}
