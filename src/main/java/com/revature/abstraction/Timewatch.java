package com.revature.abstraction;

import java.time.LocalDateTime;

public interface Timewatch {

	public void setLife(int days, boolean active);

	public void resetLife(LocalDateTime targetDateTime);

}
