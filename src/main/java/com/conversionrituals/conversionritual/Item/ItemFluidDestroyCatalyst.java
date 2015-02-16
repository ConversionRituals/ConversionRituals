package com.conversionrituals.conversionritual.Item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.conversionrituals.conversionritual.Lib.RefStrings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFluidDestroyCatalyst extends ItemConversionRituals{
	
	int range = 3;

	@SideOnly(Side.CLIENT)
	public static IIcon notActiavted;

	@SideOnly(Side.CLIENT)
	public static IIcon activated;


	public ItemFluidDestroyCatalyst(){
		setMaxStackSize(1);
	}



	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		if(itemstack.getItemDamage() == 0){
			itemstack.setItemDamage(1);
		}else if(itemstack.getItemDamage() == 1){
			itemstack.setItemDamage(0);
		}
		return itemstack;
	}


	public void onUpdate(ItemStack itemstack, World world, Entity player, int par2, boolean par3) {
		if(world.isRemote) return;
		
		boolean hitLava = false;
		
		if(itemstack.getItemDamage() == 1){

			int xCoord = (int)player.posX;
			int yCoord = (int)player.posY;
			int zCoord = (int)player.posZ;
			
			for (int x = -range; x <= range; x++) {
				for (int y = -range; y <= range; y++) {
					for (int z = -range; z <= range; z++) {
						Block block = world.getBlock(xCoord + x, yCoord + y, zCoord + z);
						
						if(block != null){
							Material m = block.getMaterial();
							
							if(m.isLiquid()){
								hitLava |= m == Material.lava;
								world.setBlockToAir(xCoord + x, yCoord + y, zCoord + z);
							}
						}
					}
				}
			}
			
		}	
		
		if (hitLava) {
			player.setFire(6);
			itemstack.setItemDamage(0);
		}

	}


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
		notActiavted = iconregister.registerIcon(RefStrings.MODID + ":" + "fluidDestroyCatalyst");
		activated = iconregister.registerIcon(RefStrings.MODID + ":" + "fluidDestroyCatalystActivated");
	}

}
