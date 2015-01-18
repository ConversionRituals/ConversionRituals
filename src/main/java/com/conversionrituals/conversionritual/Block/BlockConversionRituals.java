package com.conversionrituals.conversionritual.Block;

import com.conversionrituals.conversionritual.CreativeTab.tabConversionRituals;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockConversionRituals extends Block {

	public BlockConversionRituals() {
		super(Material.rock);
		setCreativeTab(tabConversionRituals.tabCR);
		setHardness(1.5F);
		setResistance(10.0F);
		setStepSound(soundTypePiston);
	}

}
