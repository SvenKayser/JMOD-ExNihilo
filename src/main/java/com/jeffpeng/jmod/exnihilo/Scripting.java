package com.jeffpeng.jmod.exnihilo;

import com.jeffpeng.jmod.JMODRepresentation;
import com.jeffpeng.jmod.exnihilo.actions.AddSifting;
import com.jeffpeng.jmod.exnihilo.actions.RemoveSifting;
import com.jeffpeng.jmod.primitives.ModScriptObject;

public class Scripting extends ModScriptObject {
	public Scripting(JMODRepresentation owner){
		super(owner);
	}
	
	public void addResult(String output, String input, int rarity){
		if(owner.testForMod("exnihilo")) new AddSifting(owner,output,input,rarity);
	}
	
	public void removeResult(String output, String input){
		if(owner.testForMod("exnihilo")) new RemoveSifting(owner,output,input);
	}
	
	public void removeResultFromAllBlocks(String output){
		if(owner.testForMod("exnihilo")) new RemoveSifting(owner,output,null);
	}
	
	public void removeAllResultsFromBlock(String input){
		if(owner.testForMod("exnihilo")) new RemoveSifting(owner,null,input);
	}
	
}
