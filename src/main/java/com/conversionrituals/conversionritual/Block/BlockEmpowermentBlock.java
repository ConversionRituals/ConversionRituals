package com.conversionrituals.conversionritual.Block;

import net.minecraft.block.Block;

public class BlockEmpowermentBlock {
	protected int empowermentLevel;
	protected Block block;
	
	public BlockEmpowermentBlock(Block block, int empowermentLevel){
		this.block = block;
		this.empowermentLevel = empowermentLevel;
	}

	public Block getBlock(){
		return block;
	}

	public int getEmpowermentLevel(){
		return empowermentLevel;
	}
}
