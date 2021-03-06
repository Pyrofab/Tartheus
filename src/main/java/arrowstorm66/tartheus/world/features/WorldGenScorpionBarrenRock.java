package arrowstorm66.tartheus.world.features;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.Tartheus;
import arrowstorm66.tartheus.blocks.tile.TileEntityScorpionSpawner;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenScorpionBarrenRock extends WorldGenerator {
    private final Block block;
	private final Block targetblock;
    private final int startRadius;

    public WorldGenScorpionBarrenRock(Block blockIn, Block targetblock, int startRadiusIn)
    {
        super(false);
        this.block = blockIn;
		this.targetblock = targetblock;
        this.startRadius = startRadiusIn;
    }

	public boolean generate(World worldIn, Random rand, BlockPos position) {
		while (true) {
			label0: {
				if (position.getY() > 3) {
					if (worldIn.isAirBlock(position.down())) {
						break label0;
					}

					Block block = worldIn.getBlockState(position.down()).getBlock();

					if (block != targetblock) {
						break label0;
					}
				}

				if (position.getY() <= 3) {
					return false;
				}

				int i1 = this.startRadius;

				for (int i = 0; i1 >= 0 && i < 3; ++i) {
					int j = i1 + rand.nextInt(2);
					int k = i1 + rand.nextInt(2);
					int l = i1 + rand.nextInt(2);
					float f = (float) (j + k + l) * 0.333F + 0.5F;

					for (BlockPos blockpos : BlockPos.getAllInBox(position.add(-j, -k, -l), position.add(j, k, l))) {
						if (blockpos.distanceSq(position) <= (double) (f * f)) {
							worldIn.setBlockState(blockpos, this.block.getDefaultState(), 4);
						}
					}

					position = position.add(-(i1 + 1) + rand.nextInt(2 + i1 * 2), 0 - rand.nextInt(2),
							-(i1 + 1) + rand.nextInt(2 + i1 * 2));

					worldIn.setBlockState(position, MBlocks.SCORPION_SPAWNER.getDefaultState(), 2);
					TileEntity tileentity = worldIn.getTileEntity(position);

					if (tileentity instanceof TileEntityScorpionSpawner) {
						((TileEntityScorpionSpawner) tileentity).getSpawnerBaseLogic()
								.setEntityId(new ResourceLocation("tartheus:scorpion"));
					} else {
						Tartheus.LOGGER.error("Failed to fetch mob spawner entity at ({}, {}, {})",
								new Object[] {position.getX(), position.getY(),
										position.getZ()});
					}
				}

				return true;
			}
			position = position.down();
		}
	}
}