package com.conversionrituals.conversionritual.Main;

import com.conversionrituals.conversionritual.Block.MainBlockRegistry;
import com.conversionrituals.conversionritual.Item.MainItemRegistry;
import com.conversionrituals.conversionritual.Lib.RefStrings;
import com.conversionrituals.conversionritual.Proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = RefStrings.MODID, name = RefStrings.NAME, version = RefStrings.VERSION)
public class ConversionRituals{
	
	@SidedProxy(clientSide = RefStrings.CLIENTPROXY, serverSide = RefStrings.COMMONPROXY)
	public static CommonProxy proxy;
	
	 @Instance(RefStrings.MODID)
	 public static ConversionRituals modInstance;

	@EventHandler
	public static void PreLoad(FMLInitializationEvent event){
		MainItemRegistry.ItemRegistryMain();
		MainBlockRegistry.BlockRegistryMain();
		proxy.registerRenderInfo();
		proxy.registerTileEntities();
	}

	@EventHandler
	public static void Load(FMLInitializationEvent event){
		proxy.registerNetworkStuff();
	}

	@EventHandler
	public static void PostLoad(FMLPostInitializationEvent event){
		
	}
}
