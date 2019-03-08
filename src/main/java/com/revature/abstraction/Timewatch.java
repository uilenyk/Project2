package com.revature.abstraction;

import java.time.LocalDateTime;

public interface Timewatch {

	/**
	 * Sets the life of listing to certain amount of days and sets it's activity
	 * 
	 * @param days
	 * @param active
	 */
	public void setLife(int days, boolean active);

	/**
	 * Resets the life to specified date
	 * 
	 * @param targetDateTime
	 */
	public void resetLife(LocalDateTime targetDateTime);

}
