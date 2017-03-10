package com.jeffpeng.jmod.exnihilo.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;







import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.jeffpeng.jmod.JMOD;
import com.jeffpeng.jmod.JMODRepresentation;
import com.jeffpeng.jmod.Lib;
import com.jeffpeng.jmod.primitives.BasicAction;

import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import exnihilo.registries.SieveRegistry;
import exnihilo.registries.helpers.SiftReward;

public class RemoveSifting extends BasicAction{
	private String output;
	private String input;
	private ItemStack outputstack;
	private List<ItemStack> inputlist = new ArrayList<>();

	
	public RemoveSifting(JMODRepresentation owner,String output, String input) {
		super(owner);
		
		log.info("removesifting "+output+" "+input);
		
		if(output == null && input == null){
			log.info("[JMOD ExNihilo/RemoveSifting] Cannot remove sifting from null stacks to null stacks. Really.");
			this.valid = false;
			return;
		}

		this.output = output;
		this.input = input;
		this.valid = true;
	}
	
	@Override
	public boolean on(FMLLoadCompleteEvent event){
		JMOD.LOG.info("###xnrs");
		if(!valid) return false;

		if(output != null){
			Object ois = lib.stringToItemStack(output);
			if(ois instanceof ItemStack){
				outputstack = (ItemStack)ois;
			} else {
				if(ois instanceof String && OreDictionary.doesOreNameExist((String)ois))	outputstack = lib.getFirstOreDictMatch(output);
				
				if(outputstack == null) {
					log.warn(ois.toString() + "[JMOD ExNihilo/RemoveSifting] Output "+(String)output+" is invalid since it's an empty ore list.");
					valid = false;
				}
			}
		} else {
			outputstack = null;
		}
		
		if(input != null){
			Object iis = lib.stringToItemStack(input);
			if(iis instanceof ItemStack){
				Block iBlock = lib.getBlockFromItemStack((ItemStack)iis);
				if(iBlock == null){
					log.warn(iis.toString() + "[JMOD ExNihilo/RemoveSifting] Input "+(String)input+" is not a block.");
					valid = false;
				} else {
					inputlist.add((ItemStack)iis);
				}
			
			} else {
				if(iis instanceof String && OreDictionary.doesOreNameExist((String)iis)){
					List<ItemStack> oreDictInputs = OreDictionary.getOres((String)iis, false);
					for(ItemStack oreDictInput : oreDictInputs) if(lib.itemStackIsBlock(oreDictInput)) inputlist.add(oreDictInput);
				}
				
				if(inputlist.size() == 1) {
					log.warn(iis.toString() + "[JMOD ExNihilo/RemoveSifting] Input "+(String)input+" is invalid.");
					valid = false;
				}
			}
		}
		
		if(valid) 
			if(inputlist.size() > 0){
				for(ItemStack is : inputlist){
					if(outputstack == null) SieveRegistry.unregisterAllRewardsFromBlock(Block.getBlockFromItem(is.getItem()), is.getItemDamage()); 
					else SieveRegistry.unregisterReward(Block.getBlockFromItem(is.getItem()), is.getItemDamage(), ((ItemStack)outputstack).getItem(), ((ItemStack)outputstack).getItemDamage());
				}
			} else {
				SieveRegistry.unregisterRewardFromAllBlocks(((ItemStack)outputstack).getItem(), ((ItemStack)outputstack).getItemDamage());
			}
		
		return valid;
	}
	
	@Override
	public void execute(){
		
	}
}
