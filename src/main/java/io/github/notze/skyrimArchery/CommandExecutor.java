package io.github.notze.skyrimArchery;

import io.github.notze.skyrimArchery.menu.SkillMenu;
import io.github.notze.skyrimArchery.persistence.Persistence;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class executes the commands.
 * @author notze
 *
 */
public class CommandExecutor implements org.bukkit.command.CommandExecutor {

	Main main;
	
	public CommandExecutor(Main main){
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		
		if( command.getName().equals("sahelp") )
			// TODO implement
			return true;
		else if( command.getName().equals("saskill") )
			if( !(sender instanceof Player) ){
				sender.sendMessage("This command can only be run by a player!");
				return true;
			}else{
				SkillMenu.show((Player) sender); 
				return true;
			}
		else if( command.getName().equals("sacheat") )
			if( !(sender instanceof Player) ){
				sender.sendMessage("This command can only be run by a player!");
				return true;
			}else{
				Persistence.playerData.get((Player) sender).addExperience(10000);
				return true;
			}
		
		return false;
	}
}
