package archives.tater.dyedvoid.item;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class DummyVanillaItem extends Item {
    public DummyVanillaItem(Properties properties) {
        super(properties);
    }

    @Override
    public String getCreatorNamespace(ItemStack stack) {
        return Identifier.DEFAULT_NAMESPACE;
    }
}
