package com.jeffpeng.jmod.exnihilo.actions;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.jeffpeng.jmod.JMODRepresentation;
import com.jeffpeng.jmod.descriptors.ItemStackDescriptor;
import com.jeffpeng.jmod.primitives.BasicAction;

import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import exnihilo.registries.SieveRegistry;

public class AddSifting extends BasicAction{
	private ItemStackDescriptor output;
	private ItemStackDescriptor input;
	private int rarity;
	private ItemStack outputstack;
	private List<ItemStack> inputlist = new ArrayList<>();
	
	public AddSifting(JMODRepresentation owner,ItemStackDescriptor output, ItemStackDescriptor input,int rarity) {
		super(owner);
		
		log.info("addsifting "+output+" "+input+" "+rarity);
		
		if(output == null && input == null){
			log.info("[ExNihilo/AddSifting] Cannot add sifting from or to null stacks. Really.");
			this.valid = false;
			return;
		}

		this.output = output;
		this.input = input;
		this.rarity = rarity;
		this.valid = true;
	}
	
	@Override
	public boolean on(FMLLoadCompleteEvent event){
		if(!valid) return false;
		ItemStack ois = output.toItemStack();
		List<ItemStack> isl = input.getItemStackList();
		
		if(ois != null) isl.forEach((v) -> {
			Block block = lib.getBlockFromItemStack(v);
			if(block != null) SieveRegistry.register(block,ois.getItem(),ois.getItemDamage(),rarity);
			else log.warn(ois.toString() + "[ExNihilo/AddSifting] " + v + " from " + input + " doesn't have a block.");
		});
		
		else log.warn(ois.toString() + "[ExNihilo/AddSifting] " + output + " doesn't translate to a valid output.");
		
		/*
		
		
		
		if(ois != null){
			outputstack = (ItemStack)ois;
		} else {
			if(ois instanceof String && OreDictionary.doesOreNameExist((String)ois))	outputstack = lib.getFirstOreDictMatch(output);
			
			if(outputstack == null) {
				log.warn(ois.toString() + "[ExNihilo/AddSifting] Output "+(String)output+" is invalid since it's an empty ore list.");
				valid = false;
			}
		}
		
		if(iis instanceof ItemStack){
			Block iBlock = lib.getBlockFromItemStack((ItemStack)iis);
			if(iBlock == null){
				log.warn(iis.toString() + "[ExNihilo/AddSifting] Input "+(String)input+" is not a block.");
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
				log.warn(ois.toString() + "[ExNihilo/AddSifting] Input "+(String)input+" is invalid.");
				valid = false;
			}
		}
		
		if(valid) for(ItemStack is : inputlist){
			SieveRegistry.register(lib.getBlockFromItemStack(is),outputstack.getItem(),outputstack.getItemDamage(),rarity);
		}*/
		
		return valid;
	}
	
	@Override
	public void execute(){
		
	}
}
