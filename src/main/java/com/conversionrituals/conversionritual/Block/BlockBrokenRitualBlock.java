package com.conversionrituals.conversionritual.Block;

import java.util.Random;

import net.minecraft.item.Item;

import com.conversionrituals.conversionritual.Item.MainItemRegistry;

public class BlockBrokenRitualBlock extends BlockConversionRituals{
	
	@Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return MainItemRegistry.brokenRitualCore;
    }

}
