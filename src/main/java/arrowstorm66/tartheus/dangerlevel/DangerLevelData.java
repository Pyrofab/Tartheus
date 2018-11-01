package arrowstorm66.tartheus.dangerlevel;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;

public class DangerLevelData implements IDangerLevel{
	
	int dangerlevel;
	private final EntityLivingBase entity;

	public DangerLevelData(@Nullable EntityLivingBase entity) {
		this.entity = entity;
	}

	@Override
	public int getDangerLevel() {
		return dangerlevel;
	}

	@Override
	public void setDangerLevel(int dangerlevel) {		
		this.dangerlevel = dangerlevel;
	}
}
