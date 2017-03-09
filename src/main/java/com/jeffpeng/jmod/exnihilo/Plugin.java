package com.jeffpeng.jmod.exnihilo;

import net.minecraft.item.Item;

import com.jeffpeng.jmod.JMODPlugin;
import com.jeffpeng.jmod.JMODPluginContainer;

import cpw.mods.fml.common.Loader;
import exnihilo.items.ItemCrook;
import exnihilo.items.hammers.ItemHammerBase;

public class Plugin extends JMODPlugin{

	public Plugin(JMODPluginContainer container) {
		super(container);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Float getRepairAmount(Item item){
		if(Loader.isModLoaded("exnihilo") && (item instanceof ItemHammerBase || item instanceof ItemCrook)){
			return 1F/4F;
		}
		return null;
	}
	
	
}
