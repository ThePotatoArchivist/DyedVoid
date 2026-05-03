package archives.tater.dyedvoid.block;

import net.minecraft.world.item.DyeColor;

public class ColoredVoidBlock extends VoidBlock {
    public final DyeColor color;

    public ColoredVoidBlock(DyeColor color, Properties settings) {
        super(settings);
        this.color = color;
    }
}
