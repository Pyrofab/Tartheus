package arrowstorm66.tartheus.world.portal;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.Tartheus;
import arrowstorm66.tartheus.world.biomes.BiomeShrubland;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeSavanna;

import javax.annotation.Nullable;
import java.util.Random;

public class TartheusTeleporter extends Teleporter {
	private final WorldServer worldServerInstance;
	private final Random random;
	private final Long2ObjectMap<Teleporter.PortalPosition> destinationCoordinateCache = new Long2ObjectOpenHashMap<>(
			4096);

	public TartheusTeleporter(WorldServer worldIn) {
		super(worldIn);
		this.worldServerInstance = worldIn;
		this.random = new Random(worldIn.getSeed());
	}

	@Nullable
	private BlockPos findSafeCoords(int range, BlockPos pos, Entity par1Entity) {
		for (int i = 0; i < 25; i++) {
			BlockPos dPos = new BlockPos(pos.getX() + random.nextInt(range) - random.nextInt(range), 100,
					pos.getZ() + random.nextInt(range) - random.nextInt(range));

			if (isStartingBiomeAt(dPos)) {
				return dPos;
			}
		}
		return null;
	}

	private boolean isStartingBiomeAt(BlockPos pos) {
		Biome biomeAt = world.getBiome(pos);
		return biomeAt instanceof BiomeSavanna || biomeAt instanceof BiomeShrubland;
	}

	@Override
	public void placeInPortal(Entity par1Entity, float facing) {
		if (!this.placeInExistingPortal(par1Entity, facing)) {
			BlockPos pos = new BlockPos(par1Entity);
			if (!isStartingBiomeAt(pos)) {
				Tartheus.LOGGER.info("Rerouting spawn position.");
				BlockPos safeCoords = findSafeCoords(200, pos, par1Entity);
				if (safeCoords != null) {
					par1Entity.setLocationAndAngles(safeCoords.getX(), par1Entity.posY, safeCoords.getZ(), 90.0F, 0.0F);
					Tartheus.LOGGER.info("Found safe spawn position.");
				} else {
					Tartheus.LOGGER.info("Rerouting spawn position... Again.");
					safeCoords = findSafeCoords(400, pos, par1Entity);
					if (safeCoords != null) {
						par1Entity.setLocationAndAngles(safeCoords.getX(), par1Entity.posY, safeCoords.getZ(), 90.0F,
								0.0F);
						Tartheus.LOGGER.info("Found another safe spawn position!");
					} else {
						Tartheus.LOGGER.info("Rerouting spawn position... Third time's a charm?");
						safeCoords = findSafeCoords(600, pos, par1Entity);
						if (safeCoords != null) {
							par1Entity.setLocationAndAngles(safeCoords.getX(), par1Entity.posY, safeCoords.getZ(),
									90.0F, 0.0F);
							Tartheus.LOGGER.info("Hopefully this one is safe.");
						} else {
							Tartheus.LOGGER.info(
									"Safe Spawn Position Not Found. " + par1Entity.getDisplayName().getFormattedText()
											+ " may have a hard time dealing with the conditions.");
						}
					}
				}
			}
			this.makePortal(par1Entity);
			this.placeInExistingPortal(par1Entity, facing);
		}
	}

	public boolean placeInExistingPortal(Entity entityIn, float rotationYaw) {
		int i = 128;
		double d0 = -1.0D;
		int j = MathHelper.floor(entityIn.posX);
		int k = MathHelper.floor(entityIn.posZ);
		boolean flag = true;
		BlockPos blockpos = BlockPos.ORIGIN;
		long l = ChunkPos.asLong(j, k);

		if (this.destinationCoordinateCache.containsKey(l)) {
			Teleporter.PortalPosition teleporter$portalposition = this.destinationCoordinateCache
					.get(l);
			d0 = 0.0D;
			blockpos = teleporter$portalposition;
			teleporter$portalposition.lastUpdateTime = this.worldServerInstance.getTotalWorldTime();
			flag = false;
		} else {
			BlockPos blockpos3 = new BlockPos(entityIn);

			for (int i1 = -128; i1 <= 128; ++i1) {
				BlockPos blockpos2;

				for (int j1 = -128; j1 <= 128; ++j1) {
					for (BlockPos blockpos1 = blockpos3.add(i1,
							this.worldServerInstance.getActualHeight() - 1 - blockpos3.getY(), j1); blockpos1
									.getY() >= 0; blockpos1 = blockpos2) {
						blockpos2 = blockpos1.down();

						if (this.worldServerInstance.getBlockState(blockpos1).getBlock() == MBlocks.TARTHEUS_PORTAL) {
							for (blockpos2 = blockpos1.down(); this.worldServerInstance.getBlockState(blockpos2)
									.getBlock() == MBlocks.TARTHEUS_PORTAL; blockpos2 = blockpos2.down()) {
								blockpos1 = blockpos2;
							}

							double d1 = blockpos1.distanceSq(blockpos3);

							if (d0 < 0.0D || d1 < d0) {
								d0 = d1;
								blockpos = blockpos1;
							}
						}
					}
				}
			}
		}

		if (d0 >= 0.0D) {
			if (flag) {
				this.destinationCoordinateCache.put(l,
						new Teleporter.PortalPosition(blockpos, this.worldServerInstance.getTotalWorldTime()));
			}

			double d5 = (double) blockpos.getX() + 0.5D;
			double d7 = (double) blockpos.getZ() + 0.5D;
			double d6 = (double) blockpos.getY() + 0.5D;

			if (entityIn instanceof EntityPlayerMP) {
				((EntityPlayerMP) entityIn).connection.setPlayerLocation(d5, d6, d7, entityIn.rotationYaw,
						entityIn.rotationPitch);
			} else {
				entityIn.setLocationAndAngles(d5, d6, d7, entityIn.rotationYaw, entityIn.rotationPitch);
			}

			return true;
		} else {
			return false;
		}
	}

