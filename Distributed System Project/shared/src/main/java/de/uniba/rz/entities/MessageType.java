package de.uniba.rz.entities;

import java.io.Serializable;

public class MessageType implements Serializable{
	
	private static final long serialVersionUID = -5688622876331802181L;
	
	//Client constants
	public static final int RETRIEVE_ALL = 0;
	public static final int RETRIEVE_ONE = 1;
	public static final int SAVE = 2;
	public static final int UPDATE = 3;
	
	//Server constants
	public static final int SEND_ALL = 4;
	public static final int SEND_ONE = 5;
	public static final int SAVE_OK = 6;
	public static final int UPDATE_OK = 7;
	
}
