package archives.tater.dyedvoid.consumeeffect;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;

public record ExhaustConsumeEffect(float exhaustion) implements ConsumeEffect {
    public static final MapCodec<ExhaustConsumeEffect> CODEC = Codec.FLOAT.fieldOf("exhaustion").xmap(ExhaustConsumeEffect::new, ExhaustConsumeEffect::exhaustion);
    public static final StreamCodec<RegistryFriendlyByteBuf, ExhaustConsumeEffect> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT, ExhaustConsumeEffect::exhaustion,
            ExhaustConsumeEffect::new
    );
    public static final Type<ExhaustConsumeEffect> TYPE = new Type<>(CODEC, STREAM_CODEC);

    @Override
    public Type<? extends ExhaustConsumeEffect> getType() {
        return TYPE;
    }

    @Override
    public boolean apply(Level world, ItemStack stack, LivingEntity user) {
        if (!(user instanceof Player player))
            return false;

        player.causeFoodExhaustion(exhaustion);

        return true;
    }
}
