package edu.csula.storage;

import java.util.Collection;
import java.util.Optional;

/**
 * UsersDAO defines the database access layer between the business layer and
 * the database layer
 */
public interface GameNameDao {
	/**
	 * Returns name of game
	 */
	public String getName();

	/**
	 * Sets name of game
   */
	 public void setName(String name);
}
