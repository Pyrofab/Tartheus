package arrowstorm66.tartheus.blocks.tile;

import arrowstorm66.tartheus.MBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedSpawnerEntity;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TileEntityScorpionSpawner extends TileEntity implements ITickable {
	private final ScorpionSpawnerBaseLogic spawnerLogic = new ScorpionSpawnerBaseLogic() {
		
		public void broadcastEvent(int id) {
			TileEntityScorpionSpawner.this.world.addBlockEvent(TileEntityScorpionSpawner.this.pos,
					MBlocks.SCORPION_SPAWNER, id, 0);
		}

		public World getSpawnerWorld() {
			return TileEntityScorpionSpawner.this.world;
		}

		public BlockPos getSpawnerPosition() {
			return TileEntityScorpionSpawner.this.pos;
		}

		public void setNextSpawnData(WeightedSpawnerEntity p_184993_1_) {
			super.setNextSpawnData(p_184993_1_);

			if (this.getSpawnerWorld() != null) {
				IBlockState iblockstate = this.getSpawnerWorld().getBlockState(this.getSpawnerPosition());
				this.getSpawnerWorld().notifyBlockUpdate(TileEntityScorpionSpawner.this.pos, iblockstate,
						iblockstate, 4);
			}
		}
	};

	public static void registerFixesMobSpawner(DataFixer fixer) {
		fixer.registerWalker(FixTypes.BLOCK_ENTITY, (fixer1, compound, versionIn) -> {
            if (TileEntity.getKey(TileEntityScorpionSpawner.class)
                    .equals(new ResourceLocation(compound.getString("id")))) {
                if (compound.hasKey("SpawnPotentials", 9)) {
                    NBTTagList nbttaglist = compound.getTagList("SpawnPotentials", 10);

                    for (int i = 0; i < nbttaglist.tagCount(); ++i) {
                        NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
                        nbttagcompound.setTag("Entity",
                                fixer1.process(FixTypes.ENTITY, nbttagcompound.getCompoundTag("Entity"), versionIn));
                    }
                }

                compound.setTag("SpawnData",
                        fixer1.process(FixTypes.ENTITY, compound.getCompoundTag("SpawnData"), versionIn));
            }

            return compound;
        });
	}

	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.spawnerLogic.readFromNBT(compound);
	}

	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		this.spawnerLogic.writeToNBT(compound);
		return compound;
	}

	/**
	 * Like the old updateEntity(), except more generic.
	 */
	public void update() {
		this.spawnerLogic.updateSpawner();
	}

	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.pos, 1, this.getUpdateTag());
	}

	public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbttagcompound = this.writeToNBT(new NBTTagCompound());
		nbttagcompound.removeTag("SpawnPotentials");
		return nbttagcompound;
	}

	public boolean receiveClientEvent(int id, int type) {
		return this.spawnerLogic.setDelayToMin(id) || super.receiveClientEvent(id, type);
	}

	public boolean onlyOpsCanSetNbt() {
		return true;
	}

	public ScorpionSpawnerBaseLogic getSpawnerBaseLogic() {
		return this.spawnerLogic;
	}
}