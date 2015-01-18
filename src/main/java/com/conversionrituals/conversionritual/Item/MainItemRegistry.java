package com.conversionrituals.conversionritual.Item;

import com.conversionrituals.conversionritual.Lib.RefStrings;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MainItemRegistry {
	
	public static Item ritualCore;
	public static Item brokenRitualCore;
	public static Item ritualPowerStaff;
	
	public static void ItemRegistryMain(){
		initItems();
		registerItems();
		registerCraftingRecipes();
	}
	
	public static void initItems(){
		ritualCore = new ItemConversionRituals().setUnlocalizedName("ritualCore").setTextureName(RefStrings.MODID + ":" + "ritualCore");
		brokenRitualCore = new ItemConversionRituals().setUnlocalizedName("brokenRitualCore").setTextureName(RefStrings.MODID + ":" + "brokenRitualCore");
		ritualPowerStaff = new ItemConversionRituals().setUnlocalizedName("ritualPowerStaff").setTextureName(RefStrings.MODID + ":" + "ritualPowerStaff");
	}
	
	public static void registerItems(){
		GameRegistry.registerItem(ritualCore, "ritualCore");
		GameRegistry.registerItem(brokenRitualCore, "brokenRitualCore");
		GameRegistry.registerItem(ritualPowerStaff, "ritualPowerStaff");
	}
	
	public static void registerCraftingRecipes(){
		GameRegistry.addShapedRecipe(new ItemStack(ritualCore, 1), new Object[]{
			" G ",
			"GDG",
			" G ",
			'G', new ItemStack(Blocks.glowstone, 1),
			'D', new ItemStack(Items.diamond, 1)
		});
		GameRegistry.addShapedRecipe(new ItemStack(ritualCore, 1), new Object[]{
			" G ",
			"GRG",
			" G ",
			'G', new ItemStack(Blocks.glowstone, 1),
			'R', new ItemStack(brokenRitualCore, 1)
		});
		GameRegistry.addShapedRecipe(new ItemStack(ritualPowerStaff, 1), new Object[]{
			"C  ",
			" B ",
			"  B",
			'C', new ItemStack(ritualCore, 1),
			'B', new ItemStack(Items.blaze_rod)
		});
	}

}
