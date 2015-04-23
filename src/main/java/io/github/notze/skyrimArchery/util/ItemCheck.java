package io.github.notze.skyrimArchery.util;

import org.bukkit.Material;
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
	public static boolean isDatBow(ItemStack item){
		if( !(item.getType().equals(Material.BOW)) ) return false;
		if( item.hasItemMeta() ) return false;
		return true;
	}
}
