package com.conversionrituals.conversionritual.Proxy;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import Renderer.ItemRenderRitualBlock;
import Renderer.RenderRitualBlock;
import TileEntity.TileEntityRitualBlock;

import com.conversionrituals.conversionritual.Block.MainBlockRegistry;
import com.conversionrituals.conversionritual.Client.KeyBindings;
import com.conversionrituals.conversionritual.Client.KeyInputHandler;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy{
	
	public void preInit(){
		registerKeybinds();
	}
	
	private void registerKeybinds(){
		FMLCommonHandler.instance().bus().register(new KeyInputHandler());
		
		for(KeyBindings key : KeyBindings.values()){
			ClientRegistry.registerKeyBinding(key.getKeyBinding());
		}
	}
	
	public void registerRenderer(){
		TileEntitySpecialRenderer render = new RenderRitualBlock();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRitualBlock.class, render);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(MainBlockRegistry.ritualBlock), new ItemRenderRitualBlock(render, new TileEntityRitualBlock()));
	}
	
	public void registerTileEntitySpecialRenderer(){
		
	}
}
