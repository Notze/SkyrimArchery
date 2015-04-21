package io.github.notze.skyrimArchery.util;

import org.bukkit.inventory.ItemStack;

/**
 * This class provides utility functions such as a check for the right item
 * @author notze
 *
 */
public class ItemCheck {

	/**
	 * returns true if the given item is Dat Bow otherwise false
	 * @param item item to check
	 * @return true if item is dat bow otherwise false
	 */
	public boolean isDatBow(ItemStack item){
		// requirements:
		//  name: Dat Bow!
		//  enchantments: none!
		//  material: bow
		return false;
	}
}
