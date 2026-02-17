package archives.tater.dyedvoid.consumeeffect;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;

public record SetAirConsumeEffect(int air) implements ConsumeEffect {
    public static final MapCodec<SetAirConsumeEffect> CODEC = Codec.INT.fieldOf("air").xmap(SetAirConsumeEffect::new, SetAirConsumeEffect::air);
    public static final StreamCodec<RegistryFriendlyByteBuf, SetAirConsumeEffect> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, SetAirConsumeEffect::air,
            SetAirConsumeEffect::new
    );
    public static final Type<SetAirConsumeEffect> TYPE = new Type<>(CODEC, STREAM_CODEC);

    @Override
    public Type<? extends SetAirConsumeEffect> getType() {
        return TYPE;
    }

    @Override
    public boolean apply(Level world, ItemStack stack, LivingEntity user) {
        user.setAirSupply(air);
        return true;
    }
}
