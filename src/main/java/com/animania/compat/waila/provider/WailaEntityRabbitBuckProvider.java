package com.animania.compat.waila.provider;

import java.util.List;
import java.util.UUID;

import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeBase;
import com.animania.common.helper.AnimaniaHelper;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class WailaEntityRabbitBuckProvider extends WailaEntityAnimalProviderBase
{

	@Override
	public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
		currenttip = super.getWailaBody(entity, currenttip, accessor, config);

		if (accessor.getPlayer().isSneaking()) {
			String mate = accessor.getNBTData().getString("MateUUID");
			World world = entity.world;

			if (!mate.equals("")) {
				for (Entity e : AnimaniaHelper.getEntitiesInRange(EntityRabbitDoeBase.class, 20, world, entity)) {
					UUID id = e.getPersistentID();
					if (id.toString().equals(mate)) {
						String name = e.getCustomNameTag();
						if (!name.equals(""))
							currenttip.add(I18n.translateToLocal("text.waila.mated") + " (" + name + ")");
						else
							currenttip.add(I18n.translateToLocal("text.waila.mated"));

						return currenttip;
					}
				}

				//currenttip.add(I18n.translateToLocal("text.waila.matemissing"));

			}

		}
		return currenttip;

	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, Entity ent, NBTTagCompound tag, World world) {
		NBTTagCompound comp = ent.getEntityData();

		String mate = comp.getString("MateUUID");
		if (!mate.equals(""))
			tag.setString("MateUUID", mate);

		return super.getNBTData(player, ent, tag, world);
	}

}
