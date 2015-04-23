package io.github.notze.skyrimArchery.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import io.github.notze.skyrimArchery.Main;
import io.github.notze.skyrimArchery.data.PlayerDataList;

/**
 * This class stores and loads all skill values and experience.
 * @author notze
 *
 */
public class Persistence {

	public static PlayerDataList playerData = new PlayerDataList();
	
	/**
	 * load all data from config
	 */
	public static void load(){
		
		File f = new File(Main.dataFolder + "/database");
		if(!f.exists() || f.isDirectory()) return;
			
		try {
			FileInputStream in = new FileInputStream(Main.dataFolder + "/database");
			ObjectInputStream ois = new ObjectInputStream(in);
			
			playerData = (PlayerDataList) ois.readObject();
			ois.close();
			in.close();
			Main.debug("loading data done!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * save all data to config
	 */
	public static void save(){
		
		try {
			FileOutputStream out = new FileOutputStream(Main.dataFolder + "/database");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			
			oos.writeObject(playerData);
			oos.close();
			out.close();
			Main.debug("saving data done!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
