package io.github.notze.skyrimArchery.event;

import io.github.notze.skyrimArchery.Main;
import io.github.notze.skyrimArchery.data.PlayerData;
import io.github.notze.skyrimArchery.menu.SkillMenu;
import io.github.notze.skyrimArchery.persistence.Persistence;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class Skilling implements Listener{
	
	// reference to main class
	Main main;
	
	// vanilla level cost to skill
	final int SKILLCOST = 10;
	
	public Skilling(Main main){
		this.main = main;
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e){
		Main.debug("check for click in skill menu");
		if ( !(e.getInventory().getName().equals("Archery Skilltree")) ) return;
		ItemStack clicked = e.getCurrentItem();
		String clickedItem = "";
		if( clicked!=null && clicked.hasItemMeta() )
			clickedItem = clicked.getItemMeta().getDisplayName();
		Main.debug("clicked on: " + clickedItem + " in skilltree");
		e.setCancelled(true);
		
		Player p = (Player) e.getInventory().getHolder();
		if( p.getLevel()<SKILLCOST ) return;
		PlayerData data = Persistence.playerData.get(p);
		int actual;
		switch(clickedItem){
		case "Bullseye":
			Main.debug("skilling bullseye");
			actual = data.getBullseye();
			if( actual!=0 ) break;
			if( data.getLevel()<100 ) break;
			if( data.getQuick()==0 && data.getRanger()==0 ) break;
			data.setBullseye(1);
			SkillMenu.show(p);
			p.setLevel(p.getLevel()-10);
			break;
		case "Quick Shot":
			Main.debug("skilling quick shot");
			actual = data.getQuick();
			if( actual!=0 ) break;
			if( data.getLevel()<70 ) break;
			if( data.getPower()<1 ) break;
			data.setQuick(1);
			SkillMenu.show(p);
			p.setLevel(p.getLevel()-10);
			break;
		case "Ranger":
			Main.debug("skilling ranger");
			actual = data.getRanger();
			if( actual!=0 ) break;
			if( data.getLevel()<60 ) break;
			if( data.getHunter()<1 ) break;
			data.setRanger(1);
			SkillMenu.show(p);
			p.setLevel(p.getLevel()-10);
			break;
		case "Hunter's Discipline":
			Main.debug("skilling hunters discipline");
			actual = data.getHunter();
			if( actual!=0 ) break;
			if( data.getLevel()<50 ) break;
			if( data.getCrit()<1 ) break;
			data.setHunter(1);
			SkillMenu.show(p);
			p.setLevel(p.getLevel()-10);
			break;
		case "Power Shot":
			Main.debug("skilling power shot");
			actual = data.getPower();
			if( actual!=0 ) break;
			if( data.getLevel()<50 ) break;
			if( data.getOverdraw()<1 ) break;
			data.setPower(1);
			SkillMenu.show(p);
			p.setLevel(p.getLevel()-10);
			break;
		case "Critical Shot":
			Main.debug("skilling critical shot");
			actual = data.getCrit();
			if( actual>=3 ) break;
			if( data.getOverdraw()<1 ) break;
			if( actual==0 ){
				if( data.getLevel()<30 ) break;
			}else if( actual==1 ){
				if( data.getLevel()<60 ) break;
			}else if( actual==2 ){
				if( data.getLevel()<90 ) break;
			}
			data.setCrit(actual+1);
			SkillMenu.show(p);
			p.setLevel(p.getLevel()-10);
			break;
		case "Overdraw":
			Main.debug("skilling overdraw");
			actual = data.getOverdraw();
			if( actual>=5 ) break;
			if( actual==1 ){
				if( data.getLevel()<20 ) break;
			}else if( actual==2 ){
				if( data.getLevel()<40 ) break;
			}else if( actual==3 ){
				if( data.getLevel()<60 ) break;
			}else if( actual==4 ){
				if( data.getLevel()<80 ) break;
			}
			data.setOverdraw(actual+1);
			SkillMenu.show(p);
			p.setLevel(p.getLevel()-10);
			break;
		default:
			Main.debug("skilling nothing");
			SkillMenu.show(p);
			break;
		}
		
	}
}
