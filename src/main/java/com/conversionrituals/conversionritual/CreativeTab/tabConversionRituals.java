package com.conversionrituals.conversionritual.CreativeTab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class tabConversionRituals extends CreativeTabs{
	
	public static tabConversionRituals tabCR = new tabConversionRituals();
	
	public tabConversionRituals() {
		super("tabConversionRituals");
	}

	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(Blocks.obsidian);
	}

}
