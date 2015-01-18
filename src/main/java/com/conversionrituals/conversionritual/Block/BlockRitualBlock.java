package com.conversionrituals.conversionritual.Block;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.conversionrituals.conversionritual.Item.MainItemRegistry;

public class BlockRitualBlock extends BlockConversionRituals{
	
	LinkedList<BlockEmpowermentBlock> empowermentBlocks;
	
	int diameter = 7;
	
	public BlockRitualBlock() {
		empowermentBlocks = new LinkedList<BlockEmpowermentBlock>();
		
		//Initializing Blocks that can be used for increasing the max. conversions possible
		empowermentBlocks.add(new BlockEmpowermentBlock(Blocks.iron_block, 6));
		empowermentBlocks.add(new BlockEmpowermentBlock(Blocks.gold_block, 9));
		empowermentBlocks.add(new BlockEmpowermentBlock(Blocks.diamond_block, 32));
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if(world.isRemote){
			return false;
		}else{
			
			//max. conversions possible
			int maxConversions = 4;
			
			//Looping through all blocks beneath the ritual altar
			for(int k = diameter; k > 0; k--){
				for(int l = diameter; l > 0; l--){
				
					//Getting from the viewing point of the ritual altar, because it's staring at the top left corner
					Block currentBlock = world.getBlock(x + k - (MathHelper.floor_double(diameter / 2) + 1), y - 1, z + l - (MathHelper.floor_double(diameter / 2) + 1));
					
					//Looping through all the empowerment blocks
					for(int j = 0; j < empowermentBlocks.size(); j++){
					
						//If one of the empowerment blocks matches the current block
						if(currentBlock == empowermentBlocks.get(j).getBlock()){
							
							//Increase the max. conversions possible by the empowerment level of that block
							maxConversions += empowermentBlocks.get(j).getEmpowermentLevel();
						}
					}
				}
			}
			
			//Checks of the current held item is null (empty hand) or is anything but the ritual power staff
			if(player.inventory.getCurrentItem() == null || (player.inventory.getCurrentItem().getItem() != MainItemRegistry.ritualPowerStaff)){
			
				//Checks if a ritual is activatable
				return checkForRitual(world, x, y, z, player, maxConversions);
			}else{
			
				//If it's the ritual power staff it print the current max. possible conversion with the current block to the player
				player.addChatMessage(new ChatComponentText("Total conversions possible with this setup : " + maxConversions));
				return true;
			}
		}
	}
	
	
	private boolean checkForRitual(World world, int x, int y, int z, EntityPlayer player, int maxConversions){
		//Checks if the emerald ritual is possible to perform
		return checkForEmeraldRitual(world, x, y, z, player, maxConversions);
	}
	
	private boolean checkForEmeraldRitual(World world, int x, int y, int z, EntityPlayer player, int maxConversions){
		
		//Checks if the required block (Lapis Lazuli) for this ritual is present
		if(world.getBlock(x, y - 1, z) == Blocks.lapis_block){
			
			//How many conversions have been performed
			int conversionsDone = 0;
			
			//Setting from the viewing point of the ritual altar, because it's staring at the top left corner
			for(int i = diameter; i > 0; i--){
				for(int j = diameter; j > 0; j--){
					
					//Sets all blocks in the diameter² beneath it to mossy cobblestone 
					world.setBlock(x + i - (MathHelper.floor_double(diameter / 2) + 1), y - 1, z + j - (MathHelper.floor_double(diameter / 2) + 1), Blocks.mossy_cobblestone);
				}
			}
			
			//Creates a new bounding box around the ritual altar
			AxisAlignedBB area = AxisAlignedBB.getBoundingBox((double)(x - (MathHelper.floor_double(diameter / 2) + 1)), (double)(y - 1), (double)(z - (MathHelper.floor_double(diameter / 2) + 1)), (double)(x + (MathHelper.floor_double(diameter / 2) + 1)), (double)(y + 1), (double)(z + (MathHelper.floor_double(diameter / 2) + 1)));
		
			//Creates a list of all EntitiyItems in this bounding box
			List entityItemsInArea = world.getEntitiesWithinAABB(EntityItem.class, area);
			
			//Loops through all EntitiyItems
			for(int a = 0; a < entityItemsInArea.size(); a++){
				
				//Checks if it can still perform a conversion
				if(conversionsDone <= maxConversions - 1){
					
					//Creates a new EntityItem for usage
					EntityItem currentEntityItem = (EntityItem)entityItemsInArea.get(a);
					
					//Checks if the current EntityItem is a diamond
					if(currentEntityItem.getEntityItem().getItem() == Items.diamond){
						
						//Checks if the stack size of the current EntityItem is less or equal then the left conversions
						if(currentEntityItem.getEntityItem().stackSize <= maxConversions - conversionsDone){
							
							//Adds the stack size of the current item stack to the total performed conversions
							conversionsDone += currentEntityItem.getEntityItem().stackSize;
							
							//Sets the item and stack size to the new EntityItem
							currentEntityItem.setEntityItemStack(new ItemStack(Items.emerald, currentEntityItem.getEntityItem().stackSize));
							
							//Places a cobblestone beneath the converted EntityItem
							world.setBlock(MathHelper.floor_double(currentEntityItem.posX), MathHelper.floor_double(currentEntityItem.posY) - 1, MathHelper.floor_double(currentEntityItem.posZ), Blocks.cobblestone);
							
						}else{
							
							//Creates a new EntityItem with the stack size of the left conversions
							EntityItem newEntityItem = new EntityItem(world, currentEntityItem.posX, currentEntityItem.posY, currentEntityItem.posZ, new ItemStack(Items.emerald, (maxConversions) - conversionsDone));
							
							//Sets the stack size to the new Item according to the old stack size - the left ones
							currentEntityItem.setEntityItemStack(new ItemStack(Items.diamond, currentEntityItem.getEntityItem().stackSize - ((maxConversions) - conversionsDone)));
							
							//Spawns the new EntityItem into the world
							world.spawnEntityInWorld(newEntityItem);
							
						}
						
					}
					
					//Updates the EntityItem in the List
					entityItemsInArea.set(a, currentEntityItem);
				}
			}
			
			//Sets the ritual block to used form
			world.setBlock(x, y, z, MainBlockRegistry.brokenRitualBlock);
			
			//Prints to the player that the ritual was successful performed
			player.addChatMessage(new ChatComponentText("Ritual successful performed ! You've converted " + conversionsDone + " diamonds !"));
			
			//Resets how many conversions have been performed
			conversionsDone = 0;
			
			return true;
		
		}else{
			
			//Is the required block to perform this ritual not present it prints it to the player
			player.addChatMessage(new ChatComponentText("Lapis Lazuli Block, required for activation, is missing !"));
			return false;
		}
	}


}
