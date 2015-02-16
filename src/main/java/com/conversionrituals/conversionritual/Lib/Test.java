package com.conversionrituals.conversionritual.Lib;

import net.minecraft.block.Block;

public class Test {
	
	Block block;
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	int x;
	int y;
	int z;
	
	public Test(Block block, int x, int y , int z){
		this.block = block;
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
