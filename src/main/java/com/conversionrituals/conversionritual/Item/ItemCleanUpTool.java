package com.conversionrituals.conversionritual.Item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCleanUpTool extends ItemConversionRituals{

	public static int range = 1; // * 3


	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ){
		if(world.isRemote) return false;

		if(itemstack.getItem() == MainItemRegistry.cleanUpTool){	
			
			switch(side){
			case 0:
			case 1:

				for(int a = -range; a <= range; a++){
					for(int b = -range; b <= range; b++){
						Block currentBlock = world.getBlock(x + a, y, z + b);
						if((currentBlock != Blocks.bedrock) && (currentBlock.getMaterial().isLiquid() == false)){
							world.setBlockToAir(x + a, y, z + b);							
						}
					}
				}

				break;
			case 2:
			case 3:

				for(int a = -range; a <= range; a++){
					for(int b = -range; b <= range; b++){
						Block currentBlock = world.getBlock(x + a, y, z + b);
						if((currentBlock != Blocks.bedrock) && (currentBlock.getMaterial().isLiquid() == false)){
							world.setBlockToAir(x + a, y + b, z);
						}
					}
				}

				break;
			case 4:
			case 5:

				for(int a = -range; a <= range; a++){
					for(int b = -range; b <= range; b++){
						Block currentBlock = world.getBlock(x + a, y, z + b);
						if((currentBlock != Blocks.bedrock) && (currentBlock.getMaterial().isLiquid() == false)){
							world.setBlockToAir(x, y + a, z + b);
						}
					}
				}

				break;
			default:
				System.out.println("Something went pretty wrong here, shouldn't happen at all...");
			}

			return true;
		}else{
			return false;
		}   
	}


}
