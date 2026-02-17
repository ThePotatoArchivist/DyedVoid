package archives.tater.dyedvoid;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;


public class DyedVoidSounds {
    private static SoundEvent register(String path) {
        Identifier identifier = DyedVoid.id(path);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
    }

    public static final SoundEvent FILL_VOID_BOTTLE = register("fill_void_bottle");
    public static final SoundEvent VOID_BLOCK_PLACE = register("place_void_block");

    public static final SoundType VOID_BLOCK_SOUND_GROUP = new SoundType(
            0.05f,
            0.7f,
            SoundEvents.EMPTY,
            SoundEvents.EMPTY,
            VOID_BLOCK_PLACE,
            SoundEvents.EMPTY,
            SoundEvents.EMPTY
    );

    public static void initialize() {}
}
