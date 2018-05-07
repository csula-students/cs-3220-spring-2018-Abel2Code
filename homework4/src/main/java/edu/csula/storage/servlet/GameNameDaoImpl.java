package edu.csula.storage.servlet;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import edu.csula.storage.GameNameDao;

/**
 * To abstract the storage access from the business layer using HttpSession
 */
public class GameNameDaoImpl implements GameNameDao {
	protected String name = "Code Clicker";
	private static GameNameDaoImpl gameNameDao;

	private GameNameDaoImpl(){}

	public static GameNameDaoImpl getInstance(){
		if(gameNameDao == null){
			gameNameDao = new GameNameDaoImpl();
		}
		return gameNameDao;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}




}
