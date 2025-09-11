package archives.tater.dyedvoid;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.EndPortalBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;


public class EndVoidBlock extends Block implements BlockEntityProvider {
    public EndVoidBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new EndVoidBlockEntity(pos, state);
    }

    public static class EndVoidBlockEntity extends EndPortalBlockEntity {
        public EndVoidBlockEntity(BlockPos pos, BlockState state) {
            super(DyedVoidBlocks.END_VOID_BLOCK_ENTITY, pos, state);
        }

        @Override
        public boolean shouldDrawSide(Direction direction) {
            if (world == null) return true;
            return Block.shouldDrawSide(this.getCachedState(), this.world.getBlockState(this.getPos().offset(direction)), direction);
        }
    }

    public static void tryCraft(Entity entity) {
        if (!(entity instanceof ItemEntity itemEntity)) return;
        var stack = itemEntity.getStack();
        if (!stack.isOf(DyedVoidItems.BLACK_VOID)) return;
        itemEntity.setStack(new ItemStack(DyedVoidItems.END_VOID, stack.getCount()));
    }
}
