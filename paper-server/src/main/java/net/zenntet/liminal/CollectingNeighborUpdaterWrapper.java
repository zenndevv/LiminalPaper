package net.zenntet.liminal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.CollectingNeighborUpdater;
import net.minecraft.world.level.redstone.InstantNeighborUpdater;
import net.minecraft.world.level.redstone.Orientation;
import org.jspecify.annotations.Nullable;

public class CollectingNeighborUpdaterWrapper extends CollectingNeighborUpdater {
    public InstantNeighborUpdater updater;

    public CollectingNeighborUpdaterWrapper(Level world) {
        super(world, 0);
        this.updater = new InstantNeighborUpdater(world);
    }

    @Override
    public void shapeUpdate(Direction direction, BlockState neighborState, BlockPos pos, BlockPos neighborPos, int flags, int maxUpdateDepth) {
        if(this.debugListener != null)
        {
            this.debugListener.accept(pos);
        }

        this.updater.shapeUpdate(direction, neighborState, pos, neighborPos, flags, maxUpdateDepth);
    }

    @Override
    public void neighborChanged(BlockPos pos, Block sourceBlock, @Nullable Orientation orientation) {
        if(this.debugListener != null)
        {
            this.debugListener.accept(pos);
        }

        this.updater.neighborChanged(pos, sourceBlock, orientation);
    }

    @Override
    public void neighborChanged(BlockState state, BlockPos pos, Block sourceBlock, @Nullable Orientation orientation, boolean notify) {
        if(this.debugListener != null)
        {
            this.debugListener.accept(pos);
        }

        this.updater.neighborChanged(state, pos, sourceBlock, orientation, notify);
    }

    @Override
    public void updateNeighborsAtExceptFromFacing(BlockPos pos, Block sourceBlock, @Nullable Direction except, @Nullable Orientation orientation) {
        this.updater.updateNeighborsAtExceptFromFacing(pos, sourceBlock, except, orientation);
    }
}