	public boolean makePortal(Entity entityIn) {
		int x = MathHelper.floor(entityIn.posX) + 8;
		int z = MathHelper.floor(entityIn.posZ) + 8;
		int y = this.worldServerInstance.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY();
		int[] heightList = { 0, 1, 2, 3, 4 };

		// Make Portal
		for (int height : heightList) {
			this.worldServerInstance.setBlockState(new BlockPos(x, y - 1, z), MBlocks.STONE_FIRST_STRATUM.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x - 1, y - 1, z), MBlocks.STONE_FIRST_STRATUM.getDefaultState(),
					2);
			this.worldServerInstance.setBlockState(new BlockPos(x + 1, y - 1, z), MBlocks.STONE_FIRST_STRATUM.getDefaultState(),
					2);
			this.worldServerInstance.setBlockState(new BlockPos(x, y - 1, z - 1), MBlocks.STONE_FIRST_STRATUM.getDefaultState(),
					2);
			this.worldServerInstance.setBlockState(new BlockPos(x, y - 1, z + 1), MBlocks.STONE_FIRST_STRATUM.getDefaultState(),
					2);
			this.worldServerInstance.setBlockState(new BlockPos(x - 1, y - 1, z + 1),
					MBlocks.STONE_FIRST_STRATUM.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x - 1, y - 1, z - 1),
					MBlocks.STONE_FIRST_STRATUM.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x + 1, y - 1, z + 1),
					MBlocks.STONE_FIRST_STRATUM.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x + 1, y - 1, z - 1),
					MBlocks.STONE_FIRST_STRATUM.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x - 2, y - 1, z), MBlocks.STONE_FIRST_STRATUM.getDefaultState(),
					2);
			this.worldServerInstance.setBlockState(new BlockPos(x + 2, y - 1, z), MBlocks.STONE_FIRST_STRATUM.getDefaultState(),
					2);
			this.worldServerInstance.setBlockState(new BlockPos(x, y - 1, z - 2), MBlocks.STONE_FIRST_STRATUM.getDefaultState(),
					2);
			this.worldServerInstance.setBlockState(new BlockPos(x, y - 1, z + 2), MBlocks.STONE_FIRST_STRATUM.getDefaultState(),
					2);
			this.worldServerInstance.setBlockState(new BlockPos(x - 2, y - 1, z + 2),
					MBlocks.STONE_FIRST_STRATUM.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x - 2, y - 1, z - 2),
					MBlocks.STONE_FIRST_STRATUM.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x + 2, y - 1, z + 2),
					MBlocks.STONE_FIRST_STRATUM.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x + 2, y - 1, z - 2),
					MBlocks.STONE_FIRST_STRATUM.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x - 1, y - 1, z + 2),
					MBlocks.STONE_FIRST_STRATUM.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x - 1, y - 1, z - 2),
					MBlocks.STONE_FIRST_STRATUM.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x + 1, y - 1, z + 2),
					MBlocks.STONE_FIRST_STRATUM.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x + 1, y - 1, z - 2),
					MBlocks.STONE_FIRST_STRATUM.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x - 2, y - 1, z + 1),
					MBlocks.STONE_FIRST_STRATUM.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x - 2, y - 1, z - 1),
					MBlocks.STONE_FIRST_STRATUM.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x + 2, y - 1, z + 1),
					MBlocks.STONE_FIRST_STRATUM.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x + 2, y - 1, z - 1),
					MBlocks.STONE_FIRST_STRATUM.getDefaultState(), 2);

			// Make Air
			this.worldServerInstance.setBlockState(new BlockPos(x, y + height, z), Blocks.AIR.getDefaultState(),
					2);
			this.worldServerInstance.setBlockState(new BlockPos(x - 1, y + height, z),
					Blocks.AIR.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x + 1, y + height, z),
					Blocks.AIR.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x, y + height, z - 1),
					Blocks.AIR.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x, y + height, z + 1),
					Blocks.AIR.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x - 1, y + height, z + 1),
					Blocks.AIR.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x - 1, y + height, z - 1),
					Blocks.AIR.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x + 1, y + height, z + 1),
					Blocks.AIR.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x + 1, y + height, z - 1),
					Blocks.AIR.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x - 2, y + height, z),
					Blocks.AIR.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x + 2, y + height, z),
					Blocks.AIR.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x, y + height, z - 2),
					Blocks.AIR.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x, y + height, z + 2),
					Blocks.AIR.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x - 2, y + height, z + 2),
					Blocks.AIR.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x - 2, y + height, z - 2),
					Blocks.AIR.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x + 2, y + height, z + 2),
					Blocks.AIR.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x + 2, y + height, z - 2),
					Blocks.AIR.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x - 1, y + height, z + 2),
					Blocks.AIR.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x - 1, y + height, z - 2),
					Blocks.AIR.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x + 1, y + height, z + 2),
					Blocks.AIR.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x + 1, y + height, z - 2),
					Blocks.AIR.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x - 2, y + height, z + 1),
					Blocks.AIR.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x - 2, y + height, z - 1),
					Blocks.AIR.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x + 2, y + height, z + 1),
					Blocks.AIR.getDefaultState(), 2);
			this.worldServerInstance.setBlockState(new BlockPos(x + 2, y + height, z - 1),
					Blocks.AIR.getDefaultState(), 2);
		}
		this.worldServerInstance.setBlockState(new BlockPos(x, y + 2, z), MBlocks.TARTHEUS_PORTAL.getDefaultState(), 2);
		return true;
	}
}