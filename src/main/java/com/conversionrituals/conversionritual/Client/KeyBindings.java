package com.conversionrituals.conversionritual.Client;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;


public enum KeyBindings {
	
	CLEAR("key.conversionrituals.clearRitualPowerStaff", Keyboard.KEY_G);
	
	private final KeyBinding keybinding;
	
	private KeyBindings(String keyName, int defaultKeyCode){
		keybinding = new KeyBinding(keyName, defaultKeyCode, "key.categories.conversionrituals");
	}
	
	public KeyBinding getKeyBinding(){
		return keybinding;
	}
	
	public boolean isPressed(){
		return keybinding.isPressed();
	}
}
