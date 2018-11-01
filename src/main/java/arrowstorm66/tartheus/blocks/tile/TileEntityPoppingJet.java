package arrowstorm66.tartheus.blocks.tile;

import net.minecraft.tileentity.*;
import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.blocks.BlockFlameJet;
import arrowstorm66.tartheus.blocks.BlockFlameJet.FireJetVariant;
import net.minecraft.block.properties.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.nbt.*;

public class TileEntityPoppingJet extends TileEntity implements ITickable
{
    private int counter;
    private FireJetVariant nextVariant;
    
    public TileEntityPoppingJet(final FireJetVariant variant) {
        this.counter = 0;
        this.nextVariant = variant;
    }
    
    public TileEntityPoppingJet() {
        this.counter = 0;
    }
    
    public void update() {
        if (++this.counter >= 60) {
            this.counter = 0;
            if (!this.world.isRemote && this.world.getBlockState(this.pos).getBlock() == MBlocks.FLAME_JET) {
                this.world.setBlockState(this.pos, MBlocks.FLAME_JET.getDefaultState().withProperty((IProperty)BlockFlameJet.VARIANT, (Comparable)this.nextVariant), 3);
            }
        }
        else if (this.counter % 20 == 0) {
            for (int i = 0; i < 8; ++i) {
                this.world.spawnParticle(EnumParticleTypes.LAVA, this.pos.getX() + 0.5, this.pos.getY() + 0.75, this.pos.getZ() + 0.5, 0.0, 0.0, 0.0, new int[0]);
            }
            this.world.playSound((EntityPlayer)null, this.pos, SoundEvents.BLOCK_LAVA_POP, SoundCategory.BLOCKS, 0.2f + this.world.rand.nextFloat() * 0.2f, 0.9f + this.world.rand.nextFloat() * 0.15f);
        }
    }
    
    public void readFromNBT(final NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.nextVariant = FireJetVariant.values()[compound.getInteger("NextMeta")];
    }
    
    public NBTTagCompound writeToNBT(final NBTTagCompound compound) {
        final NBTTagCompound ret = super.writeToNBT(compound);
        ret.setInteger("NextMeta", this.nextVariant.ordinal());
        return ret;
    }
}
