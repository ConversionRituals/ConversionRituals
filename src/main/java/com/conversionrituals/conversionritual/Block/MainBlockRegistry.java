package com.conversionrituals.conversionritual.Block;

import com.conversionrituals.conversionritual.Item.MainItemRegistry;
import com.conversionrituals.conversionritual.Lib.RefStrings;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class MainBlockRegistry {
	
    public static Block ritualBlock;
    public static Block emeraldRitualComponent;
    public static Block emeraldRitual;
    public static Block brokenRitualBlock;
	
	public static void BlockRegistryMain(){
		initBlocks();
		registerBlocks();
		registerCraftingRecipes();
	}
	
	public static void initBlocks(){
		ritualBlock = new BlockRitualBlock().setBlockName("ritualBlock").setBlockTextureName(RefStrings.MODID + ":" + "ritualBlock");
    	brokenRitualBlock = new BlockBrokenRitualBlock().setBlockName("brokenRitualBlock").setBlockTextureName(RefStrings.MODID + ":" + "brokenRitualBlock");
	}
	
	public static void registerBlocks(){
		GameRegistry.registerBlock(ritualBlock, "ritualBlock");
    	GameRegistry.registerBlock(brokenRitualBlock, "brokenRitualBlock");
	}
	
	public static void registerCraftingRecipes(){
    	GameRegistry.addShapedRecipe(new ItemStack(ritualBlock, 1), new Object[]{
    		"OQO",
    		"QCQ",
    		"OQO",
    		'O', new ItemStack(Blocks.obsidian, 1),
    		'C', new ItemStack(MainItemRegistry.ritualCore, 1),
    		'Q', new ItemStack(Blocks.quartz_block, 1)
    		
    	});
	}
	
}
