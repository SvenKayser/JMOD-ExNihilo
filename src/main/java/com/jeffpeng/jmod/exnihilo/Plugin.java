package com.jeffpeng.jmod.exnihilo;

import net.minecraft.item.Item;

import com.jeffpeng.jmod.JMODPlugin;
import com.jeffpeng.jmod.JMODPluginContainer;
import com.jeffpeng.jmod.API.forgeevents.JMODGetRepairAmountEvent;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import exnihilo.items.ItemCrook;
import exnihilo.items.hammers.ItemHammerBase;

public class Plugin extends JMODPlugin{

	public Plugin(JMODPluginContainer container) {
		super(container);
		// TODO Auto-generated constructor stub
	}
	
	@SubscribeEvent
	public void getRepairAmount(JMODGetRepairAmountEvent event){
		Item item = event.getItem();
		if(Loader.isModLoaded("exnihilo") && (item instanceof ItemHammerBase || item instanceof ItemCrook)){
			event.setRepairAmount(1F/4F);
		}
	}
	
	
}
