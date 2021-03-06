package com.animania.common.entities.generic.ai;

import com.animania.api.interfaces.ISleeping;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.util.math.MathHelper;

public class GenericAIFollowOwner<T extends EntityTameable & ISleeping> extends EntityAIFollowOwner
{
	private final T tameable;

	public GenericAIFollowOwner(T tameableIn, double followSpeedIn, float minDistIn, float maxDistIn)
	{
		super(tameableIn, followSpeedIn, minDistIn, maxDistIn);
		this.tameable = tameableIn;
	}

	public boolean shouldExecute()
	{

		if (this.tameable.getSleeping())
			return false;

		return super.shouldExecute();
	}

	@Override
	protected boolean isTeleportFriendlyBlock(int x, int p_192381_2_, int y, int p_192381_4_, int p_192381_5_)
	{
		if(!AnimaniaConfig.gameRules.tamedAnimalsTeleport)
			return false;
		
		return super.isTeleportFriendlyBlock(x, p_192381_2_, y, p_192381_4_, p_192381_5_);
	}
}