package com.conversionrituals.conversionritual.Item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.conversionrituals.conversionritual.Block.BlockRitualBlock;
import com.conversionrituals.conversionritual.Block.MainBlockRegistry;
import com.conversionrituals.conversionritual.Client.KeyBindings;
import com.conversionrituals.conversionritual.Lib.RefStrings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRitualPowerStaff extends ItemConversionRituals{

	@SideOnly(Side.CLIENT)
	public static IIcon notActiavted;

	@SideOnly(Side.CLIENT)
	public static IIcon activated;

	private boolean isBinded = false;
	private int bindedBlockX;
	private int bindedBlockY;
	private int bindedBlockZ;
	private String ritual;

	/**
	 * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
	 * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
	 */
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int sidePressed, float par7, float par8, float par9){	
		if(!world.isRemote){
			if((world.getBlock(x, y, z) == MainBlockRegistry.ritualBlock) && player.isSneaking()){
				this.isBinded = true;
				setDamage(itemstack, 1);
				this.bindedBlockX = x;
				this.bindedBlockY = y;
				this.bindedBlockZ = z;
			}
		}
		return false;
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player){
		if(!world.isRemote){
			if(this.isBinded && !player.isSneaking()){
				System.out.println(itemstack.getItemDamage());

				BlockRitualBlock blockToActivate = (BlockRitualBlock)world.getBlock(bindedBlockX, bindedBlockY, bindedBlockZ);
				boolean hasRitualBeenPerformed = blockToActivate.activate(world, bindedBlockX, bindedBlockY, bindedBlockZ, player);
				
				if(hasRitualBeenPerformed){
					setDamage(itemstack, 0);
					this.isBinded = false;
					this.bindedBlockX = -1;
					this.bindedBlockY = -1;
					this.bindedBlockZ = -1;
				}
			}else if((this.isBinded && player.isSneaking()) && KeyBindings.CLEAR.isPressed()){
				setDamage(itemstack, 0);
				this.isBinded = false;
				this.bindedBlockX = -1;
				this.bindedBlockY = -1;
				this.bindedBlockZ = -1;
			}
		}
		return itemstack;
	}


	/**
	 * Gets an icon index based on an item's damage value
	 */
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int damage){
		if(damage == 0){
			return notActiavted;
		}else if(damage == 1){
			return activated;
		}else{
			return notActiavted;
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconregister){
		notActiavted = iconregister.registerIcon(RefStrings.MODID + ":" + "ritualPowerStaff");
		activated = iconregister.registerIcon(RefStrings.MODID + ":" + "ritualPowerStaffActivated");
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par3) {
		if(itemstack.getItemDamage() == 0){
			list.add("Not bound to a Ritual Block !");
			list.add("Shift-right click a Ritual Block to bind !");
		}else if(itemstack.getItemDamage() == 1){
			list.add("Bound to Ritual Block at " + this.bindedBlockX + "x, " + this.bindedBlockY + "y, " + bindedBlockZ + "z");
			list.add("Put in Crafting Grid to unbind !");
		}
	}
	

}
