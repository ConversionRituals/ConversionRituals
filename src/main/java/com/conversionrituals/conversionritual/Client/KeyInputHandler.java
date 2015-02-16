package com.conversionrituals.conversionritual.Client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class KeyInputHandler {
	
	private KeyBindings getPressedKey(){
		for(KeyBindings key : KeyBindings.values()){
			if(key.isPressed()){
				return key;
			}
		}
		return null;
	}
	
	@SubscribeEvent
	public void handleKeyInputEvent(InputEvent.KeyInputEvent event){
		KeyBindings key = getPressedKey();
		
		if(key != null){
			switch(key){
			case CLEAR:
				System.out.println("Ritual Power Staff cleared!");
			}
		}
	}
}
