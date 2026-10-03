package archives.tater.dyedvoid.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import org.jetbrains.annotations.Nullable;

public class SkyVoidBlock extends VoidBlock {
    public static final BooleanProperty NIGHT = BooleanProperty.create("night");

    public SkyVoidBlock(Properties settings) {
        super(settings);
        registerDefaultState(getStateDefinition().any().setValue(NIGHT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NIGHT);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(NIGHT, context.getLevel().hasNeighborSignal(context.getClickedPos()));
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos neighborPos, boolean movedByPiston) {
        if (level.isClientSide) return;
        var night = state.getValue(NIGHT);
        if (night != level.hasNeighborSignal(pos))
            level.setBlock(pos, state.cycle(NIGHT), UPDATE_CLIENTS);
    }
}
