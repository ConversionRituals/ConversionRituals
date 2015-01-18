package com.conversionrituals.conversionritual.Block;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.conversionrituals.conversionritual.Item.MainItemRegistry;
import com.sun.xml.internal.stream.Entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockRitualBlock extends BlockConversionRituals{

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if(world.isRemote){

			return false;

		}else{

			Item it = Items.apple;

			try{
				it = player.inventory.getStackInSlot(player.inventory.currentItem).getItem();
			}catch(NullPointerException e){
				e.printStackTrace();
			}
			
			int maxConversions = 4;

			for(int k = 7; k > 0; k--){
				for(int l = 7; l > 0; l--){
					Block currentBlock = world.getBlock(x + k - 4, y - 1, z + l - 4);

					if(currentBlock == Blocks.iron_block){
						maxConversions += 6;
					}else if(currentBlock == Blocks.gold_block){
						maxConversions += 9;
					}else if(currentBlock == Blocks.diamond_block){
						maxConversions += 32;
					}
				}
			}

			if(it == MainItemRegistry.ritualPowerStaff){

				player.addChatMessage(new ChatComponentText("Total conversions possible with this setup : " + maxConversions));
				return true;

			}else{

				return checkForRitual(world, x, y, z, player, maxConversions);

			}

		}

	}

	private boolean checkForRitual(World world, int x, int y, int z, EntityPlayer player, int maxConversions){
		return checkForEmeraldRitual(world, x, y, z, player, maxConversions);
	}

	private boolean checkForEmeraldRitual(World world, int x, int y, int z, EntityPlayer player, int maxConversions){
		if(world.getBlock(x, y - 1, z) == Blocks.lapis_block){

			int totalConversion = 0;

			for(int i = 7; i > 0; i--){
				for(int j = 7; j > 0; j--){
					world.setBlock(x + i - 4, y - 1, z + j - 4, Blocks.mossy_cobblestone);
				}
			}

			/**
			 * Returns a bounding box with the specified bounds. Args: minX, minY, minZ, maxX, maxY, maxZ
			 */
			AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox((double)(x - 4), (double)(y - 1), (double)(z - 4), (double)(x + 4), (double)(y + 1), (double)(z + 4));

			List item = world.getEntitiesWithinAABB(EntityItem.class, aabb);

			for(int a = 0; a < item.size(); a++){
				if(totalConversion <= maxConversions - 1){

					EntityItem currentItem = (EntityItem)item.get(a);

					if(currentItem.getEntityItem().getItem() == Items.diamond){

						if(currentItem.getEntityItem().stackSize <= maxConversions - totalConversion){

							totalConversion += currentItem.getEntityItem().stackSize;
							currentItem.setEntityItemStack(new ItemStack(Items.emerald, currentItem.getEntityItem().stackSize));
							world.setBlock(MathHelper.floor_double(currentItem.posX), MathHelper.floor_double(currentItem.posY) - 1, MathHelper.floor_double(currentItem.posZ), Blocks.cobblestone);

						}
					}
					item.set(a, currentItem);
				}

			}

			totalConversion = 0;

			world.setBlock(x, y, z, MainBlockRegistry.brokenRitualBlock);

			String color = "§4§kA" + getColor() + "BOOM" + "§4§kA";
			player.addChatMessage(new ChatComponentText(color));
			return true;


		}else{
			pP(x, y - 1, z, "Lapis Lazuli Block", player);
			return false;
		}
	}

	private void pP(int x, int y, int z, String Block, EntityPlayer player){
		player.addChatComponentMessage(new ChatComponentText(Block + " missing at " + x + "x, " + y + "y, " + z + "z"));
	}

	private String getColor(){
		int rand = new Random().nextInt(16);
		String str = "§0";

		switch(rand){
		case 0:
			str = "§0";
			break;
		case 1:
			str = "§1";    
			break;
		case 2:
			str = "§2";
			break;
		case 3:
			str = "§3";
			break;
		case 4:
			str = "§4";
			break;
		case 5:
			str = "§5";
			break;
		case 6:
			str = "§6";
			break;
		case 7:
			str = "§7";
			break;
		case 8:
			str = "§8";
			break;
		case 9:
			str = "§9";
			break;
		case 10:
			str = "§a";
			break;
		case 11:
			str = "§b";
			break;
		case 12:
			str = "§c";
			break;
		case 13:
			str = "§d";
			break;
		case 14:
			str = "§e";
			break;
		case 15:
			str = "§f";
			break;
		default:
			System.out.println("ERROR AT GETTING COLOR");
		}

		return str;
	}



}
