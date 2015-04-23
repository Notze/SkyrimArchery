package io.github.notze.skyrimArchery.menu;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.notze.skyrimArchery.data.PlayerData;
import io.github.notze.skyrimArchery.persistence.Persistence;

/**
 * This class creates and shows the skill menu
 * @author notze
 *
 */
public class SkillMenu {

	/**
	 * Shows the given players skill menu to the player
	 * @param player player who typed in /saskill
	 */
	public static void show(Player player){
		player.openInventory(getSkillInventory(player));
	}
	
	private static Inventory getSkillInventory(Player p){
		Inventory inv = Bukkit.createInventory(p, 54, "Archery Skilltree");
		PlayerData data = Persistence.playerData.get(p);
		
		ItemStack level = new ItemStack(Material.DIAMOND);
		level = setText(level, "Archery Level: " + data.getLevel(), null);
		
		ItemStack overdraw = new ItemStack(Material.STRING);
		String[] overdrawlore = {"+20/40/60/80/100% Damage", "Archery 0/20/40/60/80 needed"};
		overdraw = setText(overdraw, "Overdraw", Arrays.asList(overdrawlore));
		
		ItemStack critshot = new ItemStack(Material.FLINT);
		String[] critshotlore = {"+10/15/20% Crit Chance","+0/25/50% Crit Damage","Archery 30/60/90 needed","Overdraw 1 needed"};
		critshot = setText(critshot, "Critical Shot", Arrays.asList(critshotlore));
		
		ItemStack powershot = new ItemStack(Material.SULPHUR);
		String[] powershotlore = {"+50% Chance for Knockback","Archery 50 needed","Overdraw 1 needed"};
		powershot = setText(powershot, "Power Shot", Arrays.asList(powershotlore));
		
		ItemStack hunter = new ItemStack(Material.ARROW);
		String[] hunterlore = {"Recover arrows from dead bodies","Archery 50 needed","Critical Shot 1 needed"};
		hunter = setText(hunter, "Hunter's Discipline", Arrays.asList(hunterlore));
		
		ItemStack ranger = new ItemStack(Material.BOW);
		String[] rangerlore = {"Move faster with a drawn bow","Archery 60 needed","Hunter's Discipline needed"};
		ranger = setText(ranger, "Ranger", Arrays.asList(rangerlore));
		
		ItemStack quickshot = new ItemStack(Material.SUGAR);
		String[] quickshotlore = {"Draw your bow faster","Archery 70 needed","Power Shot needed"};
		quickshot = setText(quickshot, "Quick Shot", Arrays.asList(quickshotlore));
		
		ItemStack bullseye = new ItemStack(Material.EYE_OF_ENDER);
		String[] bullseyelore = {"+15% Chance of paralyzing"," the target for a few seconds","Archery 100 needed","Quick Shot or Ranger needed"};
		bullseye = setText(bullseye, "Bullseye", Arrays.asList(bullseyelore));
		
		
		inv.setItem(0, level);
		bullseye.setAmount(data.getBullseye());
		inv.setItem(4, bullseye);
		inv.setItem(12, new ItemStack(Material.STICK));
		quickshot.setAmount(data.getQuick());
		inv.setItem(14, quickshot);
		ranger.setAmount(data.getRanger());
		inv.setItem(21, ranger);
		inv.setItem(23, new ItemStack(Material.STICK));
		hunter.setAmount(data.getHunter());
		inv.setItem(30, hunter);
		powershot.setAmount(data.getPower());
		inv.setItem(32, powershot);
		critshot.setAmount(data.getCrit());
		inv.setItem(39, critshot);
		inv.setItem(41, new ItemStack(Material.STICK));
		overdraw.setAmount(data.getOverdraw());
		inv.setItem(49, overdraw);
		
		return inv;
	}
	
	private static ItemStack setText(ItemStack item, String name, List<String> lores){
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(name);
		im.setLore(lores);
		item.setItemMeta(im);
		return item;
	}
}
