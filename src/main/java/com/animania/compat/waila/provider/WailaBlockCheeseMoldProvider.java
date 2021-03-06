package com.animania.compat.waila.provider;

import java.util.List;

import com.animania.common.tileentities.TileEntityCheeseMold;
import com.animania.config.AnimaniaConfig;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class WailaBlockCheeseMoldProvider implements IWailaDataProvider
{

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return ItemStack.EMPTY;
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return null;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		 TileEntityCheeseMold tile = (TileEntityCheeseMold) accessor.getTileEntity();

	        ItemStack stack = tile.getItemHandler().getStackInSlot(0);
	        FluidStack fluid = tile.getFluidHandler().getFluid();
	        int progress = tile.getProgress();
	        
	        if(stack.isEmpty())
				currenttip.add(I18n.translateToLocal("text.waila.aging") + ": " + (int) (((float) progress / (float) AnimaniaConfig.gameRules.cheeseMaturityTime) * 100) + "%");
	        else
				currenttip.add(I18n.translateToLocal("text.waila.aging") + ": " + "100%");

	        if (!stack.isEmpty())
	            currenttip.add(stack.getCount() + " " + stack.getDisplayName());

	        if (fluid != null)
	            currenttip.add(fluid.getLocalizedName() + ", " + fluid.amount + "mB");

	        return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return null;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, BlockPos pos)
	{
		return null;
	}

}
